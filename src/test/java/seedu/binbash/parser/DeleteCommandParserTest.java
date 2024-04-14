package seedu.binbash.parser;

import org.apache.commons.cli.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.binbash.command.DeleteCommand;

class DeleteCommandParserTest {
    DeleteCommandParser deleteCommandParser;
    DeleteCommand deleteCommand;

    @BeforeEach
    void setUp() {
        deleteCommandParser = new DeleteCommandParser();
    }

    @Test
    void parse_deleteByIndexOption_success() {
        String[] validCommandArgs = new String[]{"-i", "1"};
        try {
            deleteCommand = deleteCommandParser.parse(validCommandArgs);
            Assertions.assertTrue(deleteCommand instanceof DeleteCommand);
        } catch (ParseException e) {
            Assertions.fail();
        }

    }@Test
    void parse_deleteByNegativeIndexOption_failure() {
        String[] invalidCommandArgs = new String[]{"-i", "-1"};
        ParseException thrown = Assertions.assertThrows(
                ParseException.class, () -> {
                    deleteCommandParser.parse(invalidCommandArgs);
                }, "ParseException was expected");
        Assertions.assertEquals(thrown.getMessage(), "item index must be positive");
    }

    @Test
    void parse_deleteByNameOption_success() {
        String[] validCommandArgs = new String[]{"-n", "1"};
        try {
            deleteCommand = deleteCommandParser.parse(validCommandArgs);
            Assertions.assertTrue(deleteCommand instanceof DeleteCommand);
        } catch (ParseException e) {
            Assertions.fail();
        }
    }

    @Test
    void parse_invalidOption_failure() {
        String[] invalidCommandArgs = new String[]{"-x", "1"};
        ParseException thrown = Assertions.assertThrows(
                ParseException.class, () -> {
                    deleteCommandParser.parse(invalidCommandArgs);
                }, "ParseException was expected");
        Assertions.assertEquals(thrown.getMessage(), "Unrecognized option: -x");
    }

    @Test
    void parse_missingOptionArgument_failure() {
        String[] invalidCommandArgs = new String[]{"-i"};
        ParseException thrown = Assertions.assertThrows(
                ParseException.class, () -> {
                    deleteCommandParser.parse(invalidCommandArgs);
                }, "ParseException was expected");
        Assertions.assertEquals(thrown.getMessage(), "Missing argument for option: i");
    }

    @Test
    void parse_missingNameArgument_failure() {
        String[] invalidCommandArgs = new String[]{"-n"};
        ParseException thrown = Assertions.assertThrows(
                ParseException.class, () -> {
                    deleteCommandParser.parse(invalidCommandArgs);
                }, "ParseException was expected");
        Assertions.assertEquals(thrown.getMessage(), "Missing argument for option: n");
    }

    @Test
    void parse_invalidIndexFormat_failure() {
        String[] invalidCommandArgs = new String[]{"-i", "abc"};
        ParseException thrown = Assertions.assertThrows(
                ParseException.class, () -> {
                    deleteCommandParser.parse(invalidCommandArgs);
                }, "ParseException was expected");
        Assertions.assertEquals(thrown.getMessage(), "item index must be a whole number.");
    }

    @Test
    void parse_invalidIndexValue_failure() {
        String[] invalidCommandArgs = new String[]{"-i", "0"};
        ParseException thrown = Assertions.assertThrows(
                ParseException.class, () -> {
                    deleteCommandParser.parse(invalidCommandArgs);
                }, "ParseException was expected");
        Assertions.assertEquals(thrown.getMessage(), "item index must be positive");
    }

    @Test
    void parse_validNameOption_success() {
        String[] validCommandArgs = new String[]{"-n", "item"};
        try {
            deleteCommand = deleteCommandParser.parse(validCommandArgs);
            Assertions.assertTrue(deleteCommand instanceof DeleteCommand);
        } catch (ParseException e) {
            Assertions.fail();
        }
    }

    @Test
    void parse_bothIndexAndNameOptions_failure() {
        String[] invalidCommandArgs = new String[]{"-i", "1", "-n", "name"};
        ParseException thrown = Assertions.assertThrows(
                ParseException.class, () -> {
                    deleteCommandParser.parse(invalidCommandArgs);
                }, "ParseException was expected");
        Assertions.assertEquals(thrown.getMessage(),
                "The option 'n' was specified but an option from this group has already been selected: 'i'");
    }

    @Test
    void parse_bothNameAndIndexOptions_failure() {
        String[] invalidCommandArgs = new String[]{"-n", "name", "-i", "1"};
        ParseException thrown = Assertions.assertThrows(
                ParseException.class, () -> {
                    deleteCommandParser.parse(invalidCommandArgs);
                }, "ParseException was expected");
        Assertions.assertEquals(thrown.getMessage(),
                "The option 'i' was specified but an option from this group has already been selected: 'n'");
    }

    @Test
    public void parse_repeatedParsing_noExceptionsThrown() {
        String[] commandArgs1 = new String[]{"-i", "1"};
        String[] commandArgs2 = new String[]{"-n", "item_name"};
        String[] commandArgs3 = new String[]{"-i", "999999"};

        // Repeatedly parse different command arguments
        Assertions.assertDoesNotThrow(() -> {
            deleteCommandParser.parse(commandArgs1);
            deleteCommandParser.parse(commandArgs2);
            deleteCommandParser.parse(commandArgs3);
        });
    }
}
