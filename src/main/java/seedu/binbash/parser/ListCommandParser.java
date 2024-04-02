package seedu.binbash.parser;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import seedu.binbash.command.ListCommand;
import seedu.binbash.enums.SortOptionEnum;

public class ListCommandParser extends DefaultParser {
    public ListCommandParser() {
        options = new Options();
        new CommandOptionAdder(options)
                .addListTypeOptionGroup();
    }

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
