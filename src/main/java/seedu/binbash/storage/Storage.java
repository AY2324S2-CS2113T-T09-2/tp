package seedu.binbash.storage;

import seedu.binbash.item.Item;
import seedu.binbash.exceptions.BinBashException;
import seedu.binbash.item.OperationalItem;
import seedu.binbash.item.PerishableRetailItem;
import seedu.binbash.item.RetailItem;
import seedu.binbash.logger.BinBashLogger;
import seedu.binbash.item.PerishableOperationalItem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages storage operations for BinBash, including loading and saving item data.
 */
public class Storage {

    protected static final DateTimeFormatter EXPECTED_INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private static final int ITEM_TYPE_INDEX = 0;
    private static final int ITEM_NAME_INDEX = 1;
    private static final int ITEM_DESCRIPTION_INDEX = 2;
    private static final int ITEM_QUANTITY_INDEX = 3;
    private static final int ITEM_COST_PRICE_INDEX = 4;
    private static final int ITEM_TOTAL_UNITS_PURCHASED_INDEX = 5;
    private static final int ITEM_THRESHOLD_INDEX = 6;
    private static final int ITEM_EXPIRATION_DATE_INDEX = 7;
    private static final int ITEM_SALE_PRICE_INDEX = 8;
    private static final int ITEM_TOTAL_UNITS_SOLD_INDEX = 9;

    private static final String DELIMITER = "|";

    protected String filePath;
    protected String dataDirectoryPath;
    protected String dataFileName;
    protected boolean isCorrupted;
    protected BinBashLogger storageLogger;

    public Storage() {
        this.filePath = "data/items.txt";
        this.dataDirectoryPath = "./data/";
        this.dataFileName = "items.txt";
        this.isCorrupted = false; // set to false by default
        this.storageLogger = new BinBashLogger(Storage.class.getName());
    }

    /**
     * Loads item data from the storage file and returns a list of items.
     * If the file is corrupted, it will attempt to handle the corrupted file
     * by renaming it and creating a new file.
     *
     * @return A list of items loaded from the storage file. Returns an incomplete list if the file is corrupted.
     */
    public ArrayList<Item> loadData() {
        storageLogger.info("Preparing to load data from storage file.");
        ArrayList<Item> outputList = new ArrayList<>();

        try {
            ArrayList<String> stringRepresentationOfTxtFile = readTxtFile();
            outputList = parseLinesToItemList(stringRepresentationOfTxtFile);
        } catch (BinBashException | IOException e) {
            storageLogger.severe("Error creating the data directory and/or file.");
        }

        if (!isCorrupted) {
            storageLogger.consoleLog("Existing data file loaded successfully.");
        } else {
            handleCorruptedFile();
            storageLogger.info("User will be given an incomplete inventory.");
        }
        return outputList;
    }

    /**
     * Handles a corrupted data file by renaming the corrupted file and creating a new data file.
     * If both operations are successful, it logs the success message. Otherwise, it logs a warning message.
     */
    private void handleCorruptedFile() {
        storageLogger.info("Data file is corrupted. BinBash is attempting to rename the corrupted file" +
                " and create a new data file.");

        boolean isRenamed = renameCorruptedFile();
        boolean isNewFileCreated = createNewTxtFile();

        if (isRenamed && isNewFileCreated) {
            storageLogger.consoleLog("Data file is corrupted. Existing file renamed and new items.txt file " +
                            "created. Old corrupted file is within the ./data/ directory.");
        } else {
            storageLogger.consoleLog("Data file is corrupted. Failed to rename the corrupted file or create a new " +
                    "items.txt file.");
        }

        assert isRenamed : "Failed to rename the corrupted file";
        assert isNewFileCreated : "Failed to create a new items.txt file";
    }

    /**
     * Renames the corrupted data file to "items_corrupted.txt".
     *
     * @return true if the file is successfully renamed, false otherwise.
     */
    private boolean renameCorruptedFile() {
        File corruptedFile = new File(filePath);
        File renamedFile = new File(dataDirectoryPath + "items_corrupted.txt");

        boolean isRenamed = corruptedFile.renameTo(renamedFile);

        if (isRenamed) {
            storageLogger.info("Corrupted file successfully renamed to items_corrupted.txt.");
        }

        return isRenamed;
    }

    /**
     * Creates a new data file named "items.txt".
     *
     * @return true if the file is successfully created, false otherwise.
     */
    private boolean createNewTxtFile() {
        File newFile = new File(filePath);

        try {
            if (newFile.createNewFile()) {
                storageLogger.info("New items.txt file created successfully.");
                return true;
            }
        } catch (IOException e) {
            // This feels like a "proceed at your own risk" kind of statement.
            storageLogger.severe("Error creating new items.txt file: " + e.getMessage());
        }
        return false;
    }

    /**
     * Reads the data file ("items.txt") and returns a list of strings representing each line in the file.
     * If the "data" directory or "items.txt" file does not exist, they will be created.
     *
     * @return A list of strings, each representing a line in the data file.
     * @throws BinBashException if the directory or file cannot be created.
     * @throws IOException if an error occurs during file reading.
     */
    protected ArrayList<String> readTxtFile() throws BinBashException, IOException {
        File dataDirectory = new File(dataDirectoryPath);
        File dataFile = new File(dataDirectory, dataFileName);

        // Checks if the 'data' directory exists, if not create it
        if (!dataDirectory.exists()) {
            boolean wasDirectoryMade = dataDirectory.mkdirs();
            if (!wasDirectoryMade) {
                throw new BinBashException("Could not create data directory.");
            }
        }

        // Checks if the 'tasks.txt' file exists, if not create it
        if (!dataFile.exists()) {
            boolean wasFileCreated = dataFile.createNewFile();
            if (!wasFileCreated) {
                throw new BinBashException("Could not create items.txt file.");
            }
        }

        assert dataDirectory.exists() : "Data directory should already exist / have been created";
        assert dataFile.exists() : "Data file (items.txt) should already exist / have been created";

        ArrayList<String> dataItems = (ArrayList<String>)
                Files.readAllLines(dataFile.toPath(), Charset.defaultCharset());
        return dataItems;
    }

    /**
     * Parses the string representation of a text file and returns a list of items.
     *
     * @param stringRepresentationOfTxtFile The list of strings representing each line in the text file.
     * @return A list of items parsed from the text file.
     */
    protected ArrayList<Item> parseLinesToItemList(ArrayList<String> stringRepresentationOfTxtFile) {
        ArrayList<Item> outputList = new ArrayList<>();

        for (String line : stringRepresentationOfTxtFile) {
            try {
                Item item = convertLineToItem(line);
                if (item != null) {
                    outputList.add(item);
                } else {
                    isCorrupted = true;
                }
            } catch (NumberFormatException | DateTimeParseException e) {
                isCorrupted = true;
            }
        }
        return outputList;
    }

    /**
     * Parses a single line from the text file to create an item object.
     *
     * @param line The string representation of a single line in the text file.
     * @return The item object created from the line, or {@code null} if the item type is unknown.
     */
    protected Item convertLineToItem(String line) {
        String[] itemElements = line.split("\\|");
        String itemType = itemElements[ITEM_TYPE_INDEX];

        switch (itemType) {
        case "OperationalItem":

            OperationalItem operationalItem = new OperationalItem(
                    itemElements[ITEM_NAME_INDEX],
                    itemElements[ITEM_DESCRIPTION_INDEX],
                    Integer.parseInt(itemElements[ITEM_QUANTITY_INDEX]),
                    Double.parseDouble(itemElements[ITEM_COST_PRICE_INDEX]),
                    Integer.parseInt(itemElements[ITEM_THRESHOLD_INDEX])
            );
            operationalItem.setTotalUnitsPurchased(Integer.parseInt(itemElements[ITEM_TOTAL_UNITS_PURCHASED_INDEX]));

            return operationalItem;
        case "PerishableOperationalItem":

            PerishableOperationalItem perishableOperationalItem = new PerishableOperationalItem(
                    itemElements[ITEM_NAME_INDEX],
                    itemElements[ITEM_DESCRIPTION_INDEX],
                    Integer.parseInt(itemElements[ITEM_QUANTITY_INDEX]),
                    LocalDate.parse(itemElements[ITEM_EXPIRATION_DATE_INDEX], EXPECTED_INPUT_DATE_FORMAT),
                    Double.parseDouble(itemElements[ITEM_COST_PRICE_INDEX]),
                    Integer.parseInt(itemElements[ITEM_THRESHOLD_INDEX])
            );
            perishableOperationalItem.setTotalUnitsPurchased(Integer.parseInt(
                    itemElements[ITEM_TOTAL_UNITS_PURCHASED_INDEX]));

            return perishableOperationalItem;
        case "PerishableRetailItem":

            PerishableRetailItem perishableRetailItem = new PerishableRetailItem(
                    itemElements[ITEM_NAME_INDEX],
                    itemElements[ITEM_DESCRIPTION_INDEX],
                    Integer.parseInt(itemElements[ITEM_QUANTITY_INDEX]),
                    LocalDate.parse(itemElements[ITEM_EXPIRATION_DATE_INDEX], EXPECTED_INPUT_DATE_FORMAT),
                    Double.parseDouble(itemElements[ITEM_SALE_PRICE_INDEX]),
                    Double.parseDouble(itemElements[ITEM_COST_PRICE_INDEX]),
                    Integer.parseInt(itemElements[ITEM_THRESHOLD_INDEX])
            );
            perishableRetailItem.setTotalUnitsPurchased(Integer.parseInt(
                    itemElements[ITEM_TOTAL_UNITS_PURCHASED_INDEX]));
            perishableRetailItem.setTotalUnitsSold(Integer.parseInt(
                    itemElements[ITEM_TOTAL_UNITS_SOLD_INDEX]));

            return perishableRetailItem;
        case "RetailItem":
            RetailItem retailItem = new RetailItem(
                    itemElements[ITEM_NAME_INDEX],
                    itemElements[ITEM_DESCRIPTION_INDEX],
                    Integer.parseInt(itemElements[ITEM_QUANTITY_INDEX]),
                    Double.parseDouble(itemElements[ITEM_SALE_PRICE_INDEX]),
                    Double.parseDouble(itemElements[ITEM_COST_PRICE_INDEX]),
                    Integer.parseInt(itemElements[ITEM_THRESHOLD_INDEX])
            );
            retailItem.setTotalUnitsPurchased(Integer.parseInt(itemElements[ITEM_TOTAL_UNITS_PURCHASED_INDEX]));
            retailItem.setTotalUnitsSold(Integer.parseInt(itemElements[ITEM_TOTAL_UNITS_SOLD_INDEX]));

            return retailItem;
        default:
            return null;
        }
    }

    /**
     * Saves the list of items to the storage file in a format suitable for loading.
     *
     * @param itemList The list of items to be saved.
     */
    public void saveToStorage(List<Item> itemList) {
        String textToSave = generateTextToSave(itemList);

        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(textToSave);
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Generates a string representation of the list of items to be saved to the file.
     *
     * @param itemList The list of items to be converted into a string.
     * @return A string representation of the list of items, suitable for saving to a file.
     */
    protected String generateTextToSave(List<Item> itemList) {
        String textToSave = "";

        for (Item item: itemList) {
            if (item != null) {
                textToSave += generateStorageRepresentationOfSingleItem(item)
                        + System.lineSeparator();
            }
        }
        return textToSave;
    }

    /**
     * Generates a string representation of a single item.
     *
     * @param item The item to be converted into a string.
     * @return A string representation of the item, suitable for saving to a file.
     */
    protected String generateStorageRepresentationOfSingleItem(Item item) {
        String itemType = item.getClass().getSimpleName();

        // Common fields for all item types
        String output = itemType + DELIMITER
                + item.getItemName() + DELIMITER
                + item.getItemDescription() + DELIMITER
                + item.getItemQuantity() + DELIMITER
                + item.getItemCostPrice() + DELIMITER
                + item.getTotalUnitsPurchased() + DELIMITER
                + item.getItemThreshold() + DELIMITER;

        // Additional fields for specific item types
        switch (itemType) {
        case "OperationalItem":
            output += " " + DELIMITER
                    + " " + DELIMITER
                    + " " + DELIMITER;
            break;
        case "PerishableOperationalItem":
            PerishableOperationalItem perishableOperationalItem = (PerishableOperationalItem) item;

            output += perishableOperationalItem.getItemExpirationDate() + DELIMITER
                    + " " + DELIMITER
                    + " " + DELIMITER;
            break;
        case "PerishableRetailItem":
            PerishableRetailItem perishableRetailItem = (PerishableRetailItem) item;

            output += perishableRetailItem.getItemExpirationDate() + DELIMITER
                    + perishableRetailItem.getItemSalePrice() + DELIMITER
                    + perishableRetailItem.getTotalUnitsSold() + DELIMITER;
            break;
        case "RetailItem":
            RetailItem retailItem = (RetailItem) item;

            output += " " + DELIMITER
                    + retailItem.getItemSalePrice() + DELIMITER
                    + retailItem.getTotalUnitsSold() + DELIMITER;
            break;
        default:
            isCorrupted = true;
            break;
        }

        return output;
    }
}
