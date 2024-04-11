package seedu.binbash.parser;

import seedu.binbash.command.DeleteCommand;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * Parses command line arguments for creating a DeleteCommand.
 */
public class DeleteCommandParser extends DefaultParser {

    /**
     * Creates a new DeleteCommandParser with the necessary options and option descriptions.
     */
    public DeleteCommandParser() {
        options = new Options();
        new CommandOptionAdder(options)
            .addItemNameAndIndexOptionGroup()
            .saveCommandOptionDescriptions("delete");
    }

    /**
     * Parses the command line arguments to create a DeleteCommand.
     *
     * @param commandArgs The command arguments to parse.
     * @return A DeleteCommand with the specified item name or index to delete.
     * @throws ParseException If there is an error parsing the command arguments.
     */
    public DeleteCommand parse(String[] commandArgs) throws ParseException {
        CommandLine commandLine = super.parse(options, commandArgs);

        if (commandLine.hasOption("name")) {
            String nameField = String.join(" ", commandLine.getOptionValues("name"));
            return new DeleteCommand(nameField);
        }
        int indexField = Parser.parseIntOptionValue(commandLine.getOptionValue("index"), "item index");
        if (indexField <= 0) {
            throw new ParseException("item index must be positive");
        }
        return new DeleteCommand(indexField);
    }
}
