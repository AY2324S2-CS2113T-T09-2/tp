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
                ParseException.class, () -> sellCommandParser.parse(invalidCommandArgs), "ParseException was expected");
        Assertions.assertEquals(thrown.getMessage(), "Please provide a positive number.");
    }

    @Test
    public void parse_missingItemId_throwsParseException() {
        String[] invalidCommandArgs = new String[]{"-q", "5"};
        ParseException thrown = Assertions.assertThrows(
                ParseException.class, () -> sellCommandParser.parse(invalidCommandArgs), "ParseException was expected");
        Assertions.assertEquals(thrown.getMessage(),
                "Missing required option: [-i Identify by index, -n Identify by name]");
    }

    @Test
    public void parse_missingQuantity_throwsParseException() {
        String[] invalidCommandArgs = new String[]{"-i", "3"};
        ParseException thrown = Assertions.assertThrows(
                ParseException.class, () -> sellCommandParser.parse(invalidCommandArgs), "ParseException was expected");
        Assertions.assertEquals(thrown.getMessage(), "Missing required option: q");
    }

    @Test
    public void parse_missingOption_throwsParseException() {
        String[] invalidCommandArgs = new String[]{"-i", "3", "5"};
        ParseException thrown = Assertions.assertThrows(
                ParseException.class, () -> sellCommandParser.parse(invalidCommandArgs), "ParseException was expected");
        Assertions.assertEquals(thrown.getMessage(), "Missing required option: q");
    }

    @Test
    public void parse_positiveQuantity_noExceptionThrown() {
        String[] validCommandArgs = new String[]{"-i", "3", "-q", "5"};
        Assertions.assertDoesNotThrow(() -> {
            sellCommandParser.parse(validCommandArgs);
        });
    }

    @Test
    public void parse_multipleOptionsInAnyOrder_noExceptionThrown() {
        String[] validCommandArgs = new String[]{"-q", "5", "-i", "3"};
        Assertions.assertDoesNotThrow(() -> {
            sellCommandParser.parse(validCommandArgs);
        });
    }

    @Test
    public void parse_invalidItemIDFormat_throwsParseException() {
        String[] invalidCommandArgs = new String[]{"-i", "abc", "-q", "5"};
        ParseException thrown = Assertions.assertThrows(
                ParseException.class, () -> {
                    sellCommandParser.parse(invalidCommandArgs);
                }, "ParseException was expected");
        Assertions.assertEquals(thrown.getMessage(), "item index must be a whole number.");
    }

    @Test
    public void parse_invalidQuantityFormat_throwsParseException() {
        String[] invalidCommandArgs = new String[]{"-i", "3", "-q", "abc"};
        ParseException thrown = Assertions.assertThrows(
                ParseException.class, () -> {
                    sellCommandParser.parse(invalidCommandArgs);
                }, "ParseException was expected");
        Assertions.assertEquals(thrown.getMessage(), "sell quantity must be a whole number.");
    }

    @Test
    public void parse_maximumQuantity_noExceptionThrown() {
        String[] validCommandArgs = new String[]{"-i", "3", "-q", "999999"};
        Assertions.assertDoesNotThrow(() -> {
            sellCommandParser.parse(validCommandArgs);
        });
    }

    @Test
    public void parse_minimumQuantity_noExceptionThrown() {
        String[] validCommandArgs = new String[]{"-i", "3", "-q", "1"};
        Assertions.assertDoesNotThrow(() -> {
            sellCommandParser.parse(validCommandArgs);
        });
    }

    @Test
    public void parse_unrecognizedOptions_throwsParseException() {
        String[] invalidCommandArgs = new String[]{"-i", "3", "-q", "5", "-x"};
        ParseException thrown = Assertions.assertThrows(
                ParseException.class, () -> {
                    sellCommandParser.parse(invalidCommandArgs);
                }, "ParseException was expected");
        Assertions.assertEquals(thrown.getMessage(), "Unrecognized option: -x");
    }

    @Test
    public void parse_emptyArguments_throwsParseException() {
        String[] invalidCommandArgs = new String[]{};
        ParseException thrown = Assertions.assertThrows(
                ParseException.class, () -> {
                    sellCommandParser.parse(invalidCommandArgs);
                }, "ParseException was expected");
        Assertions.assertEquals(thrown.getMessage(),
                "Missing required options: [-i Identify by index, -n Identify by name], q");
    }

    @Test
    public void parse_repeatedParsing_noExceptionsThrown() {
        String[] commandArgs1 = new String[]{"-i", "3", "-q", "5"};
        String[] commandArgs2 = new String[]{"-n", "item_name", "-q", "10"};
        String[] commandArgs3 = new String[]{"-i", "999999", "-q", "20"};

        // Repeatedly parse different command arguments
        Assertions.assertDoesNotThrow(() -> {
            sellCommandParser.parse(commandArgs1);
            sellCommandParser.parse(commandArgs2);
            sellCommandParser.parse(commandArgs3);
        });
    }
}
