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

    public RestockCommandParser() {
        options = new Options();
        optionDescriptions = new ArrayList<>();
        new CommandOptionAdder(options, optionDescriptions)
            .addItemNameAndIndexOptionGroup()
            .addQuantityOption(true, "Units of item to restock.");
    }

    public ArrayList<OptDesc> getOptionDecriptions() {
        return optionDescriptions;
    }

    public RestockCommand parse(String[] commandArgs) throws ParseException {
        CommandLine commandLine = new DefaultParser().parse(options, commandArgs);
        RestockCommand restockCommand;
        String restockQuantity = commandLine.getOptionValue("quantity");
        int itemRestockQuantity = TypeHandler.createNumber(restockQuantity).intValue();

        if (commandLine.hasOption("name")) {
            String itemName = String.join(" ", commandLine.getOptionValues("name"));
            restockCommand = new RestockCommand(itemName, itemRestockQuantity);
        } else {
            int index = TypeHandler.createNumber(commandLine.getOptionValue("index")).intValue();
            restockCommand = new RestockCommand(index, itemRestockQuantity);
            restockCommand.setIsIndex();
        }

        binBashLogger.info("Parsing RestockCommand...");

        return restockCommand;
    }
}
