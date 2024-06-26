package seedu.binbash.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import seedu.binbash.inventory.ItemList;
import seedu.binbash.command.AddCommand;
import seedu.binbash.command.Command;
import seedu.binbash.command.ListCommand;
import seedu.binbash.command.ByeCommand;
import seedu.binbash.command.QuoteCommand;
import seedu.binbash.exceptions.BinBashException;
import seedu.binbash.exceptions.InvalidCommandException;
import seedu.binbash.item.Item;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.ParseException;

import org.junit.jupiter.api.Assertions;
import seedu.binbash.quotes.Quotes;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    private ItemList itemList;
    private Parser parser;

    @BeforeEach
    public void setUp() {
        itemList = new ItemList(new ArrayList<Item>());
        parser = new Parser();
    }

    @Test
    public void testParseCommand_validCommandBye_returnsByeCommand() {
        try {
            Command command = parser.parseCommand("bye");
            assertTrue(command instanceof ByeCommand);
        } catch (BinBashException e) {
            fail("Unexpected InvalidCommandException: " + e.getMessage());
        }
    }

    @Test
    public void testParseCommand_repeatedByeCommand_returnsByeCommandAfterRestart() {
        try {
            Command command1 = parser.parseCommand("bye");
            assertTrue(command1 instanceof ByeCommand);
            setUp();
            Command command2 = parser.parseCommand("bye");
            assertTrue(command2 instanceof ByeCommand);
        } catch (BinBashException e) {
            fail("Unexpected InvalidCommandException: " + e.getMessage());
        }
    }

    @Test
    public void testParseCommand_validCommandQuote_returnsQuoteCommand() {
        try {
            Command command = parser.parseCommand("quote");
            assertTrue(command instanceof QuoteCommand);
        } catch (BinBashException e) {
            fail("Unexpected InvalidCommandException: " + e.getMessage());
        }
    }

    @Test
    public void testParseCommand_repeatedQuoteCommand_returnsQuoteCommand() {
        try {
            Command command1 = parser.parseCommand("quote");
            Command command2 = parser.parseCommand("quote");
            assertTrue(command1 instanceof QuoteCommand);
            assertTrue(command2 instanceof QuoteCommand);
        } catch (BinBashException e) {
            fail("Unexpected InvalidCommandException: " + e.getMessage());
        }
    }


    @Test
    void getRandomQuote_returnsValidQuote() {
        String quote = Quotes.getRandomQuote();

        boolean quoteFound = false;
        for (String predefinedQuote : Quotes.CUSTOM_MESSAGES) {
            if (predefinedQuote.equals(quote)) {
                quoteFound = true;
                break;
            }
        }
        assertTrue(quoteFound, "Returned quote is not one of the predefined quotes.");
    }

    @Test
    public void testParseCommand_invalidCommand_throwsInvalidCommandException() {
        assertThrows(InvalidCommandException.class, () -> parser.parseCommand("invalid"));
    }

    @Test
    public void parseAddCommand_multipleItemTypeOptions_throwsInvalidCommandException() {
        assertThrows(
                InvalidCommandException.class,
                () -> parser.parseCommand("add -re -op -n Test Item -d Test Description -c 0.00")
        );
    }

    @Test
    public void parseAddCommand_createItemWithNoQuantityAndExpirationDate_returnsAddCommand() {
        try {
            itemList.addItem("retail", "Test Item", "Test Description", 0, LocalDate.MIN, 0.00, 0.00, 0);
            Command command = parser.parseCommand("add -re -n Test Item -d Test Description -s 0.00 -c 0.00 -t 0");
            assertTrue(command instanceof AddCommand);
        } catch (BinBashException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void parseAddCommand_createItemWithNoQuantity_returnsAddCommand() {
        try {
            itemList.addItem("retail", "Test Item", "Test Description", 0, LocalDate.of(2999, 1, 1), 0.00, 0.00, 0);
            Command command = parser.parseCommand(
                    "add -re -n Test Item -d Test Description -e 01-01-2999 -s 0.00 -c 0.00 -t 0"
            );
            assertTrue(command instanceof AddCommand);
        } catch (BinBashException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void parseAddCommand_createItemWithNoExpiration_returnsAddCommand() {
        try {
            itemList.addItem("retail", "Test Item", "Test Description", 10, LocalDate.MIN, 0.00, 0.00, 0);
            Command command = parser.parseCommand(
                    "add -re -n Test Item -d Test Description -q 10 -s 0.00 -c 0.00 -t 0");
            assertInstanceOf(AddCommand.class, command);
        } catch (BinBashException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void parseAddCommand_createItemWithAllArguments_returnsAddCommand() {
        try {
            itemList.addItem("retail", "Test Item", "Test Description", 10, LocalDate.of(2999, 1, 1), 0.00, 0.00, 0);
            Command command = parser.parseCommand(
                    "add -re -n Test Item -d Test Description -q 10 -e 01-01-2999 -s 0.00 -c 0.00 -t 0"
            );
            assertInstanceOf(AddCommand.class, command);
        } catch (BinBashException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void testParseCommand_validCommandList_returnsListCommand() throws BinBashException {
        Command command = parser.parseCommand("list");
        assertTrue(command instanceof ListCommand);
    }

    @Test
    public void testParseCommand_invalidAddCommand_throwsInvalidCommandException() {
        assertThrows(InvalidCommandException.class, () -> parser.parseCommand("add invalid format"));
    }

    @Test
    public void testParseCommand_invalidSearchCommand_throwsInvalidCommandException() {
        assertThrows(InvalidCommandException.class, () -> parser.parseCommand("search"));
    }

    @Test
    void checkDuplicateOption_processedDuplicateOptions_throwsParseExceptionWithWarning() {
        Option[] processedOptions = new Option[]{new Option("t", "test option"), new Option("t", "test option 2")};
        ParseException thrown = Assertions.assertThrows(
                ParseException.class, () -> {
                    parser.checkDuplicateOption(processedOptions);
                }, "ParseException was expected");
        Assertions.assertEquals(thrown.getMessage(), "Duplicate option: -t");
    }

    @Test
    void parseIntOptionValue_intMin_parsesIntMin() {
        String intOptionValue = String.valueOf(Integer.MIN_VALUE);
        try {
            int parsedInt = parser.parseIntOptionValue(intOptionValue, "int");
            Assertions.assertEquals(parsedInt, Integer.MIN_VALUE);
        } catch (ParseException e) {
            Assertions.fail();
        }
    }

    @Test
    void parseIntOptionValue_moreThanIntMax_throwsParseExceptionWithWarning() {
        String intOptionValue = String.valueOf(Long.MAX_VALUE);
        ParseException thrown = Assertions.assertThrows(
                ParseException.class, () -> {
                    parser.parseIntOptionValue(intOptionValue, "int");
                }, "ParseException was expected");
        Assertions.assertEquals(thrown.getMessage(), "int too large!");
    }

    @Test
    void parseDoubleOptionValue_nonNumber_throwsParseExceptionWithWarning() {
        String doubleOptionValue = "not a double";
        ParseException thrown = Assertions.assertThrows(
                ParseException.class, () -> {
                    parser.parseDoubleOptionValue(doubleOptionValue, "double");
                }, "ParseException was expected");
        Assertions.assertEquals(thrown.getMessage(), "double must be a number.");
    }
}
