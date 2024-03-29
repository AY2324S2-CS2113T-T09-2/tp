package seedu.binbash.parser;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import seedu.binbash.command.AddCommand;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.Optional;

public class AddCommandParser extends DefaultParser {
    protected static final DateTimeFormatter EXPECTED_INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public AddCommandParser() {
        options = new Options();
        new CommandOptionAdder(options)
                .addItemTypeOptionGroup()
                .addNameOption(true, "Easily recognizable item name.")
                .addDescriptionOption(true, "A brief description of the item.")
                .addQuantityOption(false, "The units of item to be added.")
                .addCostPriceOption(true, "The cost of the item.")
                .addSalePriceOption(false, "How much you'll sell the item for.")
                .addExpirationDateOption(false, "If the item has an expiration date, specify it here.");
    }

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
        String itemName = String.join(" ", commandLine.getOptionValues("name"));// Allow multiple arguments
        String itemDescription = String.join(" ", commandLine.getOptionValues("description"));
        double itemCostPrice;
        try {
            itemCostPrice = Double.parseDouble(commandLine.getOptionValue("cost"));
        } catch (NumberFormatException e) {
            throw new ParseException("Item cost price must be a number!");
        }

        // Optional options
        int itemQuantity;
        try {
            itemQuantity = Integer.parseInt(commandLine.getOptionValue("quantity", "0"));
        } catch (NumberFormatException e) {
            throw new ParseException("Item quantity must be a number!");
        }

        double itemSalePrice;
        try {
            itemSalePrice = Double.parseDouble(commandLine.getOptionValue("salePrice", "0.00"));
        } catch (NumberFormatException e) {
            throw new ParseException("Item sale price must be a number!");
        }

        LocalDate itemExpirationDate = Optional.ofNullable(commandLine.getOptionValue("expiration"))
                .map(x -> LocalDate.parse(x, EXPECTED_INPUT_DATE_FORMAT))
                .orElse(LocalDate.MIN);

        return new AddCommand(itemType, itemName, itemDescription, itemQuantity, itemExpirationDate, itemSalePrice,
                itemCostPrice);
    }
}
