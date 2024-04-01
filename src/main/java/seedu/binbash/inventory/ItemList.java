package seedu.binbash.inventory;

import seedu.binbash.exceptions.InvalidArgumentException;
import seedu.binbash.item.Item;
import seedu.binbash.item.OperationalItem;
import seedu.binbash.item.PerishableOperationalItem;
import seedu.binbash.item.PerishableRetailItem;
import seedu.binbash.item.RetailItem;
import seedu.binbash.command.RestockCommand;
import seedu.binbash.logger.BinBashLogger;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class ItemList {
    private static final BinBashLogger logger = new BinBashLogger(ItemList.class.getName());
    private double totalRevenue;
    private double totalCost;
    private final ArrayList<Item> itemList;
    private SearchAssistant searchAssistant;

    public ItemList() {
        this.itemList = new ArrayList<Item>();
        this.totalRevenue = 0;
        this.totalCost = 0;
        searchAssistant = new SearchAssistant();
    }

    public double getTotalRevenue() {
        double totalRevenue = 0;

        for (Item item: itemList) {
            if (item instanceof RetailItem) {
                // Downcast made only after checking if item is a RetailItem (and below) object.
                // TODO: Add an assert statement to verify code logic.
                RetailItem retailItem = (RetailItem) item;
                totalRevenue += (retailItem.getTotalUnitsSold() * retailItem.getItemSalePrice());
            }
        }

        return totalRevenue;
    }

    public double getTotalCost() {
        double totalCost = 0;

        for (Item item: itemList) {
            totalCost += (item.getTotalUnitsPurchased() * item.getItemCostPrice());
        }

        return totalCost;
    }

    public String getProfitMargin() {
        double totalCost = getTotalCost();
        double totalRevenue = getTotalRevenue();
        double netProfit = totalRevenue - totalCost;

        String output =
                String.format("Here are your metrics: " + System.lineSeparator()
                        + "\tTotal Cost: %.2f" + System.lineSeparator()
                        + "\tTotal Revenue: %.2f" + System.lineSeparator()
                        + "\tNet Profit: %.2f" + System.lineSeparator(),
                        totalCost,
                        totalRevenue,
                        netProfit);
        return output;
    }

    public SearchAssistant getSearchAssistant() {
        searchAssistant.setFoundItems(itemList);
        return searchAssistant;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public int getItemCount() {
        return itemList.size();
    }


    public String addItem(String itemType, String itemName, String itemDescription, int itemQuantity,
                          LocalDate itemExpirationDate, double itemSalePrice, double itemCostPrice, int itemThreshold) {
        Item item;
        if (itemType.equals("retail") && !itemExpirationDate.equals(LocalDate.MIN)) {
            // Perishable Retail Item
            item = new PerishableRetailItem(itemName, itemDescription, itemQuantity,
                    itemExpirationDate, itemSalePrice, itemCostPrice, itemThreshold);
        } else if (itemType.equals("retail") && itemExpirationDate.equals(LocalDate.MIN)) {
            // Non-perishable Retail Item
            item = new RetailItem(itemName, itemDescription, itemQuantity, itemSalePrice, itemCostPrice, itemThreshold);
        } else if (itemType.equals("operational") && !itemExpirationDate.equals(LocalDate.MIN)) {
            // Perishable Operational Item
            item = new PerishableOperationalItem(itemName, itemDescription, itemQuantity,
                    itemExpirationDate, itemCostPrice, itemThreshold);
        } else {
            // Non-perishable Operational Item
            item = new OperationalItem(itemName, itemDescription, itemQuantity, itemCostPrice, itemThreshold);
        }

        int beforeSize = itemList.size();
        itemList.add(item);
        assert itemList.size() == (beforeSize + 1);

        String output = "Noted! I have added the following item into your inventory:" + System.lineSeparator()
                + System.lineSeparator() + item;
        return output;
    }

    public String updateItemDataByName (String itemName, String itemDescription, int itemQuantity,
                                  LocalDate itemExpirationDate, double itemSalePrice, double itemCostPrice,
                                  int itemThreshold) throws InvalidArgumentException {
        Item item = findItemByName(itemName);

        updateItemData(item, itemDescription, itemQuantity, itemExpirationDate, itemSalePrice, itemCostPrice,
                itemThreshold);

        String output = "I have updated the your item information. Do check the following if it is correct."
                + System.lineSeparator() + System.lineSeparator() + item;
        return output;
    }

    public String updateItemDataByIndex (int index, String itemDescription, int itemQuantity,
                                  LocalDate itemExpirationDate, double itemSalePrice, double itemCostPrice,
                                  int itemThreshold) throws InvalidArgumentException {
        Item item = itemList.get(index - 1);

        updateItemData(item, itemDescription, itemQuantity, itemExpirationDate, itemSalePrice, itemCostPrice,
                itemThreshold);

        String output = "I have updated the your item information. Do check the following if it is correct."
                + System.lineSeparator() + System.lineSeparator() + item;;
        return output;
    }

    private void updateItemData(Item item, String itemDescription, int itemQuantity, LocalDate itemExpirationDate,
                                double itemSalePrice, double itemCostPrice, int itemThreshold)
            throws InvalidArgumentException {
        updateItemDescription(item, itemDescription);
        updateItemQuantity(item, itemQuantity);
        updateItemExpirationDate(item, itemExpirationDate);
        updateItemCostPrice(item, itemCostPrice);
        updateItemSalePrice(item, itemSalePrice);
        updateItemThreshold(item, itemThreshold);
    }

    public void updateItemDescription(Item item, String itemDescription) {
        if (itemDescription != null) {
            logger.info("Attempting to update item description");
            item.setItemDescription(itemDescription);
        }
    }
    public void updateItemQuantity(Item item, int itemQuantity) {
        if (itemQuantity != Integer.MIN_VALUE) {
            logger.info("Attempting to update item quantity");
            item.setItemQuantity(itemQuantity);
        }
    }

    public void updateItemExpirationDate(Item item, LocalDate itemExpirationDate) throws InvalidArgumentException {
        if (itemExpirationDate != LocalDate.MIN) {
            logger.info("Attempting to update item expiration date");
            if (item instanceof PerishableOperationalItem) {
                ((PerishableOperationalItem) item).setItemExpirationDate(itemExpirationDate);
            } else if (item instanceof PerishableRetailItem) {
                ((PerishableRetailItem) item).setItemExpirationDate(itemExpirationDate);
            } else {
                throw new InvalidArgumentException("This item is not a perishable and has no expiry date.");
            }
        }
    }
    public void updateItemSalePrice(Item item, double itemSalePrice) {

        if (itemSalePrice != Double.MIN_VALUE) {
            logger.info("Attempting to update item sale price");
            if (item instanceof RetailItem) {
                ((RetailItem) item).setItemSalePrice(itemSalePrice);
            }
        }
    }

    public void updateItemCostPrice(Item item, double itemCostPrice) {
        if (itemCostPrice != Double.MIN_VALUE) {
            logger.info("Attempting to update item cost price");
            item.setItemCostPrice(itemCostPrice);
        }
    }

    public void updateItemThreshold(Item item, int itemThreshold) {
        if (itemThreshold != Integer.MIN_VALUE) {
            logger.info("Attempting to update item threshold");
            item.setItemThreshold(itemThreshold);
        }
    }

    public Item findItemByName(String itemName) throws InvalidArgumentException {
        for (Item item : itemList) {
            if (item.getItemName().trim().equals(itemName.trim())) {
                return item;
            }
        }
        throw new InvalidArgumentException("Item with name '" + itemName + "' not found.");
    }

    public String sellOrRestockItem(String itemName, int itemQuantity, String command) {
        String output = "Sorry, I can't find the item you are looking for.";
        String alertText = "";

        for (Item item : itemList) {
            int newQuantity = item.getItemQuantity();

            if (!item.getItemName().trim().equals(itemName.trim())) {
                continue;
            }

            // Restocking item consists of (i) Updating itemQuantity, (ii) Updating totalUnitsPurchased
            if (command.trim().equals(RestockCommand.COMMAND.trim())) {
                newQuantity += itemQuantity;

                int totalUnitsPurchased = item.getTotalUnitsPurchased();
                item.setTotalUnitsPurchased(totalUnitsPurchased + itemQuantity);

            // Selling item consists of (i) Updating itemQuantity, (ii) Updating totalUnitsSold
            } else {
                newQuantity -= itemQuantity;

                // Stinky downcast?
                // I'm going off the assertion that only retail items (and its subclasses) can be sold.
                // TODO: Add an assert statement to verify code logic.
                RetailItem retailItem = (RetailItem)item;

                int itemThreshold = retailItem.getItemThreshold();
                if (newQuantity < itemThreshold) {
                    alertText = alertItemQuantity(retailItem);
                }

                int totalUnitsSold = retailItem.getTotalUnitsSold();
                retailItem.setTotalUnitsSold(totalUnitsSold + itemQuantity);
            }
            item.setItemQuantity(newQuantity);
            output = "Great! I have updated the quantity of the item for you:" + System.lineSeparator()
                    + System.lineSeparator() + item
                    + alertText;

        }
        return output;
    }

    public String alertItemQuantity(Item item) {
        item.setAlert(true);
        String output = System.lineSeparator() + System.lineSeparator() + "Oh no! Your item is running low!";

        return output;
    }


    /**
     * Deletes an item from the inventory by identifying the item using its index.
     *
     * @param index index of the item to be deleted.
     * @return the message indicating which item was deleted.
     */
    public String deleteItem(int index) {
        logger.info("Attempting to delete an item");
        int beforeSize = itemList.size();
        Item tempItem = itemList.remove(index - 1);
        assert itemList.size() == (beforeSize - 1);

        String output = "Got it! I've removed the following item:" + System.lineSeparator()
                + System.lineSeparator() + tempItem;
        logger.info("An item has been deleted");
        return output;
    }

    /**
     * Deletes an item from the inventory by identifying the item using its name.
     *
     * @param keyword the name of the item to be deleted.
     * @return the message indicating which item was deleted.
     */
    public String deleteItem(String keyword) {
        int targetIndex = -1;
        Item currentItem;
        for (int i = 0 ; i < itemList.size(); i ++) {
            currentItem = itemList.get(i);
            if (currentItem.getItemName().trim().equals(keyword)) {
                logger.info("first matching item at index " + i + " found.");
                targetIndex = i + 1;
                break;
            }
        }

        if (targetIndex == -1) {
            logger.info("No matching item was found, no item was deleted.");
            String output = "Item not found! Nothing was deleted!";
            return output;
        }

        return deleteItem(targetIndex);
    }

    /**
     * Returns a string representation of all the items in the list. Each item's string
     * representation is obtained by calling its `toString` method.
     *
     * @return A concatenated string of all item representations in the list, each on a new line.
     */
    public String printList(List<Item> itemList) {
        int index = 1;
        String output = "";

        for (Item item: itemList) {
            output += index + ". " + item.toString() + System.lineSeparator() + System.lineSeparator();
            index++;
        }

        return output;
    }

    @Override
    public String toString() {
        return itemList.toString();
    }
}
