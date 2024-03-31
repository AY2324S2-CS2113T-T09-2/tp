package seedu.binbash;

import seedu.binbash.exceptions.InvalidArgumentException;
import seedu.binbash.item.Item;
import seedu.binbash.item.PerishableOperationalItem;
import seedu.binbash.item.PerishableRetailItem;
import seedu.binbash.item.RetailItem;
import seedu.binbash.command.RestockCommand;
import seedu.binbash.logger.BinBashLogger;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.logging.Logger;
import java.util.logging.Level;

public class ItemList {
    private static final Logger ITEMLIST_LOGGER = Logger.getLogger("ItemList");
    private static final BinBashLogger logger = new BinBashLogger(ItemList.class.getName());
    private double totalRevenue;
    private double totalCost;
    private final List<Item> itemList;

    public ItemList(ArrayList<Item> itemList) {
        this.itemList = itemList;
        ITEMLIST_LOGGER.setLevel(Level.WARNING);
        this.totalRevenue = 0;
        this.totalCost = 0;
    }

    private double getTotalRevenue() {
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

    private double getTotalCost() {
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

    public List<Item> getItemList() {
        return itemList;
    }

    public int getItemCount() {
        return itemList.size();
    }

    public String addItem(String itemName, String itemDescription, int itemQuantity,
                          LocalDate itemExpirationDate, double itemSalePrice, double itemCostPrice, int itemThreshold) {
        Item item;
        if (!itemExpirationDate.equals(LocalDate.MIN)) {
            // Create perishable item
            item = new PerishableRetailItem(itemName, itemDescription, itemQuantity,
                    itemExpirationDate, itemSalePrice, itemCostPrice, itemThreshold);
        } else {
            // Create non-perishable item
            item = new RetailItem(itemName, itemDescription, itemQuantity, itemSalePrice, itemCostPrice, itemThreshold);
        }

        int beforeSize = itemList.size();
        itemList.add(item);
        assert itemList.size() == (beforeSize + 1);

        String output = "Noted! I have added the following item into your inventory:" + System.lineSeparator()
                + System.lineSeparator() + item;
        return output;
    }

    public String updateItemDataByString(String itemName, String newItemDescription,
                                 Integer newItemQuantity, LocalDate newItemExpirationDate, Double newItemSalePrice,
                                 Double newItemCostPrice, Integer newItemThreshold) {

        updateItemDescription(itemName,newItemDescription);
        updateItemQuantity(itemName, newItemQuantity);
        updateItemExpirationDate(itemName, newItemExpirationDate);
        updateItemSalePrice(itemName, newItemSalePrice);
        updateItemCostPrice(itemName, newItemCostPrice);
        updateItemThreshold(itemName, newItemThreshold);

        String output = "I have updated the your item information. Do check the following if it is correct.";

        return output;
    }

    public String updateItemDataByIndex(int index, String newItemDescription,
                                         Integer newItemQuantity, LocalDate newItemExpirationDate, Double newItemSalePrice,
                                         Double newItemCostPrice, Integer newItemThreshold) {

        updateItemDescription(index,newItemDescription);
        updateItemQuantity(index, newItemQuantity);
        updateItemExpirationDate(index, newItemExpirationDate);
        updateItemSalePrice(index, newItemSalePrice);
        updateItemCostPrice(index, newItemCostPrice);
        updateItemThreshold(index, newItemThreshold);

        String output = "I have updated the your item information. Do check the following if it is correct.";

        return output;
    }

    public void updateItemDescription(String itemName, String newItemDescription) {
        for (Item item : itemList) {
            if (item.getItemName().trim().equals(itemName.trim())) {
                logger.info("Attempting to update item description");
                item.setItemDescription(newItemDescription);
            }
        }
    }

    public void updateItemDescription(int index, String newItemDescription) {
        Item item = itemList.get(index - 1);
        logger.info("Attempting to update item description");
        item.setItemDescription(newItemDescription);
    }

    public void updateItemQuantity(String itemName, int newItemQuantity) {
        for (Item item : itemList) {
            if (!item.getItemName().trim().equals(itemName.trim())) {
                continue;
            }
            logger.info("Attempting to update item quantity");
            item.setItemQuantity(newItemQuantity);
        }
    }

    public void updateItemQuantity(int index, int newItemQuantity) {
        Item item = itemList.get(index - 1);
        logger.info("Attempting to update item quantity");
        item.setItemQuantity(newItemQuantity);
    }

    public void updateItemExpirationDate(String itemName, LocalDate newItemExpirationDate) {
        for (Item item : itemList) {
            if (!item.getItemName().trim().equals(itemName.trim())) {
                continue;
                }
            logger.info("Attempting to update item expiration date");
            if (item instanceof PerishableOperationalItem) {
                ((PerishableOperationalItem) item).setItemExpirationDate(newItemExpirationDate);
            } else if (item instanceof PerishableRetailItem) {
                ((PerishableRetailItem) item).setItemExpirationDate(newItemExpirationDate);
            }
        }
    }

    public void updateItemExpirationDate(int index, LocalDate newItemExpirationDate) {
        Item item = itemList.get(index - 1);
        logger.info("Attempting to update item expiration date");
        if (item instanceof PerishableOperationalItem) {
            ((PerishableOperationalItem) item).setItemExpirationDate(newItemExpirationDate);
        } else if (item instanceof PerishableRetailItem) {
            ((PerishableRetailItem) item).setItemExpirationDate(newItemExpirationDate);
        }
    }

    public void updateItemSalePrice(String itemName, double newItemSalePrice) {
        for (Item item : itemList) {
            if (!item.getItemName().trim().equals(itemName.trim())) {
                continue;
                }
            logger.info("Attempting to update item sale price");
            if (item instanceof RetailItem) {
                ((RetailItem) item).setItemSalePrice(newItemSalePrice);
            }
        }
    }

    public void updateItemSalePrice(int index, double newItemSalePrice) {
        Item item = itemList.get(index - 1);
        logger.info("Attempting to update item sale price");
        if (item instanceof RetailItem) {
            ((RetailItem) item).setItemSalePrice(newItemSalePrice);
        }
    }

    public void updateItemCostPrice(String itemName, double newItemCostPrice) {
        for (Item item : itemList) {
            if (!item.getItemName().trim().equals(itemName.trim())) {
                continue;
            }
            logger.info("Attempting to update item cost price");
            item.setItemCostPrice(newItemCostPrice);
        }
    }

    public void updateItemCostPrice(int index, double newItemCostPrice) {
        Item item = itemList.get(index - 1);
        logger.info("Attempting to update item quantity");
        item.setItemCostPrice(newItemCostPrice);
    }

    public void updateItemThreshold(String itemName, int newItemThreshold) {
        for (Item item : itemList) {
            if (!item.getItemName().trim().equals(itemName.trim())) {
                continue;
            }
            logger.info("Attempting to update item threshold");
            item.setItemThreshold(newItemThreshold);
        }
    }

    public void updateItemThreshold(int index, int newItemThreshold) {
        Item item = itemList.get(index - 1);
        logger.info("Attempting to update item quantity");
        item.setItemThreshold(newItemThreshold);
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
        String output = System.lineSeparator() + System.lineSeparator() + "Oh no! You're item is running low!";

        return output;
    }

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

    public String deleteItem(String keyword) {
        int targetIndex = -1;
        Item currentItem;
        for (int i = 0 ; i < itemList.size(); i ++) {
            currentItem = itemList.get(i);
            if (currentItem.getItemName().trim().equals(keyword)) {
                ITEMLIST_LOGGER.log(Level.INFO, "first matching item at index " + i + " found.");
                targetIndex = i + 1;
                break;
            }
        }

        if (targetIndex == -1) {
            String output = "Item not found! Nothing was deleted!";
            return output;
        }

        return deleteItem(targetIndex);
    }

    public String searchItem(String keyword) {
        ArrayList<Item> filteredList = (ArrayList<Item>) itemList.stream()
                .filter(item -> item.getItemName().contains(keyword))
                .collect(Collectors.toList());

        String output = "";

        if (filteredList.isEmpty()) {
            output += String.format("There are no tasks with the keyword '%s'!", keyword);
        } else {
            output = String.format("Here's a list of items that contain the keyword '%s': ", keyword)
                    + System.lineSeparator()
                    + printList(filteredList);
        }

        assert filteredList.size() > 0 && filteredList.size() <= itemList.size();
        return output;
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
