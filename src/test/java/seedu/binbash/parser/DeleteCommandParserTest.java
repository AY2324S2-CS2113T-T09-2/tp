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
}
