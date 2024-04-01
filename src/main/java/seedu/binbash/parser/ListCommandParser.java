package seedu.binbash.parser;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import seedu.binbash.command.ListCommand;

public class ListCommandParser extends DefaultParser {
    public ListCommandParser() {
        options = new Options();
        new CommandOptionAdder(options)
                .addListTypeOptionGroup();
    }

    public ListCommand parse(String[] commandArgs) throws ParseException {
        CommandLine commandLine = super.parse(options, commandArgs);
        if (commandLine.hasOption("cost")) {
            return new ListCommand("c");
        } else if (commandLine.hasOption("expiry")) {
            return new ListCommand("e");
        }  else if (commandLine.hasOption("sale")) {
            return new ListCommand("s");
        } else {
            return new ListCommand();
        }
    }
}
