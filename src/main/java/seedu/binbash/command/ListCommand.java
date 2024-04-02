package seedu.binbash.command;

import seedu.binbash.inventory.ItemList;
import seedu.binbash.logger.BinBashLogger;


public class ListCommand extends Command {

    public ListCommand() {
        commandLogger = new BinBashLogger(ListCommand.class.getName());
        commandLogger.info("Creating List Command...");
    }

    public boolean execute(ItemList itemList) {
        executionUiOutput = itemList.printList(itemList.getItemList());
        return true;
    }
}
