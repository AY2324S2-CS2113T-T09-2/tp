package seedu.binbash.parser;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.TypeHandler;
import seedu.binbash.command.RestockCommand;

public class RestockCommandParser extends DefaultParser {
    public RestockCommandParser() {
        options = new Options();
        new CommandOptionAdder(options)
            .addNameOption(true, "Name of item.")
            .addQuantityOption(true, "Units of item to restock.");
    }

    public RestockCommand parse(String[] commandArgs) throws ParseException {
        CommandLine commandLine = super.parse(options, commandArgs);

        String itemName = String.join(" ", commandLine.getOptionValues("name"));// Allow multiple arguments
        int restockQuantity = TypeHandler.createNumber(
                commandLine.getOptionValue("quantity")).intValue();

        return new RestockCommand(itemName, restockQuantity);
    }
}
