package seedu.binbash.command;

import seedu.binbash.inventory.ItemList;
import seedu.binbash.quotes.Quotes;
import seedu.binbash.logger.BinBashLogger;

public class QuoteCommand extends Command {

    public QuoteCommand() {
        commandLogger = new BinBashLogger(QuoteCommand.class.getName());
        commandLogger.info("Creating Quote Command...");
    }

    @Override
    public boolean execute(ItemList itemList) {
        executionUiOutput = Quotes.getRandomQuote();
        return true;
    }
}
