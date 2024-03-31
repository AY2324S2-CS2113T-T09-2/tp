package seedu.binbash.parser;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import seedu.binbash.command.UpdateCommand;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class UpdateCommandParser extends DefaultParser {
    protected static final DateTimeFormatter EXPECTED_INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private Options options;

    public UpdateCommandParser() {
        options = new Options();
        new CommandOptionAdder(options)
                .addNameOption(false, "Easily recognizable item name.")
                .addDescriptionOption(false, "A brief description of the item.")
                .addQuantityOption(false, "The units of item to be added.")
                .addCostPriceOption(false, "The cost of the item.")
                .addSalePriceOption(false, "How much you'll sell the item for.")
                .addExpirationDateOption(false,
                        "If the item has an expiration date, specify it here.")
                .addThresholdOption(false,
                        "Minimum quantity, below which an alert will be displayed")
                .addIndexOption(false, "The index of the item to update.");
    }

    public UpdateCommand parse(String[] commandArgs) throws ParseException {
        CommandLine commandLine = new DefaultParser().parse(options, commandArgs);

        // Optional options
        String itemDescription = Optional.ofNullable(commandLine.getOptionValues("description"))
                .map(values -> String.join(" ", values))
                .orElse(null);
        Integer itemQuantity = Optional.ofNullable(commandLine.getOptionValue("quantity"))
                .map(Integer::parseInt)
                .orElse(null);
        Double itemCostPrice = Optional.ofNullable(commandLine.getOptionValue("cost"))
                .map(Double::parseDouble)
                .orElse(null);
        Double itemSalePrice = Optional.ofNullable(commandLine.getOptionValue("salePrice"))
                .map(Double::parseDouble)
                .orElse(null);
        LocalDate itemExpirationDate = Optional.ofNullable(commandLine.getOptionValue("expiration"))
                .map(x -> LocalDate.parse(x, EXPECTED_INPUT_DATE_FORMAT))
                .orElse(null);
        Integer itemThreshold = Optional.ofNullable(commandLine.getOptionValue("threshold"))
                .map(Integer::parseInt)
                .orElse(null);

        if (commandLine.hasOption("index")) {
            int index = Integer.parseInt(commandLine.getOptionValue("index"));
            return new UpdateCommand(index, itemDescription, itemQuantity, itemExpirationDate,
                    itemSalePrice, itemCostPrice, itemThreshold);
        } else {
            String itemName = String.join(" ", commandLine.getOptionValues("name"));
            return new UpdateCommand(itemName, itemDescription, itemQuantity, itemExpirationDate,
                    itemSalePrice, itemCostPrice, itemThreshold);
        }
    }
}

