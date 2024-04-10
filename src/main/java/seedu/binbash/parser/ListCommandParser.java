package seedu.binbash.parser;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.jline.builtins.Completers.OptDesc;

import seedu.binbash.command.ListCommand;
import seedu.binbash.enums.SortOptionEnum;

import java.util.ArrayList;

/**
 * Parses command line arguments for creating a ListCommand.
 */
public class ListCommandParser extends DefaultParser {
    private ArrayList<OptDesc> optionDescriptions;

    /**
     * Creates a new ListCommandParser.
     */
    public ListCommandParser() {
        options = new Options();
        optionDescriptions = new ArrayList<>();
        new CommandOptionAdder(options, optionDescriptions)
                .addListTypeOptionGroup()
                .saveCommandOptionDescriptions("list");
    }

    /**
     * Gets the option descriptions for the list command.
     *
     * @return The list of option descriptions.
     */
    public ArrayList<OptDesc> getOptionDecriptions() {
        return optionDescriptions;
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
        } else if (commandLine.hasOption("expiry")) {
            return new ListCommand(SortOptionEnum.EXPIRY);
        }  else if (commandLine.hasOption("sale")) {
            return new ListCommand(SortOptionEnum.SALE);
        } else if (commandLine.hasOption("profit")) {
            return new ListCommand(SortOptionEnum.PROFIT);
        } else {
            return new ListCommand();
        }
    }
}
