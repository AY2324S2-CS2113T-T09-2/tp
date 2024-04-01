package seedu.binbash.command;

import seedu.binbash.ItemList;
import seedu.binbash.exceptions.InvalidArgumentException;
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

    public UpdateCommand(String itemName) {
        commandLogger = new BinBashLogger(UpdateCommand.class.getName());
        this.itemName = itemName;


        commandLogger.info(String.format(
                "Creating Update Command... itemName: %s",
                itemName
        ));
    }

    public UpdateCommand(int index) {
        commandLogger = new BinBashLogger(UpdateCommand.class.getName());
        this.index = index;

        commandLogger.info(String.format(
                "Creating Update Command... index: %d",
                index
        ));
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public void setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public void setItemExpirationDate(LocalDate itemExpirationDate) {
        this.itemExpirationDate = itemExpirationDate;
    }

    public void setItemSalePrice(Double itemSalePrice) {
        this.itemSalePrice = itemSalePrice;
    }

    public void setItemCostPrice(Double itemCostPrice) {
        this.itemCostPrice = itemCostPrice;
    }

    public void setItemThreshold(Integer itemThreshold) {
        this.itemThreshold = itemThreshold;
    }

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
                executionUiOutput = itemList.updateItemDataByIndex(index, itemDescription, itemQuantity, itemExpirationDate,
                        itemSalePrice, itemCostPrice, itemThreshold);
            } catch (InvalidArgumentException e) {
                executionUiOutput = e.getMessage();
                return true;
            }
        } else {
            commandLogger.info("Update identifier is detected as an item name");
            try {
                executionUiOutput = itemList.updateItemDataByName(itemName, itemDescription, itemQuantity,
                        itemExpirationDate, itemSalePrice, itemCostPrice, itemThreshold);
            } catch (InvalidArgumentException e) {
                executionUiOutput = e.getMessage();
            }
        }
        hasToSave = true;
        return true;
    }
}


