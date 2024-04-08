package seedu.binbash.command;

import seedu.binbash.inventory.ItemList;
import seedu.binbash.ui.Ui;
import seedu.binbash.logger.BinBashLogger;

public class QuoteCommand extends Command {
    private Ui ui;

    public QuoteCommand() {
        commandLogger = new BinBashLogger(QuoteCommand.class.getName());
        commandLogger.info("Creating Quote Command...");
    }

    @Override
    public boolean execute(ItemList itemList) {

        String randomMessage = ui.getRandomMessage();
        executionUiOutput = randomMessage;
        return true;
    }
    
    public void setUi(Ui ui) {
        this.ui = ui;
    }
}
