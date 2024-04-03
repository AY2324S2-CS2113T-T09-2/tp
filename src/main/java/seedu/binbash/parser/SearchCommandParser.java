package seedu.binbash.parser;

import seedu.binbash.command.SearchCommand;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.TypeHandler;
import org.jline.builtins.Completers.OptDesc;

import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.util.ArrayList;

public class SearchCommandParser extends DefaultParser {
    private ArrayList<OptDesc> optionDescriptions;

    public SearchCommandParser() {
        options = new Options();
        optionDescriptions = new ArrayList<>();
        new CommandOptionAdder(options, optionDescriptions)
            .addNameOption(false, "Search by name")
            .addDescriptionOption(false, "Search by description")
            .addQuantityOption(false, "Search by quantity")
            .addCostPriceOption(false, "Search by cost-price")
            .addSalePriceOption(false, "Search by sale-price")
            .addExpirationDateOption(false, "Search by expiry date")
            .addListOption(false, "Lists the first n results");
    }

    public ArrayList<OptDesc> getOptionDecriptions() {
        return optionDescriptions;
    }

    public SearchCommand parse(String[] commandArgs) throws ParseException {
        CommandLine commandLine = super.parse(options, commandArgs);

        boolean hasOption = false;
        SearchCommand searchCommand = new SearchCommand();

        if (commandLine.hasOption("name")) {
            String nameField = String.join(" ", commandLine.getOptionValues("name"));// Allow multiple arguments
            searchCommand.setNameField(nameField);
            hasOption = true;
        }

        if (commandLine.hasOption("description")) {
            String descriptionField = String.join(" ", commandLine.getOptionValues("description"));
            searchCommand.setDescriptionField(descriptionField);
            hasOption = true;
        }

        if (commandLine.hasOption("quantity")) {
            String[] rangeArgument = parseRangeArgument(commandLine.getOptionValue("quantity"), "quantity");
            int[] quantityRange = {Integer.MIN_VALUE, Integer.MAX_VALUE};
            if (rangeArgument[0] != "") {
                quantityRange[0] = TypeHandler.createNumber(rangeArgument[0]).intValue();
            }
            if (rangeArgument[1] != "") {
                quantityRange[1] = TypeHandler.createNumber(rangeArgument[1]).intValue();
            }
            searchCommand.setQuantityRange(quantityRange);
            hasOption = true;
        }

        if (commandLine.hasOption("cost-price")) {
            String[] rangeArgument = parseRangeArgument(commandLine.getOptionValue("cost-price"), "cost-price");
            double[] costPriceRange = {Double.MIN_VALUE, Double.MAX_VALUE};
            if (rangeArgument[0] != "") {
                costPriceRange[0] = TypeHandler.createNumber(rangeArgument[0]).doubleValue();
            }
            if (rangeArgument[1] != "") {
                costPriceRange[1] = TypeHandler.createNumber(rangeArgument[1]).doubleValue();
            }
            searchCommand.setCostPriceRange(costPriceRange);
            hasOption = true;
        }

        if (commandLine.hasOption("sale-price")) {
            String[] rangeArgument = parseRangeArgument(commandLine.getOptionValue("sale-price"), "sale-price");
            double[] salePriceRange = {Double.MIN_VALUE, Double.MAX_VALUE};
            if (rangeArgument[0] != "") {
                salePriceRange[0] = TypeHandler.createNumber(rangeArgument[0]).doubleValue();
            }
            if (rangeArgument[1] != "") {
                salePriceRange[1] = TypeHandler.createNumber(rangeArgument[1]).doubleValue();
            }
            searchCommand.setSalePriceRange(salePriceRange);
            hasOption = true;
        }

        if (commandLine.hasOption("expiry-date")) {
            String[] rangeArgument = parseRangeArgument(commandLine.getOptionValue("expiry-date"), "expiry-date");
            LocalDate[] expiryDateRange = {LocalDate.MIN, LocalDate.MAX};
            try {
                if (rangeArgument[0] != "") {
                    expiryDateRange[0] = LocalDate.parse(rangeArgument[0], Parser.EXPECTED_INPUT_DATE_FORMAT);
                }
                if (rangeArgument[1] != "") {
                    expiryDateRange[1] = LocalDate.parse(rangeArgument[1], Parser.EXPECTED_INPUT_DATE_FORMAT);
                }
            } catch (DateTimeParseException e) {
                throw new ParseException("Invalid date");
            }
            searchCommand.setExpiryDateRange(expiryDateRange);
            hasOption = true;
        }

        if (!hasOption) {
            throw new ParseException("At least one of -n, -d, -q, -c, -s, -e option required");
        }

        if (commandLine.hasOption("list")) {
            int numberOfResults = TypeHandler.createNumber(
                    commandLine.getOptionValue("list")).intValue();
            searchCommand.setNumberOfResults(numberOfResults);
        }
        return searchCommand;
    }

    String[] parseRangeArgument(String argument, String option) throws ParseException {
        if (!argument.contains("..") || argument.length() < 3) {
            throw new ParseException("Format for " + option + " option: {min}..{max}. "
                    + "At least one of min or max is required.");
        }
        String[] argumentRange = {"", ""};
        String[] values = argument.split("\\Q..\\E");
        if (values[0].equals("")) {
            argumentRange[1] = values[1];
            return argumentRange;
        }
        argumentRange[0] = values[0];
        try {
            argumentRange[1] = values[1];
            return argumentRange;
        } catch (ArrayIndexOutOfBoundsException e) {
            return argumentRange;
        }
    }
}
