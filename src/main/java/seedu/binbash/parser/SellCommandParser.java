package seedu.binbash.parser;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.TypeHandler;
import org.jline.builtins.Completers.OptDesc;

import seedu.binbash.command.SellCommand;
import seedu.binbash.logger.BinBashLogger;

import java.util.ArrayList;

public class SellCommandParser extends DefaultParser {
    private static final BinBashLogger binBashLogger = new BinBashLogger(SellCommandParser.class.getName());
    private ArrayList<OptDesc> optionDescriptions;

    public SellCommandParser() {
        options = new Options();
        optionDescriptions = new ArrayList<>();
        new CommandOptionAdder(options, optionDescriptions)
            .addNameOption(true, "Name of item sold.")
            .addQuantityOption(true, "Units of item sold.");
    }

    public ArrayList<OptDesc> getOptionDecriptions() {
        return optionDescriptions;
    }

    public SellCommand parse(String[] commandArgs) throws ParseException {
        CommandLine commandLine = super.parse(options, commandArgs);

        String itemName = String.join(" ", commandLine.getOptionValues("name"));// Allow multiple arguments
        int sellQuantity = TypeHandler.createNumber(
                commandLine.getOptionValue("quantity")).intValue();

        binBashLogger.info("Parsing SellCommand...");

        return new SellCommand(itemName, sellQuantity);
    }
}
