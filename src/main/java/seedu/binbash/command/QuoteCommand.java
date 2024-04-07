package seedu.binbash.command;

import seedu.binbash.inventory.ItemList;
import seedu.binbash.ui.Ui;
import seedu.binbash.logger.BinBashLogger;

public class QuoteCommand extends Command {
    private Ui ui;

    public QuoteCommand() {
        commandLogger = new BinBashLogger(QuoteCommand.class.getName());
        commandLogger.info("Creating Quotes Command...");
    }

    @Override
    public boolean execute(ItemList itemList) {
        if (ui == null) {
            throw new IllegalStateException("Ui is not initialized. Please initialize it before calling execute()");
        }

        String randomMessage = ui.getRandomMessage();
        executionUiOutput = randomMessage;
        hasToSave = false; // Assuming you don't need to save after executing the Quotes command
        return true;
    }

    // Setter method to initialize Ui
    public void setUi(Ui ui) {
        this.ui = ui;
    }
}
