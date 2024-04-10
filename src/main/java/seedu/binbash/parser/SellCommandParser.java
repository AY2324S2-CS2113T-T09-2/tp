package seedu.binbash.parser;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.jline.builtins.Completers.OptDesc;

import seedu.binbash.command.SellCommand;
import seedu.binbash.logger.BinBashLogger;

import java.util.ArrayList;


/**
 * Parses command line arguments for creating a SellCommand.
 */
public class SellCommandParser extends DefaultParser {
    private static final BinBashLogger binBashLogger = new BinBashLogger(SellCommandParser.class.getName());
    private ArrayList<OptDesc> optionDescriptions;


    /**
     * Creates a new SellCommandParser with the necessary options and option descriptions.
     */
    public SellCommandParser() {
        options = new Options();
        optionDescriptions = new ArrayList<>();
        new CommandOptionAdder(options, optionDescriptions)
            .addItemNameAndIndexOptionGroup()
            .addQuantityOption(true, "Units of item sold.")
            .saveCommandOptionDescriptions("sell");
    }


    /**
     * Gets the option descriptions for the SellCommandParser.
     *
     * @return The list of option descriptions.
     */
    public ArrayList<OptDesc> getOptionDecriptions() {
        return optionDescriptions;
    }


    /**
     * Parses the command line arguments to create a SellCommand.
     *
     * @param commandArgs The command line arguments.
     * @return The parsed SellCommand.
     * @throws ParseException If an error occurs during parsing.
     */
    public SellCommand parse(String[] commandArgs) throws ParseException {
        CommandLine commandLine = new DefaultParser().parse(options, commandArgs);
        SellCommand sellCommand;
        String sellQuantity = commandLine.getOptionValue("quantity");
        int itemSellQuantity = Parser.parseIntOptionValue(sellQuantity, "sell quantity");
        if (itemSellQuantity <= 0) {
            throw new ParseException("Please provide a positive number.");
        }

        if (commandLine.hasOption("name")) {
            String itemName = String.join(" ", commandLine.getOptionValues("name"));
            sellCommand = new SellCommand(itemName, itemSellQuantity);
        } else {
            int index = Parser.parseIntOptionValue(commandLine.getOptionValue("index"), "item index");
            sellCommand = new SellCommand(index, itemSellQuantity);
            sellCommand.setIsIndex();
        }

        binBashLogger.info("Parsing RestockCommand...");

        return sellCommand;
    }
}
