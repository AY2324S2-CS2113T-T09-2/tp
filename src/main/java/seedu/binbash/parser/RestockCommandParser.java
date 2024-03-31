package seedu.binbash.parser;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.TypeHandler;
import org.jline.builtins.Completers.OptDesc;

import seedu.binbash.command.RestockCommand;
import seedu.binbash.logger.BinBashLogger;

import java.util.ArrayList;

public class RestockCommandParser extends DefaultParser {
    private static final BinBashLogger binBashLogger = new BinBashLogger(RestockCommandParser.class.getName());
    private ArrayList<OptDesc> optionDescriptions;

    public RestockCommandParser(ArrayList<ArrayList<OptDesc>> allCommandsOptionDescriptions) {
        options = new Options();
        optionDescriptions = new ArrayList<>();
        new CommandOptionAdder(options, optionDescriptions)
            .addNameOption(true, "Name of item.")
            .addQuantityOption(true, "Units of item to restock.");
        allCommandsOptionDescriptions.add(optionDescriptions);
    }

    public RestockCommand parse(String[] commandArgs) throws ParseException {
        CommandLine commandLine = super.parse(options, commandArgs);

        String itemName = String.join(" ", commandLine.getOptionValues("name"));// Allow multiple arguments
        int restockQuantity = TypeHandler.createNumber(
                commandLine.getOptionValue("quantity")).intValue();

        binBashLogger.info("Parsing RestockCommand...");

        return new RestockCommand(itemName, restockQuantity);
    }
}
