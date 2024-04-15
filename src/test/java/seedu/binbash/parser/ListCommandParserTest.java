package seedu.binbash.parser;

import org.apache.commons.cli.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.binbash.command.ListCommand;

class ListCommandParserTest {
    ListCommandParser listCommandParser;
    ListCommand listCommand;

    @BeforeEach
    void setUp() {
        listCommandParser = new ListCommandParser();
    }

    @Test
    void parse_noOption_success() {
        String[] commandArgs = {};
        try {
            listCommand = listCommandParser.parse(commandArgs);
            Assertions.assertTrue(listCommand instanceof ListCommand);
        } catch (ParseException e) {
            Assertions.fail();
        }
    }

    @Test
    void parse_expiryDateOption_success() {
        String[] commandArgs = {"-e"};
        try {
            listCommand = listCommandParser.parse(commandArgs);
            Assertions.assertTrue(listCommand instanceof ListCommand);
        } catch (ParseException e) {
            Assertions.fail();
        }
    }

    @Test
    void parse_costPriceOption_success() {
        String[] commandArgs = {"-c"};
        try {
            listCommand = listCommandParser.parse(commandArgs);
            Assertions.assertTrue(listCommand instanceof ListCommand);
        } catch (ParseException e) {
            Assertions.fail();
        }
    }

    @Test
    void parse_salePriceOption_success() {
        String[] commandArgs = {"-s"};
        try {
            listCommand = listCommandParser.parse(commandArgs);
            Assertions.assertTrue(listCommand instanceof ListCommand);
        } catch (ParseException e) {
            Assertions.fail();
        }
    }

    @Test
    void parse_profitOption_success() {
        String[] commandArgs = {"-p"};
        try {
            listCommand = listCommandParser.parse(commandArgs);
            Assertions.assertTrue(listCommand instanceof ListCommand);
        } catch (ParseException e) {
            Assertions.fail();
        }
    }

    @Test
    public void parse_repeatedParsing_noExceptionsThrown() {
        String[] commandArgs1 = {};
        String[] commandArgs2 = {"-e"};
        String[] commandArgs3 = {"-c"};
        String[] commandArgs4 = {"-s"};
        String[] commandArgs5 = {"-p"};

        Assertions.assertDoesNotThrow(() -> {
            listCommandParser.parse(commandArgs1);
            listCommandParser.parse(commandArgs2);
            listCommandParser.parse(commandArgs3);
            listCommandParser.parse(commandArgs4);
            listCommandParser.parse(commandArgs5);
        });
    }

}
