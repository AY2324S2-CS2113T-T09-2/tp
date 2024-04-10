package seedu.binbash.parser;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import seedu.binbash.command.ListCommand;
import seedu.binbash.enums.SortOptionEnum;

/**
 * Parses command line arguments for creating a ListCommand.
 */
public class ListCommandParser extends DefaultParser {

    /**
     * Creates a new ListCommandParser.
     */
    public ListCommandParser() {
        options = new Options();
        new CommandOptionAdder(options)
                .addListTypeOptionGroup()
                .saveCommandOptionDescriptions("list");
    }

    /**
     * Parses the command line arguments to create a ListCommand.
     *
     * @param commandArgs The command arguments to parse.
     * @return A ListCommand with the specified sort option, or a default ListCommand if no sort option is specified.
     * @throws ParseException If there is an error parsing the command arguments.
     */
    public ListCommand parse(String[] commandArgs) throws ParseException {
        CommandLine commandLine = super.parse(options, commandArgs);
        if (commandLine.hasOption("cost")) {
            return new ListCommand(SortOptionEnum.COST);
        }
        if (commandLine.hasOption("expiry")) {
            return new ListCommand(SortOptionEnum.EXPIRY);
        }
        if (commandLine.hasOption("sale")) {
            return new ListCommand(SortOptionEnum.SALE);
        }
        if (commandLine.hasOption("profit")) {
            return new ListCommand(SortOptionEnum.PROFIT);
        }
        return new ListCommand();
    }
}
