package seedu.binbash.parser;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import org.apache.commons.cli.ParseException;

public class SellCommandParserTest {
    private final SellCommandParser sellCommandParser = new SellCommandParser();

    // New test case: Parsing with negative quantity
    @Test
    public void parse_negativeQuantity_showsNegativeQuantityWarning() {
        String[] invalidCommandArgs = new String[]{"-i", "3", "-q", "-5"};
        ParseException thrown = Assertions.assertThrows(
                ParseException.class, () -> sellCommandParser.parse(invalidCommandArgs), "ParseException was expected");
        Assertions.assertEquals(thrown.getMessage(), "Please provide a positive number.");
    }

    // New test case: Parsing with missing item ID
    @Test
    public void parse_missingItemId_throwsParseException() {
        String[] invalidCommandArgs = new String[]{"-q", "5"};
        ParseException thrown = Assertions.assertThrows(
                ParseException.class, () -> sellCommandParser.parse(invalidCommandArgs), "ParseException was expected");
        Assertions.assertEquals(thrown.getMessage(),
                "Missing required option: [-i Identify by index, -n Identify by name]");
    }

    // New test case: Parsing with missing quantity
    @Test
    public void parse_missingQuantity_throwsParseException() {
        String[] invalidCommandArgs = new String[]{"-i", "3"};
        ParseException thrown = Assertions.assertThrows(
                ParseException.class, () -> sellCommandParser.parse(invalidCommandArgs), "ParseException was expected");
        Assertions.assertEquals(thrown.getMessage(), "Missing required option: q");
    }

    // New test case: Parsing with missing option
    @Test
    public void parse_missingOption_throwsParseException() {
        String[] invalidCommandArgs = new String[]{"-i", "3", "5"};
        ParseException thrown = Assertions.assertThrows(
                ParseException.class, () -> sellCommandParser.parse(invalidCommandArgs), "ParseException was expected");
        Assertions.assertEquals(thrown.getMessage(), "Missing required option: q");
    }
}
