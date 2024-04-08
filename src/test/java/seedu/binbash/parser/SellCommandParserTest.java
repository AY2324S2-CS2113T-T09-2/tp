package seedu.binbash.parser;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import org.apache.commons.cli.ParseException;

public class SellCommandParserTest {
    private final SellCommandParser sellCommandParser = new SellCommandParser();

    @Test
    public void parse_negativeQuantity_showsNegativeQuantityWarning() {
        String[] invalidCommandArgs = new String[]{"-i", "3", "-q", "-5"};
        ParseException thrown = Assertions.assertThrows(
                ParseException.class, () -> {
                    sellCommandParser.parse(invalidCommandArgs);
                }, "ParseException was expected");
        Assertions.assertEquals(thrown.getMessage(), "Please provide a positive number.");
    }
}
