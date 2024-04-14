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
        String[] invalidCommandArgs = new String[]{"-l", "2"};
        ParseException thrown = Assertions.assertThrows(
                ParseException.class, () -> {
                    searchCommandParser.parse(invalidCommandArgs);
                }, "ParseException was expected");
        Assertions.assertEquals(thrown.getMessage(), "At least one of -n, -d, -q, -c, -s, -e option required");
    }

    @Test
    public void parse_invalidQuantityRange_correctExceptionMessage() {
        String[] invalidCommandArgs = new String[]{"-q", "invalid"};
        ParseException thrown = Assertions.assertThrows(
                ParseException.class, () -> {
                    searchCommandParser.parse(invalidCommandArgs);
                }, "ParseException was expected");
        Assertions.assertEquals(thrown.getMessage(), "Format for quantity option: {min}..{max}. "
                + "At least one of min or max is required.");
    }

    @Test
    public void parse_invalidCostPriceUpperBound_correctExceptionMessage() {
        String[] invalidCommandArgs = new String[]{"-c", "2..-5"};
        ParseException thrown = Assertions.assertThrows(
                ParseException.class, () -> {
                    searchCommandParser.parse(invalidCommandArgs);
                }, "ParseException was expected");
        Assertions.assertEquals(thrown.getMessage(), "cost price upper bound cannot be negative");
    }

    @Test
    public void parse_invalidSalePriceLowerBound_correctExceptionMessage() {
        String[] invalidCommandArgs = new String[]{"-s", "sam..3.30"};
        ParseException thrown = Assertions.assertThrows(
                ParseException.class, () -> {
                    searchCommandParser.parse(invalidCommandArgs);
                }, "ParseException was expected");
        Assertions.assertEquals(thrown.getMessage(), "sale price lower bound must be a number.");
    }

    @Test
    public void parse_invalidExpiryDateBounds_correctExceptionMessage() {
        String[] invalidCommandArgs = new String[]{"-e", "11-02-2025..11-03-2024"};
        ParseException thrown = Assertions.assertThrows(
                ParseException.class, () -> {
                    searchCommandParser.parse(invalidCommandArgs);
                }, "ParseException was expected");
        Assertions.assertEquals(thrown.getMessage(), "expiry date lower bound is after upper bound");
    }

    @Test
    public void parse_negativeResultsSpecified_correctExceptionMessage() {
        String[] invalidCommandArgs = new String[]{"-n", " ", "-l", "-20"};
        ParseException thrown = Assertions.assertThrows(
                ParseException.class, () -> {
                    searchCommandParser.parse(invalidCommandArgs);
                }, "ParseException was expected");
        Assertions.assertEquals(thrown.getMessage(), "number of results must be positive");
    }

    @Test
    public void parse_quantityRangeSpecified_correctlyParsesQuantityRange() {
        String[] invalidCommandArgs = new String[]{"-q", "0..-0"};
        try {
            SearchCommand searchCommand = searchCommandParser.parse(invalidCommandArgs);
            Assertions.assertTrue(searchCommand instanceof SearchCommand);
        } catch (ParseException e) {
            Assertions.fail();
        }
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
    public void parse_rangeBounds_correctlyParsesRanges() {
        String[] invalidCommandArgs = new String[]{"-c", "15.49..15.50", "-s", "..-0", "-e", "07-07-2007..07-07-2007"};
        try {
            SearchCommand searchCommand = searchCommandParser.parse(invalidCommandArgs);
            Assertions.assertTrue(searchCommand instanceof SearchCommand);
        } catch (ParseException e) {
            Assertions.fail();
        }
    }

    @Test
    void parseRangeArgument_noRange_throwsParseExceptionWithWarningMessage() {
        String invalidRangeArgument = " .. ";
        ParseException thrown = Assertions.assertThrows(
                ParseException.class, () -> {
                    searchCommandParser.parseRangeArgument(invalidRangeArgument, "test");
                }, "ParseException was expected");
        Assertions.assertEquals(thrown.getMessage(), "Format for test option: {min}..{max}. "
                + "At least one of min or max is required.");
    }

    @Test
    void parseRangeArgument_moreThanOneRange_throwsParseExceptionWithWarningMessage() {
        String invalidRangeArgument = "a..b..c";
        ParseException thrown = Assertions.assertThrows(
                ParseException.class, () -> {
                    searchCommandParser.parseRangeArgument(invalidRangeArgument, "test");
                }, "ParseException was expected");
        Assertions.assertEquals(thrown.getMessage(), "Format for test option: {min}..{max}. "
                + "At least one of min or max is required.");
    }

    @Test
    void parseRangeArgument_invalidRange_throwsParseExceptionWithWarningMessage() {
        String invalidRangeArgument = ".....";
        ParseException thrown = Assertions.assertThrows(
                ParseException.class, () -> {
                    searchCommandParser.parseRangeArgument(invalidRangeArgument, "test");
                }, "ParseException was expected");
        Assertions.assertEquals(thrown.getMessage(), "Format for test option: {min}..{max}. "
                + "At least one of min or max is required.");
    }

    @Test
    void parseRangeArgument_multipleRanges_success() {
        String[] rangeArguments = {"..4", "0.. ", " 3...8 ", "1..6 s", "a.b.c..5"};
        String[][] expectedRanges = {
            {"", "4"}, {"0", ""}, {"3", ".8"}, {"1", "6 s"}, {"a.b.c", "5"}
        };
        for (int i = 0; i < rangeArguments.length; i += 1) {
            try {
                String[] parsedRangeArgument = searchCommandParser.parseRangeArgument(
                        rangeArguments[i], "test");
                Assertions.assertEquals(expectedRanges[i][0], parsedRangeArgument[0]);
                Assertions.assertEquals(expectedRanges[i][1], parsedRangeArgument[1]);
            } catch (ParseException e) {
                Assertions.fail();
            }
        }
    }
}
