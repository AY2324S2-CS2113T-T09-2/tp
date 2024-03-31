package seedu.binbash.parser;

import seedu.binbash.command.SearchCommand;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.TypeHandler;
import org.jline.builtins.Completers.OptDesc;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.util.ArrayList;

public class SearchCommandParser extends DefaultParser {
    protected static final DateTimeFormatter EXPECTED_INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private ArrayList<OptDesc> optionDescriptions;

    public ArrayList<OptDesc> getOptionDecriptions() {
        return optionDescriptions;
    }

    public SearchCommandParser() {
        options = new Options();
        optionDescriptions = new ArrayList<>();
        new CommandOptionAdder(options, optionDescriptions)
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
            double costPriceField = parsePriceField(commandLine.getOptionValue("cost-price"));
            searchCommand.setCostPriceField(costPriceField);
            hasOption = true;
        }
        if (commandLine.hasOption("sale-price")) {
            double salePriceField = parsePriceField(commandLine.getOptionValue("sale-price"));
            searchCommand.setSalePriceField(salePriceField);
            hasOption = true;
        }
        if (commandLine.hasOption("expiry-date")) {
            //LocalDate expiryDateField = Optional.ofNullable(commandLine.getOptionValue("expiry-date"))
            //        .map(x -> LocalDate.parse(x, EXPECTED_INPUT_DATE_FORMAT))
            //        .orElse(LocalDate.MIN);
            try {
                LocalDate expiryDateField = LocalDate.parse(commandLine.getOptionValue("expiry-date"),
                        EXPECTED_INPUT_DATE_FORMAT);
                searchCommand.setExpiryDateField(expiryDateField);
            } catch (DateTimeParseException e) {
                throw new ParseException(e.getMessage());
            }
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
     * Returns a negative number if searching everything less than, positive if searching more than
     */
    private double parsePriceField(String argument) throws ParseException {
        if (!argument.substring(0, 1).equals("<") && !argument.substring(0, 1).equals(">")) {
            throw new ParseException("Price argument must start with '<' or '>'!");
        }
        boolean isSearchSmaller = argument.substring(0, 1).equals("<");
        double price = 0;
        try {
            price = Double.parseDouble(argument.substring(1));
        } catch (NumberFormatException e) {
            throw new ParseException("Please specify a price!");
        }
        if (isSearchSmaller) {
            price *= -1;
        }
        return price;
    }
}
