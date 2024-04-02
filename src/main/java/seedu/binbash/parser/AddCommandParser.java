package seedu.binbash.parser;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.TypeHandler;
import org.jline.builtins.Completers.OptDesc;

import seedu.binbash.command.AddCommand;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ArrayList;

public class AddCommandParser extends DefaultParser {
    protected static final DateTimeFormatter EXPECTED_INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private ArrayList<OptDesc> optionDescriptions;

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

    public ArrayList<OptDesc> getOptionDecriptions() {
        return optionDescriptions;
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
        double itemCostPrice = TypeHandler.createNumber(
                commandLine.getOptionValue("cost-price")).doubleValue();

        // Optional options
        int itemQuantity = TypeHandler.createNumber(
                commandLine.getOptionValue("quantity", "0.00")).intValue();
        double itemSalePrice = TypeHandler.createNumber(
                commandLine.getOptionValue("sale-price", "0.00")).doubleValue();
        LocalDate itemExpirationDate = Optional.ofNullable(commandLine.getOptionValue("expiry-date"))
                .map(x -> LocalDate.parse(x, EXPECTED_INPUT_DATE_FORMAT))
                .orElse(LocalDate.MIN);
        int itemThreshold = Optional.ofNullable(commandLine.getOptionValue("threshold"))
                .map(Integer::parseInt)
                .orElse(1);

        return new AddCommand(itemType, itemName, itemDescription, itemQuantity, itemExpirationDate, itemSalePrice,
                itemCostPrice, itemThreshold);

    }
}
