package seedu.binbash.parser;

import seedu.binbash.command.SearchCommand;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import org.apache.commons.cli.ParseException;

import java.util.Arrays;

public class SearchCommandParserTest {
    private final SearchCommandParser searchCommandParser = new SearchCommandParser();

    @Test
    public void parse_noOptionSpecified_throwsParseExceptionWithWarning() {
        String[] invalidCommandArgs = new String[]{"3", "-l", "2"};
        ParseException thrown = Assertions.assertThrows(
                ParseException.class, () -> {
                    searchCommandParser.parse(invalidCommandArgs);
                }, "ParseException was expected");
        Assertions.assertEquals(thrown.getMessage(), "At least one of -n, -d, -q, -c, -s, -e option required");
    }

    @Test
    public void parse_costPriceRangeSpecified_correctlyParsesPriceRange() {
        String[] invalidCommandArgs = new String[]{"-c", "1..2"};
        try {
            SearchCommand searchCommand = searchCommandParser.parse(invalidCommandArgs);
            Assertions.assertTrue(searchCommand instanceof SearchCommand);
        } catch (ParseException e) {
            Assertions.fail();
        }
    }

    @Test
    public void parse_expiryDateOption_success() {
        String[] commandArgs = new String[]{"-e", "..23-11-2023"};
        try {
            SearchCommand searchCommand = searchCommandParser.parse(commandArgs);
            Assertions.assertTrue(searchCommand instanceof SearchCommand);
        } catch (ParseException e) {
            Assertions.fail();
        }
    }

    @Test
    void parseRangeArgument_multipleRanges_success() {
        String[] rangeArguments = {"..4", "0..", "1..6", "..5..10"};
        String[][] expectedRanges = {
            {"", "4"}, {"0", ""}, {"1", "6"}, {"", "5"}
        };
        for (int i = 0; i < rangeArguments.length; i += 1) {
            try {
                Assertions.assertTrue(Arrays.equals(expectedRanges[i],
                        searchCommandParser.parseRangeArgument(rangeArguments[i], "option")));
            } catch (ParseException e) {
                Assertions.fail();
            }
        }
    }
}
