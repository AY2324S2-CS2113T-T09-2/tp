package seedu.binbash.command;

import seedu.binbash.inventory.ItemList;
import seedu.binbash.item.Item;
import seedu.binbash.logger.BinBashLogger;

import java.util.ArrayList;

/**
 * Represents the execution of the delete command that will remove an item from the inventory.
 */
public class DeleteCommand extends Command {
    private String keyword;
    private int index;
    private boolean isIndex;

    /**
     * Constructs a DeleteCommand object that will delete an item using item index.
     *
     * @param index index of the item to be deleted from the inventory.
     */
    public DeleteCommand(int index) {
        this.index = index;
        isIndex = true;
        commandLogger = new BinBashLogger(DeleteCommand.class.getName());
        commandLogger.info(String.format("Creating Delete Command... ItemIndex: %d", index));
    }

    /**
     * Constructs a DeleteCommand object that will delete an item using item name.
     *
     * @param keyword a String matching the item name of the item to be deleted from the inventory.
     */
    public DeleteCommand(String keyword) {
        this.keyword = keyword;
        isIndex = false;
        commandLogger = new BinBashLogger(DeleteCommand.class.getName());
        commandLogger.info(String.format("Creating Delete Command... ItemName:", keyword));
    }

    /**
     * Executes the delete command to remove an item from the inventory.
     *
     * @param itemList the inventory where the item will be deleted from.
     * @return true if the command execution is successful.
     */
    public boolean execute(ItemList itemList) {
        if (isIndex) {
            ArrayList<Integer> itemListSortedOrder = itemList.getSortedOrder();
            if (index <= 0 || index > itemListSortedOrder.size()) {
                commandLogger.info("Index entered is out of bounds");
                executionUiOutput = "Index entered is out of bounds!";
                return true;
            }
            assert index > 0 && index <= itemListSortedOrder.size();
            commandLogger.info("Delete identifier is detected as an index");
            executionUiOutput = itemList.deleteItem(index);
        } else {
            commandLogger.info("Delete identifier is detected as an item name");
            executionUiOutput = itemList.deleteItem(keyword);
        }
        hasToSave = true;
        return true;
    }
}
