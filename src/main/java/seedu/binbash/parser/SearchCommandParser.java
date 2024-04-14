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
    private static final int RANGE_ARGUMENT_LOWER_BOUND = 0;
    private static final int RANGE_ARGUMENT_UPPER_BOUND = 1;

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
        Parser.checkDuplicateOption(commandLine.getOptions());
        checkNonListOption(commandLine.getOptions());

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
            searchCommand.setExpiryDateRange(parseDateRange(rangeArgument, "expiry date"));
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
            argumentRange[RANGE_ARGUMENT_UPPER_BOUND] = values[1];
            return argumentRange;
        }
        argumentRange[RANGE_ARGUMENT_LOWER_BOUND] = values[0];
        try {
            argumentRange[RANGE_ARGUMENT_UPPER_BOUND] = values[1];
            return argumentRange;
        } catch (ArrayIndexOutOfBoundsException e) {
            return argumentRange;
        }
    }

    private double[] parseNonNegativeDoubleRange(String[] rangeArgument, String type) throws ParseException {
        double[] doubleRange = {0.0, Double.MAX_VALUE};
        if (rangeArgument[RANGE_ARGUMENT_LOWER_BOUND] != "") {
            doubleRange[RANGE_ARGUMENT_LOWER_BOUND] = Parser.parseDoubleOptionValue(rangeArgument[RANGE_ARGUMENT_LOWER_BOUND],
                    type + " lower bound");
            if (doubleRange[RANGE_ARGUMENT_LOWER_BOUND] < 0) {
                throw new ParseException(type + " lower bound cannot be negative");
            }
        }
        if (rangeArgument[RANGE_ARGUMENT_UPPER_BOUND] != "") {
            doubleRange[RANGE_ARGUMENT_UPPER_BOUND] = Parser.parseDoubleOptionValue(rangeArgument[RANGE_ARGUMENT_UPPER_BOUND],
                    type + " upper bound");
            if (doubleRange[RANGE_ARGUMENT_UPPER_BOUND] < 0) {
                throw new ParseException(type + " upper bound cannot be negative");
            }
        }
        if (doubleRange[RANGE_ARGUMENT_LOWER_BOUND] > doubleRange[RANGE_ARGUMENT_UPPER_BOUND]) {
            throw new ParseException(type + " lower bound is more than upper bound");
        }
        return doubleRange;
    }

    private int[] parseNonNegativeIntRange(String[] rangeArgument, String type) throws ParseException {
        int[] intRange = {0, Integer.MAX_VALUE};
        if (rangeArgument[RANGE_ARGUMENT_LOWER_BOUND] != "") {
            intRange[RANGE_ARGUMENT_LOWER_BOUND] = Parser.parseIntOptionValue(rangeArgument[RANGE_ARGUMENT_LOWER_BOUND],
                    type + " lower bound");
            if (intRange[RANGE_ARGUMENT_LOWER_BOUND] < 0) {
                throw new ParseException(type + " lower bound cannot be negative");
            }
        }
        if (rangeArgument[RANGE_ARGUMENT_UPPER_BOUND] != "") {
            intRange[RANGE_ARGUMENT_UPPER_BOUND] = Parser.parseIntOptionValue(rangeArgument[RANGE_ARGUMENT_UPPER_BOUND],
                    "quantity upper bound");
            if (intRange[RANGE_ARGUMENT_UPPER_BOUND] < 0) {
                throw new ParseException(type + " upper bound cannot be negative");
            }
        }
        if (intRange[RANGE_ARGUMENT_LOWER_BOUND] > intRange[RANGE_ARGUMENT_UPPER_BOUND]) {
            throw new ParseException(type + " lower bound is more than upper bound");
        }
        return intRange;
    }

    private LocalDate[] parseDateRange(String[] rangeArgument, String type) throws ParseException {
        LocalDate[] dateRange = {LocalDate.MIN, LocalDate.MAX};
        if (rangeArgument[RANGE_ARGUMENT_LOWER_BOUND] != "") {
            dateRange[RANGE_ARGUMENT_LOWER_BOUND] = Parser.parseDateOptionValue(rangeArgument[RANGE_ARGUMENT_LOWER_BOUND],
                    "expiry date lower bound");
        }
        if (rangeArgument[RANGE_ARGUMENT_UPPER_BOUND] != "") {
            dateRange[RANGE_ARGUMENT_UPPER_BOUND] = Parser.parseDateOptionValue(rangeArgument[RANGE_ARGUMENT_UPPER_BOUND],
                    "expiry date upper bound");
        }
        if (dateRange[RANGE_ARGUMENT_LOWER_BOUND].isAfter(dateRange[RANGE_ARGUMENT_UPPER_BOUND])) {
            throw new ParseException(type + " lower bound is after upper bound");
        }
        return dateRange;
    }

    private void checkNonListOption(Option[] processedOptions) throws ParseException {
        for (Option processedOption : processedOptions) {
            if (processedOption.getLongOpt() != "list") {
                return;
            }
        }
        throw new ParseException("At least one of -n, -d, -q, -c, -s, -e option required");
    }
}
