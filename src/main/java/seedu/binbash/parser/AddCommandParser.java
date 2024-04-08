package seedu.binbash.parser;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.TypeHandler;
import org.jline.builtins.Completers.OptDesc;

import seedu.binbash.command.AddCommand;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.ArrayList;

/**
 * Parses command line arguments for creating a AddCommand.
 */
public class AddCommandParser extends DefaultParser {
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
     */
    public AddCommand parse(String[] commandArgs) throws ParseException {

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

    private int getItemThreshold(CommandLine commandLine) throws ParseException {
        String threshold = commandLine.getOptionValue("threshold", "1");
        int itemThreshold = TypeHandler.createNumber(threshold).intValue();
        if (itemThreshold < 0) {
            throw new ParseException("Threshold must be must be at least 0.");
        }
        return itemThreshold;
    }

    private LocalDate getItemExpirationDate (CommandLine commandLine) throws ParseException {
        LocalDate itemExpiryDate;
        try {
            String expiryDate = commandLine.getOptionValue("expiry-date");
            itemExpiryDate = Optional.ofNullable(expiryDate)
                    .map(x -> LocalDate.parse(x, Parser.EXPECTED_INPUT_DATE_FORMAT))
                    .orElse(LocalDate.MIN);
        } catch (DateTimeParseException e) {
            throw new ParseException("Please enter a valid date with the format as such: dd-mm-yyyy");
        }
        if (!itemExpiryDate.isEqual(LocalDate.MIN) && itemExpiryDate.isBefore(LocalDate.now())) {
            throw new ParseException("Expiry date has already past.");
        }
        return itemExpiryDate;
    }

    private double getItemSalePrice(CommandLine commandLine) throws ParseException {
        String salePrice = commandLine.getOptionValue("sale-price", "0.00");
        double itemSalePrice;
        try {
            itemSalePrice = Double.parseDouble(salePrice);
        } catch (NumberFormatException e) {
            throw new ParseException("Sale price must be a number.");
        }
        if (itemSalePrice < 0) {
            throw new ParseException("Sale price must be at least 0.");
        }
        return itemSalePrice;
    }

    private double getItemCostPrice(CommandLine commandLine) throws ParseException {
        String costPrice = commandLine.getOptionValue("cost-price");
        double itemCostPrice;
        try {
            itemCostPrice = Double.parseDouble(costPrice);
        } catch (NumberFormatException e) {
            throw new ParseException("Cost price must be a number.");
        }
        if (itemCostPrice < 0) {
            throw new ParseException("Cost price must be at least 0.");
        }
        return itemCostPrice;
    }

    private int getItemQuantity(CommandLine commandLine) throws ParseException {
        String quantity = commandLine.getOptionValue("quantity", "0");
        int itemQuantity = Parser.parseIntOptionValue(quantity, "quantity");
        if (itemQuantity < 0) {
            throw new ParseException("Quantity must be at least 0.");
        }
        return itemQuantity;
    }
}
