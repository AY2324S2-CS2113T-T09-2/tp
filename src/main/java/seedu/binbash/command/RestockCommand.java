package seedu.binbash.command;

import seedu.binbash.exceptions.InvalidCommandException;
import seedu.binbash.logger.BinBashLogger;
import seedu.binbash.inventory.ItemList;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Represents the execution of a restock command that will increase the quantity of an Item.
 */
public class RestockCommand extends Command{
    public static final String COMMAND = "restock";
    public static final Pattern COMMAND_FORMAT = Pattern.compile(
            "restock\\s+"
                    + "n/(?<itemName>.+?)(?=q/)"
                    + "q/(?<restockQuantity>.+)"
    );
    private String itemName;
    private int restockQuantity;
    private int index;
    private boolean isIndex = false;

    /**
     * Creates a new RestockCommand with the specified item name and restock quantity.
     *
     * @param itemName The name of the item to restock.
     * @param restockQuantity The quantity by which to restock the item.
     */
    public RestockCommand(String itemName, int restockQuantity) {
        this.itemName = itemName;
        this.restockQuantity = restockQuantity;

        commandLogger = new BinBashLogger(RestockCommand.class.getName());
        commandLogger.info(String.format(
                "Creating Restock Command... itemName: %s, restockQuantity: %d",
                itemName,
                restockQuantity
        ));
    }

    /**
     * Creates a new RestockCommand with the specified index and restock quantity.
     *
     * @param index The index of the item to restock.
     * @param restockQuantity The quantity by which to restock the item.
     */
    public RestockCommand(int index, int restockQuantity) {
        commandLogger = new BinBashLogger(RestockCommand.class.getName());
        this.index = index;
        this.restockQuantity = restockQuantity;

        commandLogger.info(String.format(
                "Creating Restock Command... index: %d, restockQuantity: %d",
                index,
                restockQuantity
        ));
    }

    /**
     * Marks this RestockCommand as using an index instead of an item name.
     */
    public void setIsIndex() {
        this.isIndex = true;
    }

    @Override
    public boolean execute(ItemList itemList) {
        if (isIndex) {
            ArrayList<Integer> itemListSortedOrder = itemList.getSortedOrder();
            if (index <= 0 || index > itemListSortedOrder.size()) {
                commandLogger.info("Index entered is out of bounds");
                executionUiOutput = "Index entered is out of bounds!";
                return true;
            }
            assert index > 0 && index <= itemListSortedOrder.size();
            commandLogger.info("Restock identifier is detected as an index");
            try {
                executionUiOutput = itemList.sellOrRestockItem(index, restockQuantity, COMMAND);
            } catch (InvalidCommandException e) {
                executionUiOutput = e.getMessage();
            }
        } else {
            commandLogger.info("Restock identifier is detected as an item name");
            try {
                executionUiOutput = itemList.sellOrRestockItem(itemName, restockQuantity, COMMAND);
            } catch (InvalidCommandException e) {
                executionUiOutput = e.getMessage();
            }
        }
        hasToSave = true;
        return true;
    }
}
