package seedu.binbash.parser;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.jline.builtins.Completers.OptDesc;

import seedu.binbash.command.ListCommand;
import seedu.binbash.enums.SortOptionEnum;

import java.util.ArrayList;

public class ListCommandParser extends DefaultParser {
    private ArrayList<OptDesc> optionDescriptions;

    public ListCommandParser() {
        options = new Options();
        optionDescriptions = new ArrayList<>();
        new CommandOptionAdder(options, optionDescriptions)
                .addListTypeOptionGroup();
    }

    public ArrayList<OptDesc> getOptionDecriptions() {
        return optionDescriptions;
    }

    public ListCommand parse(String[] commandArgs) throws ParseException {
        CommandLine commandLine = super.parse(options, commandArgs);
        if (commandLine.hasOption("cost")) {
            return new ListCommand(SortOptionEnum.COST);
        } else if (commandLine.hasOption("expiry")) {
            return new ListCommand(SortOptionEnum.EXPIRY);
        }  else if (commandLine.hasOption("sale")) {
            return new ListCommand(SortOptionEnum.SALE);
        } else {
            return new ListCommand();
        }
    }
}
