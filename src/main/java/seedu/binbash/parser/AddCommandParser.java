package seedu.binbash.parser;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.TypeHandler;
import org.jline.builtins.Completers.OptDesc;

import seedu.binbash.command.AddCommand;
import seedu.binbash.exceptions.InvalidArgumentException;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.ArrayList;

/**
 * Parses command line arguments for creating a AddCommand.
 */
public class AddCommandParser extends DefaultParser {
    protected static final DateTimeFormatter EXPECTED_INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private ArrayList<OptDesc> optionDescriptions;

    /**
     * Creates a new AddCommandParser with the necessary options and option descriptions.
     */
    public AddCommandParser() {
        options = new Options();
        optionDescriptions = new ArrayList<>();
        new CommandOptionAdder(options, optionDescriptions)
            .addItemTypeOptionGroup()
            .addNameOption(true, "Easily recognizable item name.")
            .addDescriptionOption(true, "A brief description of the item.")
            .addQuantityOption(false, "The units of item to be added.")
            .addCostPriceOption(true, "The cost of the item.")
            .addSalePriceOption(false, "How much you'll sell the item for.")
            .addExpirationDateOption(false, "If the item has an expiration date, specify it here.")
            .addThresholdOption(false, "Minimum quantity, below which an alert will be displayed");
    }

    /**
     * Gets the option descriptions for the list command.
     *
     * @return The list of option descriptions.
     */
    public ArrayList<OptDesc> getOptionDecriptions() {
        return optionDescriptions;
    }

    /**
     * Parses the command line arguments to create an AddCommand.
     *
     * @param commandArgs The command line arguments to parse.
     * @return An AddCommand with the specified item details.
     * @throws ParseException If there is an error parsing the command arguments.
     * @throws InvalidArgumentException If there is an invalid argument in the command.
     */
    public AddCommand parse(String[] commandArgs) throws ParseException, InvalidArgumentException {

        CommandLine commandLine = super.parse(options, commandArgs);

        // Determine item type to be created
        String itemType;
        if (commandLine.hasOption("retail")) {
            itemType = "retail";
        } else {
            itemType = "operational";
        }

        // Required options
        String itemName = getItemName(commandLine);
        String itemDescription = getItemDescription(commandLine);
        double itemCostPrice = getItemCostPrice(commandLine);

        // Optional options
        int itemQuantity = getItemQuantity(commandLine);
        double itemSalePrice = getItemSalePrice(commandLine);
        LocalDate itemExpirationDate = getItemExpirationDate(commandLine);
        int itemThreshold = getItemThreshold(commandLine);

        return new AddCommand(itemType, itemName, itemDescription, itemQuantity, itemExpirationDate, itemSalePrice,
                itemCostPrice, itemThreshold);
    }

    private String getItemDescription(CommandLine commandLine) {
        return String.join(" ", commandLine.getOptionValues("description"));
    }

    private String getItemName(CommandLine commandLine) {
        return String.join(" ", commandLine.getOptionValues("name"));
    }

    private int getItemThreshold(CommandLine commandLine)
            throws InvalidArgumentException {
        String threshold = commandLine.getOptionValue("threshold");
        int itemThreshold;
        try {
            itemThreshold = Optional.ofNullable(threshold)
                    .map(Integer::parseInt)
                    .orElse(1);
        } catch (NumberFormatException e) {
            // Thrown when provided value > Integer.MAX_VALUE or < Integer.MIN_VALUE
            throw new InvalidArgumentException("Threshold value is invalid!");
        }

        if (itemThreshold < 0) {
            throw new InvalidArgumentException("Threshold value must be must be at least 0.");
        }

        return itemThreshold;
    }

    private LocalDate getItemExpirationDate (CommandLine commandLine) throws InvalidArgumentException {
        LocalDate itemExpiryDate;
        try {
            String expiryDate = commandLine.getOptionValue("expiry-date");
            itemExpiryDate = Optional.ofNullable(expiryDate)
                    .map(x -> LocalDate.parse(x, EXPECTED_INPUT_DATE_FORMAT))
                    .orElse(LocalDate.MIN);
        } catch (DateTimeParseException e) {
            throw new InvalidArgumentException("Please enter a valid date with the format as such: dd-mm-yyyy");
        }
        return itemExpiryDate;
    }

    private double getItemSalePrice(CommandLine commandLine)
            throws ParseException, InvalidArgumentException {
        String salePrice = commandLine.getOptionValue("sale-price", "0.00");
        double itemSalePrice = TypeHandler.createNumber(salePrice).doubleValue();
        if (itemSalePrice < 0) {
            throw new InvalidArgumentException("Sale price must be at least 0.");
        } else if (itemSalePrice == Double.MAX_VALUE) {
            throw new InvalidArgumentException("Your sale price is too large!");
        }
        return itemSalePrice;
    }

    private double getItemCostPrice(CommandLine commandLine)
            throws ParseException, InvalidArgumentException {
        String costPrice = commandLine.getOptionValue("cost-price");
        double itemCostPrice = TypeHandler.createNumber(costPrice).doubleValue();
        if (itemCostPrice < 0) {
            throw new InvalidArgumentException("Cost price must be at least 0.");
        } else if (itemCostPrice == Double.MAX_VALUE) {
            throw new InvalidArgumentException("Your cost price is too large!");
        }
        return itemCostPrice;
    }

    private int getItemQuantity(CommandLine commandLine)
            throws InvalidArgumentException {
        String quantity = commandLine.getOptionValue("quantity", "0");
        long itemQuantity;

        try {
            itemQuantity = TypeHandler.createNumber(quantity).longValue();
        } catch (ParseException e) {
            // Thrown when provided value > Long.MAX_VALUE or < Long.MIN_VALUE
            throw new InvalidArgumentException("Quantity value is invalid!");
        }

        // Valid Long is provided, now ensure value is within Integer bounds.
        if (itemQuantity < 0) {
            throw new InvalidArgumentException("Quantity must be at least 0.");
        } else if (itemQuantity > Integer.MAX_VALUE) {
            throw new InvalidArgumentException("Item quantity is too large!");
        }

        // Downcast as value is within bounds
        return (int) itemQuantity;
    }
}
