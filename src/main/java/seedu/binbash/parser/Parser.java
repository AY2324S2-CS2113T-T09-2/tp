package seedu.binbash;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import seedu.binbash.command.Command;
import seedu.binbash.command.AddCommand;
import seedu.binbash.command.DeleteCommand;
import seedu.binbash.command.SearchCommand;
import seedu.binbash.command.ListCommand;
import seedu.binbash.command.RestockCommand;
import seedu.binbash.command.SellCommand;
import seedu.binbash.command.ByeCommand;
import seedu.binbash.exceptions.InvalidCommandException;
import seedu.binbash.exceptions.InvalidArgumentException;
import seedu.binbash.exceptions.InvalidFormatException;

public class Parser {
    private static final DateTimeFormatter EXPECTED_INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private final ItemList itemList;

    public Parser(ItemList itemList) {
        this.itemList = itemList;
    }

    public Command parseCommand(String userInput) throws InvalidCommandException {
        String[] tokens = userInput.trim().split("\\s+", 2);
        String commandString = tokens[0].toLowerCase();
        String arguments = tokens.length > 1 ? tokens[1] : "";

        try {
            switch (commandString) {
            case "bye":
                return new ByeCommand(itemList);
            case "add":
                return parseAddCommand(userInput);
            case "delete":
                return parseDeleteCommand(userInput);
            case "list":
                return parseListCommand(userInput);
            case "search":
                return parseSearchCommand(userInput);
            case "restock":
                return parseRestockCommand(userInput);
            case "sell":
                return parseSellCommand(userInput);
            default:
                throw new InvalidCommandException("Invalid command!");
            }
        } catch (InvalidCommandException e) {
            throw e;
        }
    }

    private Command parseDeleteCommand(String userInput) throws InvalidFormatException,
            InvalidArgumentException {
        Matcher argumentMatcher = DeleteCommand.COMMAND_FORMAT.matcher(userInput);
        if (!argumentMatcher.matches()) {
            throw new InvalidFormatException("Delete command is not properly formatted!");
        }

        Pattern indexIdentifier = Pattern.compile("^-?[0-9]+$");
        Matcher indexMatcher = indexIdentifier.matcher(argumentMatcher.group("identifier"));

        if (indexMatcher.matches()) {
            int index = Integer.parseInt(argumentMatcher.group("identifier"));
            if (index <= 0 || index > itemList.getItemCount()) {
                throw new InvalidArgumentException("Index is out of bounds!");
            }
            assert index > 0 && index <= itemList.getItemCount();
            return new DeleteCommand(itemList, index);
        } else {
            String keyword = argumentMatcher.group("identifier");
            assert !keyword.isEmpty();
            return new DeleteCommand(itemList, keyword);
        }
    }

    private Command parseAddCommand(String userInput) throws InvalidFormatException {
        Matcher matcher = AddCommand.COMMAND_FORMAT.matcher(userInput);
        if (!matcher.matches()) {
            throw new InvalidFormatException("Add command is not properly formatted!");
        }
        String itemName = matcher.group("itemName").strip();
        String itemDescription = matcher.group("itemDescription").strip();
        int itemQuantity = Integer.parseInt(
                Objects.requireNonNullElse(matcher.group("itemQuantity"), "0").strip()
        );
        // itemExpirationDate is optional. If none provided, we will set it as LocalDate.MIN
        LocalDate itemExpirationDate = Optional.ofNullable(matcher.group("itemExpirationDate"))
                .map(String::strip)
                .map(x -> LocalDate.parse(x, EXPECTED_INPUT_DATE_FORMAT))
                .orElse(LocalDate.MIN);
        double itemSalePrice = Double.parseDouble(matcher.group("itemSalePrice")); // Will set as optional later
        double itemCostPrice = Double.parseDouble(matcher.group("itemCostPrice"));

        return new AddCommand(itemList, itemName, itemDescription, itemQuantity, itemExpirationDate, itemSalePrice,
                    itemCostPrice);
    }

    private Command parseRestockCommand(String userInput) throws InvalidFormatException {
        Matcher matcher = RestockCommand.COMMAND_FORMAT.matcher(userInput);
        if (!matcher.matches()) {
            throw new InvalidFormatException("Restock command is not properly formatted!");
        }
        String itemName = matcher.group("itemName");
        int restockQuantity = Integer.parseInt(
                Objects.requireNonNullElse(matcher.group("restockQuantity"), "0").strip()
        );

        return new RestockCommand(itemList, itemName, restockQuantity);
    }

    private Command parseSellCommand(String userInput) throws InvalidFormatException {
        Matcher matcher = SellCommand.COMMAND_FORMAT.matcher(userInput);
        if (!matcher.matches()) {
            throw new InvalidFormatException("Sell command is not properly formatted!");
        }
        String itemName = matcher.group("itemName");
        int sellQuantity = Integer.parseInt(
                Objects.requireNonNullElse(matcher.group("sellQuantity"), "0").strip()
        );

        return new SellCommand(itemList, itemName, sellQuantity);
    }

    private Command parseSearchCommand(String userInput) throws InvalidFormatException {
        Matcher matcher = SearchCommand.COMMAND_FORMAT.matcher(userInput);
        if (!matcher.matches()) {
            throw new InvalidFormatException("Search command is not properly formatted!");
        }
        String keyword = matcher.group("keyword");
        return new SearchCommand(itemList, keyword);
    }

    private Command parseListCommand(String arguments) {
        return new ListCommand(itemList);
    }
}
