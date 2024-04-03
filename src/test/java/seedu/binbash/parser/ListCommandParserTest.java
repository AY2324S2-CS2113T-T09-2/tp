package seedu.binbash.parser;

import org.apache.commons.cli.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import seedu.binbash.command.ListCommand;

class ListCommandParserTest {
    private final ListCommandParser listCommandParser = new ListCommandParser();

    @Test
    void parse_noOption_success() {
        String[] commandArgs = {};
        try {
            ListCommand searchCommand = listCommandParser.parse(commandArgs);
            Assertions.assertTrue(searchCommand instanceof ListCommand);
        } catch (ParseException e) {
            Assertions.fail();
        }
    }

    @Test
    void parse_expiryDateOption_success() {
        String[] commandArgs = {"-e"};
        try {
            ListCommand searchCommand = listCommandParser.parse(commandArgs);
            Assertions.assertTrue(searchCommand instanceof ListCommand);
        } catch (ParseException e) {
            Assertions.fail();
        }
    }

    @Test
    void parse_costPriceOption_success() {
        String[] commandArgs = {"-c"};
        try {
            ListCommand searchCommand = listCommandParser.parse(commandArgs);
            Assertions.assertTrue(searchCommand instanceof ListCommand);
        } catch (ParseException e) {
            Assertions.fail();
        }
    }

    @Test
    void parse_salePriceOption_success() {
        String[] commandArgs = {"-s"};
        try {
            ListCommand searchCommand = listCommandParser.parse(commandArgs);
            Assertions.assertTrue(searchCommand instanceof ListCommand);
        } catch (ParseException e) {
            Assertions.fail();
        }
    }

    @Test
    void parse_profitOption_success() {
        String[] commandArgs = {"-p"};
        try {
            ListCommand searchCommand = listCommandParser.parse(commandArgs);
            Assertions.assertTrue(searchCommand instanceof ListCommand);
        } catch (ParseException e) {
            Assertions.fail();
        }
    }
}
