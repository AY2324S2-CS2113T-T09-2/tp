package seedu.binbash.parser;

import seedu.binbash.command.AddCommand;
import seedu.binbash.command.ByeCommand;
import seedu.binbash.command.Command;
import seedu.binbash.command.DeleteCommand;
import seedu.binbash.command.UpdateCommand;
import seedu.binbash.command.SearchCommand;
import seedu.binbash.command.ListCommand;
import seedu.binbash.command.ProfitCommand;
import seedu.binbash.command.QuoteCommand;

import seedu.binbash.exceptions.BinBashException;

import seedu.binbash.exceptions.InvalidCommandException;

import org.apache.commons.cli.ParseException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 * Parses user input to generate commands for managing inventory.
 */
public class Parser {
    protected static final DateTimeFormatter EXPECTED_INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private AddCommandParser addCommandParser;
    private SearchCommandParser searchCommandParser;
    private RestockCommandParser restockCommandParser;
    private SellCommandParser sellCommandParser;
    private ListCommandParser listCommandParser;
    private UpdateCommandParser updateCommandParser;
    private DeleteCommandParser deleteCommandParser;

    public Parser() {
        addCommandParser = new AddCommandParser();
        restockCommandParser = new RestockCommandParser();
        sellCommandParser = new SellCommandParser();
        updateCommandParser = new UpdateCommandParser();
        searchCommandParser = new SearchCommandParser();
        listCommandParser = new ListCommandParser();
        deleteCommandParser = new DeleteCommandParser();
    }

    /**
     * Parses a command from user input.
     *
     * @param userInput The user input to parse.
     * @return The parsed command.
     * @throws InvalidCommandException If the command is invalid or cannot be parsed.
     */
    public Command parseCommand(String userInput) throws BinBashException {
        String[] tokens = userInput.trim().split("\\s+"); // Tokenize user input
        String commandString = tokens[0].toLowerCase();
        String[] commandArgs = Arrays.copyOfRange(tokens, 1, tokens.length); // Takes only options and arguments

        switch (commandString) {
        case "bye":
        case "exit":
        case "quit":
            return new ByeCommand();
        case "add":
            return parseAddCommand(commandArgs);
        case "delete":
            return parseDeleteCommand(commandArgs);
        case "list":
            return parseListCommand(commandArgs);
        case "search":
            return parseSearchCommand(commandArgs);
        case "restock":
            return parseRestockCommand(commandArgs);
        case "sell":
            return parseSellCommand(commandArgs);
        case "update":
            return parseUpdateCommand(commandArgs);
        case "profit":
            return new ProfitCommand();
        case "quote":
            return new QuoteCommand();
        default:
            throw new InvalidCommandException("Invalid command: "  + commandString);
        }
    }

    private DeleteCommand parseDeleteCommand(String[] commandArgs) throws InvalidCommandException {
        try {
            return deleteCommandParser.parse(commandArgs);
        } catch (ParseException e) {
            throw new InvalidCommandException(e.getMessage());
        }
    }

    private AddCommand parseAddCommand(String[] commandArgs) throws InvalidCommandException {
        try {
            return addCommandParser.parse(commandArgs);
        } catch (ParseException e) {
            throw new InvalidCommandException(e.getMessage());
        }
    }

    private UpdateCommand parseUpdateCommand(String[] commandArgs) throws InvalidCommandException {
        try {
            return updateCommandParser.parse(commandArgs);
        } catch (ParseException e) {
            throw new InvalidCommandException(e.getMessage());
        }
    }

    private Command parseRestockCommand(String[] commandArgs) throws InvalidCommandException {
        try {
            return restockCommandParser.parse(commandArgs);
        } catch (ParseException e) {
            throw new InvalidCommandException(e.getMessage());
        }
    }

    private Command parseSellCommand(String[] commandArgs) throws InvalidCommandException {
        try {
            return sellCommandParser.parse(commandArgs);
        } catch (ParseException e) {
            throw new InvalidCommandException(e.getMessage());
        }
    }

    private SearchCommand parseSearchCommand(String[] commandArgs) throws InvalidCommandException {
        try {
            return searchCommandParser.parse(commandArgs);
        } catch (ParseException e) {
            throw new InvalidCommandException(e.getMessage());
        }
    }

    private ListCommand parseListCommand(String[] commandArgs) throws InvalidCommandException {
        try {
            return listCommandParser.parse(commandArgs);
        } catch (ParseException e) {
            throw new InvalidCommandException(e.getMessage());
        }
    }

    /**
     * Returns an integer value parsed from a provided string.
     *
     * @param argument The provided string.
     * @param option The option taking this parsed value as an argument.
     * @return Parsed integer value.
     * @throws ParseException If argument is not the string representation of a Java int.
     */
    static int parseIntOptionValue(String argument, String option) throws ParseException {
        long longValue;
        try {
            longValue = Long.parseLong(argument);
        } catch (NumberFormatException e) {
            throw new ParseException(option + " must be a whole number.");
        }
        if (longValue > Integer.MAX_VALUE) {
            throw new ParseException(option + " too large!");
        }
        if (longValue < Integer.MIN_VALUE) {
            throw new ParseException(option + " too small!");
        }
        return (int) longValue;
    }

    /**
     * Returns a double value parsed from a provided string.
     *
     * @param argument The provided string.
     * @param option The option taking this parsed value as an argument.
     * @return Parsed double value.
     * @throws ParseException If argument is not the string representation of a Java double.
     */
    static double parseDoubleOptionValue(String argument, String option) throws ParseException {
        if (argument.length() > 300) {
            throw new ParseException(option + " number given too long!");
        }
        try {
            double doubleValue = Double.parseDouble(argument);
            return doubleValue;
        } catch (NumberFormatException e) {
            throw new ParseException(option + " must be a number.");
        }
    }

    /**
     * Returns a LocalDate parsed from a provided string.
     *
     * @param argument The provided string.
     * @param option The option taking this parsed value as an argument.
     * @return Parsed date.
     * @throws ParseException If argument is not the string representation of a LocalDate formatted in
     *     EXPECTED_INPUT_DATE_FORMAT.
     */
    static LocalDate parseDateOptionValue(String argument, String option) throws ParseException {
        try {
            LocalDate dateValue = LocalDate.parse(argument, EXPECTED_INPUT_DATE_FORMAT);
            return dateValue;
        } catch (DateTimeParseException e) {
            throw new ParseException(option + " is invalid. Required format: dd-mm-yyyy");
        }
    }
}
