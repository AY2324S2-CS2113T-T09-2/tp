package seedu.binbash.command;

import java.util.regex.Pattern;
import java.util.logging.Level;
import seedu.binbash.ItemList;
import seedu.binbash.logger.BinBashLogger;

/**
 * Represents the execution of the delete command that will remove an item from the inventory.
 */
public class DeleteCommand extends Command {
    public static final Pattern COMMAND_FORMAT = Pattern.compile("delete\\s(?<identifier>.+)");
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
        commandLogger.info(String.format(
                "Creating Delete Command... ItemIndex: %d",
                index
        ));
    }

    /**
     * Construsts a DeleteCommand object that will delete an item using item name.
     *
     * @param keyword a String matching the item name of the item to be deleted from the inventory.
     */
    public DeleteCommand(String keyword) {
        this.keyword = keyword;
        isIndex = false;
        commandLogger.info(String.format(
                "Creating Delete Command... ItemIndex: %d",
                index
        ));
    }

    /**
     * Executes the delete command to remove an item from the inventory.
     *
     * @param itemList the inventory where the item will be deleted from.
     * @return true if the command execution is successful, else false
     */
    public boolean execute(ItemList itemList) {
        if (isIndex) {
            if (index <= 0 || index > itemList.getItemCount()) {
                executionUiOutput = "Index is out of bounds!";
                return true;
            }
            assert index > 0 && index <= itemList.getItemCount();
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
