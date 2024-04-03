package seedu.binbash.command;

import seedu.binbash.exceptions.InvalidCommandException;
import seedu.binbash.inventory.ItemList;
import seedu.binbash.logger.BinBashLogger;

import java.time.LocalDate;

public class UpdateCommand extends Command {
    private String itemName;
    private int index;
    private String itemDescription = null;
    private int itemQuantity = Integer.MIN_VALUE;
    private LocalDate itemExpirationDate = LocalDate.MIN;
    private double itemSalePrice = Double.MIN_VALUE;
    private double itemCostPrice = Double.MIN_VALUE;
    private int itemThreshold = Integer.MIN_VALUE;
    private boolean isIndex = false;

    /**
     * Creates an update command, to update by item name.
     *
     * @param itemName The name of the item to be updated.
     */
    public UpdateCommand(String itemName) {
        commandLogger = new BinBashLogger(UpdateCommand.class.getName());
        this.itemName = itemName;


        commandLogger.info(String.format(
                "Creating Update Command... itemName: %s",
                itemName
        ));
    }

    /**
     * Creates an update command, to update by item index (in the ItemList).
     *
     * @param index The index of the item to be updated.
     */
    public UpdateCommand(int index) {
        commandLogger = new BinBashLogger(UpdateCommand.class.getName());
        this.index = index;

        commandLogger.info(String.format(
                "Creating Update Command... index: %d",
                index
        ));
    }

    /**
     * Sets the new description of the item.
     *
     * @param itemDescription The new description of the item.
     */
    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    /**
     * Sets the new quantity of the item.
     *
     * @param itemQuantity The new quantity of the item.
     */
    public void setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    /**
     * Sets the new expiration date of the item.
     *
     * @param itemExpirationDate The new expiration date of the item.
     */
    public void setItemExpirationDate(LocalDate itemExpirationDate) {
        this.itemExpirationDate = itemExpirationDate;
    }

    /**
     * Sets the new sale price of the item.
     *
     * @param itemSalePrice The new sale price of the item.
     */
    public void setItemSalePrice(Double itemSalePrice) {
        this.itemSalePrice = itemSalePrice;
    }

    /**
     * Sets the new cost price of the item.
     *
     * @param itemCostPrice The new cost price of the item.
     */
    public void setItemCostPrice(Double itemCostPrice) {
        this.itemCostPrice = itemCostPrice;
    }

    /**
     * Sets the new quantity threshold of the item.
     *
     * @param itemThreshold The new quantity threshold of the item.
     */
    public void setItemThreshold(Integer itemThreshold) {
        this.itemThreshold = itemThreshold;
    }

    /**
     * Sets the isIndex value to true.
     * isIndex is used to denote whether the item is being updated by index.
     */
    public void setIsIndex() {
        this.isIndex = true;
    }

    @Override
    public boolean execute(ItemList itemList) {
        if (isIndex) {
            if (index <= 0 || index > itemList.getItemCount()) {
                commandLogger.info("Index entered is out of bounds");
                executionUiOutput = "Index entered is out of bounds!";
                return true;
            }
            assert index > 0 && index <= itemList.getItemCount();
            commandLogger.info("Delete identifier is detected as an index");
            try {
                executionUiOutput = itemList.updateItemDataByIndex(index, itemDescription, itemQuantity,
                        itemExpirationDate, itemSalePrice, itemCostPrice, itemThreshold);
            } catch (InvalidCommandException e) {
                executionUiOutput = e.getMessage();
                return true;
            }
        } else {
            commandLogger.info("Update identifier is detected as an item name");
            try {
                executionUiOutput = itemList.updateItemDataByName(itemName, itemDescription, itemQuantity,
                        itemExpirationDate, itemSalePrice, itemCostPrice, itemThreshold);
            } catch (InvalidCommandException e) {
                executionUiOutput = e.getMessage();
            }
        }
        hasToSave = true;
        return true;
    }
}


