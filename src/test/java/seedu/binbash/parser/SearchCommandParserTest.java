package seedu.binbash.parser;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import org.apache.commons.cli.ParseException;

public class SearchCommandParserTest {
    private final SearchCommandParser searchCommandParser = new SearchCommandParser();

    @Test
    public void parse_noOptionSpecified_throwsParseExceptionWithWarning() {
        String[] invalidCommandArgs = new String[]{"3", "-l", "2"};
        ParseException thrown = Assertions.assertThrows(
                ParseException.class, () -> {
                    searchCommandParser.parse(invalidCommandArgs);
                }, "ParseException was expected");
        Assertions.assertEquals("At least one of -n, -d, -c, -s, -e option required", thrown.getMessage());
    }
}
