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
        String[] invalidCommandArgs = new String[]{"-i", "1"};
        try {
            deleteCommand = deleteCommandParser.parse(invalidCommandArgs);
            Assertions.assertTrue(deleteCommand instanceof DeleteCommand);
        } catch (ParseException e) {
            Assertions.fail();
        }
    }

    @Test
    void parse_deleteByNameOption_success() {
        String[] invalidCommandArgs = new String[]{"-n", "1"};
        try {
            deleteCommand = deleteCommandParser.parse(invalidCommandArgs);
            Assertions.assertTrue(deleteCommand instanceof DeleteCommand);
        } catch (ParseException e) {
            Assertions.fail();
        }
    }
}
