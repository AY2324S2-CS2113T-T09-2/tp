package seedu.binbash.command;

import seedu.binbash.exceptions.InvalidCommandException;
import seedu.binbash.logger.BinBashLogger;
import seedu.binbash.inventory.ItemList;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Represents the execution of a sell command that will decrease the quantity of an Item.
 */
public class SellCommand extends Command{
    public static final String COMMAND = "sell";
    private String itemName;
    private final int sellQuantity;
    private int index;
    private boolean isIndex;

    /**
     * Creates a new SellCommand with the specified item name and sell quantity.
     *
     * @param itemName The name of the item to sell.
     * @param sellQuantity The quantity by which to sell the item.
     */
    public SellCommand(String itemName, int sellQuantity) {
        this.itemName = itemName;
        this.sellQuantity = sellQuantity;

        commandLogger=  new BinBashLogger(SellCommand.class.getName());
        commandLogger.info(String.format(
                "Creating Sell Command... itemName: %s, sellQuantity: %d",
                itemName,
                sellQuantity
        ));
    }

    /**
     * Creates a new SellCommand with the specified index and sell quantity.
     *
     * @param index The index of the item to sell.
     * @param sellQuantity The quantity by which to sell the item.
     */
    public SellCommand(int index, int sellQuantity) {
        commandLogger = new BinBashLogger(SellCommand.class.getName());
        this.index = index;
        this.sellQuantity = sellQuantity;

        commandLogger.info(String.format(
                "Creating Sell Command... index: %d, sellQuantity: %d",
                index,
                sellQuantity
        ));
    }

    /**
     * Marks this SellCommand as using an index instead of an item name.
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
            commandLogger.info("Sell identifier is detected as an index");
            try {
                executionUiOutput = itemList.sellOrRestockItem(index, sellQuantity, COMMAND);
            } catch (InvalidCommandException e) {
                executionUiOutput = e.getMessage();
            }
        } else {
            commandLogger.info("Sell identifier is detected as an item name");
            try {
                executionUiOutput = itemList.sellOrRestockItem(itemName, sellQuantity, COMMAND);
            } catch (InvalidCommandException e) {
                executionUiOutput = e.getMessage();
            }
        }
        hasToSave = true;
        return true;
    }
}
