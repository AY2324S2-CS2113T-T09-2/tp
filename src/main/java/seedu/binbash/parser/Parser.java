package seedu.binbash.parser;

import seedu.binbash.command.AddCommand;
import seedu.binbash.command.ByeCommand;
import seedu.binbash.command.Command;
import seedu.binbash.command.DeleteCommand;
import seedu.binbash.command.UpdateCommand;
import seedu.binbash.command.SearchCommand;
import seedu.binbash.command.ListCommand;
import seedu.binbash.command.ProfitCommand;
import seedu.binbash.exceptions.InvalidCommandException;
import seedu.binbash.exceptions.InvalidArgumentException;

import org.apache.commons.cli.ParseException;
import org.jline.builtins.Completers.OptDesc;
import seedu.binbash.exceptions.InvalidFormatException;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.ArrayList;

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

    public ArrayList<ArrayList<OptDesc>> getAllCommandsOptionDescriptions() {
        ArrayList<ArrayList<OptDesc>> allCommandsOptionDescriptions = new ArrayList<>() {
            {
                add(addCommandParser.getOptionDecriptions());
                add(restockCommandParser.getOptionDecriptions());
                add(sellCommandParser.getOptionDecriptions());
                add(updateCommandParser.getOptionDecriptions());
                add(searchCommandParser.getOptionDecriptions());
                add(listCommandParser.getOptionDecriptions());
                add(deleteCommandParser.getOptionDecriptions());
            }
        };
        return allCommandsOptionDescriptions;
    }

    public Command parseCommand(String userInput) throws InvalidCommandException {
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

    private AddCommand parseAddCommand(String[] commandArgs) throws InvalidFormatException {
        try {
            return addCommandParser.parse(commandArgs);
        } catch (InvalidArgumentException e) {
            throw new InvalidFormatException(e.getMessage());
        } catch (ParseException e) {
            throw new InvalidFormatException("Please enter a valid number.");
        }
    }

    private UpdateCommand parseUpdateCommand(String[] commandArgs) throws InvalidFormatException {
        try {
            return updateCommandParser.parse(commandArgs);
        } catch (InvalidArgumentException e) {
            throw new InvalidFormatException(e.getMessage());
        } catch (ParseException e) {
            throw new InvalidFormatException("Please enter a valid number.");
        }
    }

    private Command parseRestockCommand(String[] commandArgs) throws InvalidFormatException {
        try {
            return restockCommandParser.parse(commandArgs);
        } catch (ParseException e) {
            throw new InvalidFormatException("Please enter a valid number.");
        }
    }

    private Command parseSellCommand(String[] commandArgs) throws InvalidFormatException {
        try {
            return sellCommandParser.parse(commandArgs);
        } catch (ParseException e) {
            throw new InvalidFormatException("Please enter a valid number.");
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
}
