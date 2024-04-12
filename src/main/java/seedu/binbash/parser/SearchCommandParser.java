package seedu.binbash.parser;

import seedu.binbash.command.SearchCommand;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.time.LocalDate;

/**
 * Parses command line arguments for creating a SearchCommand.
 */
public class SearchCommandParser extends DefaultParser {

    /**
     * Creates a new SearchCommandParser with the necessary options and option descriptions.
     */
    public SearchCommandParser() {
        options = new Options();
        new CommandOptionAdder(options)
            .addNameOption(false, "Search by name")
            .addDescriptionOption(false, "Search by description")
            .addQuantityOption(false, "Search by quantity")
            .addCostPriceOption(false, "Search by cost-price")
            .addSalePriceOption(false, "Search by sale-price")
            .addExpirationDateOption(false, "Search by expiry date")
            .addListOption(false, "Lists the first n results")
            .saveCommandOptionDescriptions("search");
    }

    /**
     * Parses the command line arguments to create a SearchCommand.
     *
     * @param commandArgs The command line arguments.
     * @return The parsed SearchCommand.
     * @throws ParseException If an error occurs during parsing.
     */
    public SearchCommand parse(String[] commandArgs) throws ParseException {
        CommandLine commandLine = super.parse(options, commandArgs);

        Option[] processedOptions = commandLine.getOptions();
        boolean hasNonListOption = false;
        for (Option processedOption : processedOptions) {
            if (processedOption.getLongOpt() != "list") {
                hasNonListOption = true;
                break;
            }
        }
        if (!hasNonListOption) {
            throw new ParseException("At least one of -n, -d, -q, -c, -s, -e option required");
        }

        SearchCommand searchCommand = new SearchCommand();

        if (commandLine.hasOption("name")) {
            String nameField = String.join(" ", commandLine.getOptionValues("name"));
            searchCommand.setNameField(nameField);
        }

        if (commandLine.hasOption("description")) {
            String descriptionField = String.join(" ", commandLine.getOptionValues("description"));
            searchCommand.setDescriptionField(descriptionField);
        }

        if (commandLine.hasOption("quantity")) {
            String[] rangeArgument = parseRangeArgument(commandLine.getOptionValue("quantity"), "quantity");
            searchCommand.setQuantityRange(parseNonNegativeIntRange(rangeArgument, "quantity"));
        }

        if (commandLine.hasOption("cost-price")) {
            String[] rangeArgument = parseRangeArgument(commandLine.getOptionValue("cost-price"), "cost-price");
            searchCommand.setCostPriceRange(parseNonNegativeDoubleRange(rangeArgument, "cost price"));
        }

        if (commandLine.hasOption("sale-price")) {
            String[] rangeArgument = parseRangeArgument(commandLine.getOptionValue("sale-price"), "sale-price");
            searchCommand.setSalePriceRange(parseNonNegativeDoubleRange(rangeArgument, "sale price"));
        }

        if (commandLine.hasOption("expiry-date")) {
            String[] rangeArgument = parseRangeArgument(commandLine.getOptionValue("expiry-date"), "expiry-date");
            LocalDate[] expiryDateRange = {LocalDate.MIN, LocalDate.MAX};
            if (rangeArgument[0] != "") {
                expiryDateRange[0] = Parser.parseDateOptionValue(rangeArgument[0], "expiry date lower bound");
            }
            if (rangeArgument[1] != "") {
                expiryDateRange[1] = Parser.parseDateOptionValue(rangeArgument[1], "expiry date upper bound");
            }
            searchCommand.setExpiryDateRange(expiryDateRange);
        }

        if (commandLine.hasOption("list")) {
            int numberOfResults = Parser.parseIntOptionValue(commandLine.getOptionValue("list"), "number of results");
            if (numberOfResults <= 0) {
                throw new ParseException("number of results must be positive");
            }
            searchCommand.setNumberOfResults(numberOfResults);
        }
        return searchCommand;
    }

    private double[] parseNonNegativeDoubleRange(String[] rangeArgument, String type) throws ParseException {
        double[] doubleRange = {0.0, Double.MAX_VALUE};
        if (rangeArgument[0] != "") {
            doubleRange[0] = Parser.parseDoubleOptionValue(rangeArgument[0], type + " lower bound");
            if (doubleRange[0] < 0) {
                throw new ParseException(type + " lower bound cannot be negative");
            }
        }
        if (rangeArgument[1] != "") {
            doubleRange[1] = Parser.parseDoubleOptionValue(rangeArgument[1], type + " upper bound");
            if (doubleRange[1] < 0) {
                throw new ParseException(type + " upper bound cannot be negative");
            }
        }
        if (doubleRange[0] > doubleRange[1]) {
            throw new ParseException(type + " lower bound is more than upper bound");
        }
        return doubleRange;
    }

    private int[] parseNonNegativeIntRange(String[] rangeArgument, String type) throws ParseException {
        int[] intRange = {0, Integer.MAX_VALUE};
        if (rangeArgument[0] != "") {
            intRange[0] = Parser.parseIntOptionValue(rangeArgument[0], "quantity lower bound");
            if (intRange[0] < 0) {
                throw new ParseException(type + " lower bound cannot be negative");
            }
        }
        if (rangeArgument[1] != "") {
            intRange[1] = Parser.parseIntOptionValue(rangeArgument[1], "quantity upper bound");
            if (intRange[1] < 0) {
                throw new ParseException(type + " upper bound cannot be negative");
            }
        }
        if (intRange[0] > intRange[1]) {
            throw new ParseException(type + " lower bound is more than upper bound");
        }
        return intRange;
    }

    /**
     * Parses the range argument for quantity, cost price, sale price, or expiry date options.
     *
     * @param argument The range argument string.
     * @param option   The option for which the range argument is parsed.
     * @return An array containing the minimum and maximum values of the range.
     * @throws ParseException If the range argument is not in the correct format.
     */
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
