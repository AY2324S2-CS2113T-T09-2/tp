package seedu.binbash.parser;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.TypeHandler;
import seedu.binbash.command.SearchCommand;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;

public class SearchCommandParser extends DefaultParser {

    public SearchCommandParser() {
        options = new Options();
        new CommandOptionAdder(options)
            .addNameOption(false, "Search by name")
            .addDescriptionOption(false, "Search by description")
            .addCostPriceOption(false, "Search by cost-price")
            .addSalePriceOption(false, "Search by sale-price")
            .addExpirationDateOption(false, "Search by expiry date")
            .addListOption(false, "Lists the first n results");
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
        if (commandLine.hasOption("cost-price")) {
            double[] costPriceRange = parsePriceField(commandLine.getOptionValue("cost-price"));
            searchCommand.setCostPriceRange(costPriceRange);
            hasOption = true;
        }
        if (commandLine.hasOption("sale-price")) {
            double[] salePriceRange = parsePriceField(commandLine.getOptionValue("sale-price"));
            searchCommand.setSalePriceRange(salePriceRange);
            hasOption = true;
        }
        if (commandLine.hasOption("expiry-date")) {
            LocalDate[] expiryDateRange = parseExpiryDateField(commandLine.getOptionValue("expiry-date"));
            searchCommand.setExpiryDateRange(expiryDateRange);
            hasOption = true;
        }
        if (!hasOption) {
            throw new ParseException("At least one of -n, -d, -c, -s, -e option required");
        }

        if (commandLine.hasOption("list")) {
            int numberOfResults = TypeHandler.createNumber(
                    commandLine.getOptionValue("list")).intValue();
            searchCommand.setNumberOfResults(numberOfResults);
        }
        return searchCommand;
    }

    /*
     * Returns a range of price to search for
     */
    private double[] parsePriceField(String priceArgument) throws ParseException {
        if (!priceArgument.contains("..") || priceArgument.length() < 3) {
            throw new ParseException("Format for price option: {min}..{max}");
        }
        double[] priceRange = {Double.MIN_VALUE, Double.MAX_VALUE};
        String[] values = priceArgument.split("\\Q..\\E");
        if (values[0].equals("")) {
            try {
                priceRange[1] = Double.parseDouble(values[1]);
                return priceRange;
            } catch (NumberFormatException e) {
                throw new ParseException("Invalid price");
            }
        }

        try {
            priceRange[0] = Double.parseDouble(values[0]);
        } catch (NumberFormatException e) {
            throw new ParseException("Invalid price");
        }
        if (values.length < 2) {
            return priceRange;
        }

        try {
            priceRange[1] = Double.parseDouble(values[1]);
        } catch (NumberFormatException e) {
            throw new ParseException("Invalid price");
        }
        return priceRange;
    }

    private LocalDate[] parseExpiryDateField(String dateArgument) throws ParseException {
        if (!dateArgument.contains("..") || dateArgument.length() < 3) {
            throw new ParseException("Format for expiry date option: {min}..{max}");
        }
        LocalDate[] expiryDateRange = {LocalDate.MIN, LocalDate.MAX};
        String[] values = dateArgument.split("\\Q..\\E");
        if (values[0].equals("")) {
            try {
                expiryDateRange[1] = LocalDate.parse(values[1], Parser.EXPECTED_INPUT_DATE_FORMAT);
                return expiryDateRange;
            } catch (DateTimeParseException e) {
                throw new ParseException("Invalid date");
            }
        }

        try {
            expiryDateRange[0] = LocalDate.parse(values[0], Parser.EXPECTED_INPUT_DATE_FORMAT);
        } catch (DateTimeParseException e) {
            throw new ParseException("Invalid date");
        }
        if (values.length < 2) {
            return expiryDateRange;
        }

        try {
            expiryDateRange[1] = LocalDate.parse(values[1], Parser.EXPECTED_INPUT_DATE_FORMAT);
        } catch (DateTimeParseException e) {
            throw new ParseException("Invalid date");
        }
        return expiryDateRange;
    }
}
