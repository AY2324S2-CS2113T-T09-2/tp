package seedu.binbash.command;

import seedu.binbash.ItemList;
import seedu.binbash.exceptions.InvalidArgumentException;
import seedu.binbash.item.Item;
import seedu.binbash.item.PerishableOperationalItem;
import seedu.binbash.item.PerishableRetailItem;
import seedu.binbash.item.RetailItem;

import java.time.LocalDate;
import java.util.logging.Level;

public class UpdateCommand extends Command{
    private String itemName;
    private final String itemDescription;
    private final Integer itemQuantity;
    private final LocalDate itemExpirationDate;
    private final Double itemSalePrice;
    private final Double itemCostPrice;
    private final Integer itemThreshold;
    private int index;
    private boolean isIndex;

    public UpdateCommand(String itemName, String itemDescription, Integer itemQuantity,
                         LocalDate itemExpirationDate, Double itemSalePrice, Double itemCostPrice,
                         Integer itemThreshold) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemQuantity = itemQuantity;
        this.itemExpirationDate = itemExpirationDate;
        this.itemSalePrice = itemSalePrice;
        this.itemCostPrice = itemCostPrice;
        this.itemThreshold = itemThreshold;
        this.isIndex = false;

        commandLogger.fine(String.format(
                "Creating Update Command... itemName: %s, itemDescription: %s, itemQuantity: %d, "
                        + "itemExpirationDate: %s, itemSalePrice: %f, itemCostPrice: %f, itemThreshold: %d",
                itemName,
                itemDescription,
                itemQuantity,
                itemExpirationDate,
                itemSalePrice,
                itemCostPrice,
                itemThreshold
        ));
    }

    public UpdateCommand(int index, String itemDescription, Integer itemQuantity,
                         LocalDate itemExpirationDate, Double itemSalePrice, Double itemCostPrice,
                         Integer itemThreshold) {
        this.index = index;
        this.itemDescription = itemDescription;
        this.itemQuantity = itemQuantity;
        this.itemExpirationDate = itemExpirationDate;
        this.itemSalePrice = itemSalePrice;
        this.itemCostPrice = itemCostPrice;
        this.itemThreshold = itemThreshold;
        this.isIndex = true;

        commandLogger.fine(String.format(
                "Creating Update Command... index: %d, itemDescription: %s, itemQuantity: %d, "
                        + "itemExpirationDate: %s, itemSalePrice: %f, itemCostPrice: %f, itemThreshold: %d",
                index,
                itemDescription,
                itemQuantity,
                itemExpirationDate,
                itemSalePrice,
                itemCostPrice,
                itemThreshold
        ));
    }

    @Override
    public boolean execute(ItemList itemList) {
        try {
            Item item;
            Item updatedItem;

            if (isIndex) {
                if (index <= 0 || index > itemList.getItemCount()) {
                    executionUiOutput = "Index is out of bounds!";
                    return true;
                }
                item = itemList.getItemList().get(index - 1);
                commandLogger.fine("Update identifier is detected as an index");
            } else {
                item = itemList.findItemByName(itemName);
                if (item == null) {
                    executionUiOutput = "Item not found!";
                    return true;
                }
            }

            String newItemDescription = itemDescription != null ? itemDescription : item.getItemDescription();
            Integer newItemQuantity = itemQuantity != null ? itemQuantity : item.getItemQuantity();
            LocalDate newItemExpirationDate = null;
            Double newItemSalePrice = null;
            Double newItemCostPrice = itemCostPrice != null ? itemCostPrice : item.getItemCostPrice();
            Integer newItemThreshold = itemThreshold != null ? itemThreshold : item.getItemThreshold();

            if (item instanceof PerishableOperationalItem) {
                newItemExpirationDate = itemExpirationDate != null
                        ? itemExpirationDate : ((PerishableOperationalItem) item).getLocalDateItemExpirationDate();
            }

            if (item instanceof PerishableRetailItem) {
                newItemExpirationDate = itemExpirationDate != null
                        ? itemExpirationDate : ((PerishableRetailItem) item).getLocalDateItemExpirationDate();
            }

            if (item instanceof RetailItem) {
                newItemSalePrice = itemSalePrice != null ? itemSalePrice : ((RetailItem) item).getItemSalePrice();
            }

            if (isIndex) {
                if (index <= 0 || index > itemList.getItemCount()) {
                    executionUiOutput = "Index is out of bounds!";
                    return true;
                }
                assert index > 0 && index <= itemList.getItemCount();
                executionUiOutput = itemList.updateItemDataByIndex(index, newItemDescription, newItemQuantity,
                        newItemExpirationDate, newItemSalePrice, newItemCostPrice, newItemThreshold);
                updatedItem = itemList.getItemList().get(index - 1);

            } else {
                executionUiOutput = itemList.updateItemDataByString(itemName, newItemDescription, newItemQuantity,
                        newItemExpirationDate, newItemSalePrice, newItemCostPrice, newItemThreshold);
                updatedItem = itemList.findItemByName(itemName);
            }

            executionUiOutput += System.lineSeparator() + System.lineSeparator() + updatedItem;
            hasToSave = true;
            return true;

        } catch (InvalidArgumentException e) {
            executionUiOutput = e.getMessage();
            return false;
        }
    }
}
