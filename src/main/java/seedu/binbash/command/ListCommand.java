package seedu.binbash.command;

import seedu.binbash.inventory.ItemList;
import seedu.binbash.logger.BinBashLogger;


public class ListCommand extends Command {
    private String sortType;
    public ListCommand() {
        commandLogger = new BinBashLogger(ListCommand.class.getName());
        commandLogger.info("Creating List Command...");
        this.sortType =  "none";
    }

    public ListCommand(String sortType) {
        commandLogger = new BinBashLogger(ListCommand.class.getName());
        commandLogger.info("Creating List Command with sort type of expiry date...");
        this.sortType = sortType;
    }

    public boolean execute(ItemList itemList) {
        if (sortType.equals("e")) {
            executionUiOutput = itemList.printListSortedByExpiryDate(itemList.getItemList());
        } else {
            executionUiOutput = itemList.printList(itemList.getItemList());
        }

        return true;
    }
}
