package seedu.binbash.parser;

import seedu.binbash.command.DeleteCommand;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.TypeHandler;
import org.jline.builtins.Completers.OptDesc;

import java.util.ArrayList;

public class DeleteCommandParser extends DefaultParser {
    private ArrayList<OptDesc> optionDescriptions;

    public DeleteCommandParser() {
        options = new Options();
        optionDescriptions = new ArrayList<>();
        new CommandOptionAdder(options, optionDescriptions)
            .addItemNameAndIndexOptionGroup();
    }

    public ArrayList<OptDesc> getOptionDecriptions() {
        return optionDescriptions;
    }

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
