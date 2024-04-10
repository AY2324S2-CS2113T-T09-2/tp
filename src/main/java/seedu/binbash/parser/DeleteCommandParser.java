package seedu.binbash.parser;

import seedu.binbash.command.DeleteCommand;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.TypeHandler;
import org.jline.builtins.Completers.OptDesc;

import java.util.ArrayList;

/**
 * Parses command line arguments for creating a DeleteCommand.
 */
public class DeleteCommandParser extends DefaultParser {
    private ArrayList<OptDesc> optionDescriptions;

    /**
     * Creates a new DeleteCommandParser with the necessary options and option descriptions.
     */
    public DeleteCommandParser() {
        options = new Options();
        optionDescriptions = new ArrayList<>();
        new CommandOptionAdder(options, optionDescriptions)
            .addItemNameAndIndexOptionGroup()
            .saveCommandOptionDescriptions("delete");
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

        int indexField = TypeHandler.createNumber(
                commandLine.getOptionValue("index")).intValue();
        return new DeleteCommand(indexField);
    }
}
