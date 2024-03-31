package seedu.binbash.command;

import seedu.binbash.ItemList;
import seedu.binbash.exceptions.InvalidArgumentException;
import seedu.binbash.item.Item;
import seedu.binbash.item.PerishableOperationalItem;
import seedu.binbash.item.PerishableRetailItem;
import seedu.binbash.item.RetailItem;
import seedu.binbash.logger.BinBashLogger;

import java.time.LocalDate;

public class UpdateCommand extends Command{
    private String itemName;
    private final String itemDescription;
    private final Integer itemQuantity;
    private final LocalDate itemExpirationDate;
    private final Double itemSalePrice;
    private final Double itemCostPrice;
    private final Integer itemThreshold;
    private int index;
    private final boolean isIndex;

    public UpdateCommand(String itemName, String itemDescription, Integer itemQuantity,
                         LocalDate itemExpirationDate, Double itemSalePrice, Double itemCostPrice,
                         Integer itemThreshold) {
        commandLogger = new BinBashLogger(UpdateCommand.class.getName());
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemQuantity = itemQuantity;
        this.itemExpirationDate = itemExpirationDate;
        this.itemSalePrice = itemSalePrice;
        this.itemCostPrice = itemCostPrice;
        this.itemThreshold = itemThreshold;
        this.isIndex = false;

        commandLogger.info(String.format(
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
        commandLogger = new BinBashLogger(UpdateCommand.class.getName());
        this.index = index;
        this.itemDescription = itemDescription;
        this.itemQuantity = itemQuantity;
        this.itemExpirationDate = itemExpirationDate;
        this.itemSalePrice = itemSalePrice;
        this.itemCostPrice = itemCostPrice;
        this.itemThreshold = itemThreshold;
        this.isIndex = true;

        commandLogger.info(String.format(
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
                commandLogger.info("Update identifier is detected as an index");
            } else {
                item = itemList.findItemByName(itemName);
                if (item == null) {
                    executionUiOutput = "Item not found!";
                    return true;
                }
            }

            String newItemDescription = getNewItemDescription(item);
            Integer newItemQuantity = getNewItemQuantity(item);
            LocalDate newItemExpirationDate = null;
            Double newItemSalePrice = null;
            Double newItemCostPrice = getNewItemCostPrice(item);
            Integer newItemThreshold = getNewItemThreshold(item);
            newItemExpirationDate = getNewItemExpirationDate(item, newItemExpirationDate);
            newItemSalePrice = getNewItemSalePrice(item, newItemSalePrice);

            if (isIndex) {
                if (index <= 0 || index > itemList.getItemCount()) {
                    executionUiOutput = "Index is out of bounds!";
                    return true;
                }
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

    private Double getNewItemSalePrice(Item item, Double newItemSalePrice) {
        if (item instanceof RetailItem) {
            newItemSalePrice = itemSalePrice != null ? itemSalePrice : ((RetailItem) item).getItemSalePrice();
        }
        return newItemSalePrice;
    }

    private LocalDate getNewItemExpirationDate(Item item, LocalDate newItemExpirationDate) {
        if (item instanceof PerishableOperationalItem) {
            newItemExpirationDate = itemExpirationDate != null
                    ? itemExpirationDate : ((PerishableOperationalItem) item).getLocalDateItemExpirationDate();
        }

        if (item instanceof PerishableRetailItem) {
            newItemExpirationDate = itemExpirationDate != null
                    ? itemExpirationDate : ((PerishableRetailItem) item).getLocalDateItemExpirationDate();
        }
        return newItemExpirationDate;
    }

    private Integer getNewItemThreshold(Item item) {
        return itemThreshold != null ? itemThreshold : item.getItemThreshold();
    }

    private Double getNewItemCostPrice(Item item) {
        return itemCostPrice != null ? itemCostPrice : item.getItemCostPrice();
    }

    private Integer getNewItemQuantity(Item item) {
        return itemQuantity != null ? itemQuantity : item.getItemQuantity();
    }

    private String getNewItemDescription(Item item) {
        return itemDescription != null ? itemDescription : item.getItemDescription();
    }
}
