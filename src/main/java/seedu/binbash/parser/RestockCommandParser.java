package seedu.binbash.parser;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.jline.builtins.Completers.OptDesc;

import seedu.binbash.command.RestockCommand;
import seedu.binbash.logger.BinBashLogger;

import java.util.ArrayList;

/**
 * Parses command line arguments for creating a RestockCommand.
 */
public class RestockCommandParser extends DefaultParser {
    private static final BinBashLogger binBashLogger = new BinBashLogger(RestockCommandParser.class.getName());
    private ArrayList<OptDesc> optionDescriptions;

    /**
     * Creates a new RestockCommandParser with the necessary options and option descriptions.
     */
    public RestockCommandParser() {
        options = new Options();
        optionDescriptions = new ArrayList<>();
        new CommandOptionAdder(options, optionDescriptions)
            .addItemNameAndIndexOptionGroup()
            .addQuantityOption(true, "Units of item to restock.")
            .saveCommandOptionDescriptions("restock");
    }

    /**
     * Gets the option descriptions for the RestockCommandParser.
     *
     * @return The list of option descriptions.
     */
    public ArrayList<OptDesc> getOptionDecriptions() {
        return optionDescriptions;
    }

    /**
     * Parses the command line arguments to create a RestockCommand.
     *
     * @param commandArgs The command line arguments.
     * @return The parsed RestockCommand.
     * @throws ParseException If an error occurs during parsing.
     */
    public RestockCommand parse(String[] commandArgs) throws ParseException {
        CommandLine commandLine = new DefaultParser().parse(options, commandArgs);
        RestockCommand restockCommand;
        String restockQuantity = commandLine.getOptionValue("quantity");
        int itemRestockQuantity = Parser.parseIntOptionValue(restockQuantity, "restock quantity");
        if (itemRestockQuantity <= 0) {
            throw new ParseException("Please provide a positive number.");
        }

        if (commandLine.hasOption("name")) {
            String itemName = String.join(" ", commandLine.getOptionValues("name"));
            restockCommand = new RestockCommand(itemName, itemRestockQuantity);
        } else {
            int index = Parser.parseIntOptionValue(commandLine.getOptionValue("index"), "item index");
            restockCommand = new RestockCommand(index, itemRestockQuantity);
            restockCommand.setIsIndex();
        }

        binBashLogger.info("Parsing RestockCommand...");

        return restockCommand;
    }
}
