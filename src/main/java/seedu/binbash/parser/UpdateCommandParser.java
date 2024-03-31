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

        String itemDescription = parseDescriptionOption(commandLine, "description");
        Integer itemQuantity = parseIntegerOption(commandLine, "quantity");
        Double itemCostPrice = parseDoubleOption(commandLine, "cost-price");
        Double itemSalePrice = parseDoubleOption(commandLine, "sale-price");
        LocalDate itemExpirationDate = parseDateOption(commandLine, "expiry-date");
        Integer itemThreshold = parseIntegerOption(commandLine, "threshold");

        if (commandLine.hasOption("index")) {
            int index = Integer.parseInt(commandLine.getOptionValue("index"));
            return new UpdateCommand(index, itemDescription, itemQuantity, itemExpirationDate,
                    itemSalePrice, itemCostPrice, itemThreshold);
        } else {
            String itemName = parseNameOption(commandLine, "name");
            return new UpdateCommand(itemName, itemDescription, itemQuantity, itemExpirationDate,
                    itemSalePrice, itemCostPrice, itemThreshold);
        }
    }

    private String parseNameOption(CommandLine commandLine, String option) {
        return String.join(" ", commandLine.getOptionValues("name"));
    }

    private String parseDescriptionOption(CommandLine commandLine, String option) {
        return Optional.ofNullable(commandLine.getOptionValues(option))
                .map(values -> String.join(" ", values))
                .orElse(null);
    }

    private Integer parseIntegerOption(CommandLine commandLine, String option) {
        return Optional.ofNullable(commandLine.getOptionValue(option))
                .map(Integer::parseInt)
                .orElse(null);
    }

    private Double parseDoubleOption(CommandLine commandLine, String option) {
        return Optional.ofNullable(commandLine.getOptionValue(option))
                .map(Double::parseDouble)
                .orElse(null);
    }

    private LocalDate parseDateOption(CommandLine commandLine, String option) {
        return Optional.ofNullable(commandLine.getOptionValue(option))
                .map(x -> LocalDate.parse(x, EXPECTED_INPUT_DATE_FORMAT))
                .orElse(null);
    }
}

