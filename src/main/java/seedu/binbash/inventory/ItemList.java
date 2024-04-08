package seedu.binbash.inventory;

import seedu.binbash.command.SellCommand;
import seedu.binbash.comparators.ItemComparatorByCostPrice;
import seedu.binbash.comparators.ItemComparatorByExpiryDate;
import seedu.binbash.comparators.ItemComparatorByProfit;
import seedu.binbash.comparators.ItemComparatorBySalePrice;
import seedu.binbash.exceptions.InvalidCommandException;
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

import static java.util.stream.Collectors.toList;

/**
 * Represents a list of items in the inventory. This class provides methods to manage and interact
 * with the inventory, such as adding, updating, and deleting items, as well as calculating financial
 * metrics like total cost and revenue.
 */
public class ItemList {
    private static final BinBashLogger logger = new BinBashLogger(ItemList.class.getName());
    private double totalRevenue;
    private double totalCost;
    private final ArrayList<Item> itemList;
    private ArrayList<Integer> sortedOrder;
    private SearchAssistant searchAssistant;

    public ItemList(ArrayList<Item> itemList) {
        this.itemList = itemList;
        this.sortedOrder = initializeSortedOrder(itemList);
        this.totalRevenue = 0;
        this.totalCost = 0;
        searchAssistant = new SearchAssistant();
    }

    /**
     * Calculates and returns the total revenue generated from all retail items in the inventory.
     *
     * @return the total revenue.
     */
    public double getTotalRevenue() {
        double totalRevenue = 0;

        for (Item item: itemList) {
            if (item instanceof RetailItem) {
                RetailItem retailItem = (RetailItem) item;
                totalRevenue += (retailItem.getTotalUnitsSold() * retailItem.getItemSalePrice());
            }
        }

        return totalRevenue;
    }

    /**
     * Calculates and returns the total cost incurred from purchasing all items in the inventory.
     *
     * @return the total cost.
     */
    public double getTotalCost() {
        double totalCost = 0;

        for (Item item: itemList) {
            totalCost += (item.getTotalUnitsPurchased() * item.getItemCostPrice());
        }

        return totalCost;
    }

    /**
     * Returns a formatted string displaying the total cost, total revenue, and net profit.
     *
     * @return the profit margin metrics as a string.
     */
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

    /**
     * Returns the SearchAssistant object bound to the ItemList instance.
     *
     * @return The SearchAssistant object bound to this ItemList instance.
     */
    public SearchAssistant getSearchAssistant() {
        searchAssistant.setFoundItems(itemList);
        return searchAssistant;
    }

    /**
     * Returns the List that contains the Items stored in the ItemList.
     *
     * @return A List containing Items stored in the ItemList.
     */
    public List<Item> getItemList() {
        return itemList;
    }

    /**
     * Returns the number of Items in the ItemList.
     *
     * @return The number of Items in the ItemList.
     */
    public int getItemCount() {
        return itemList.size();
    }

    /**
     * Returns the number of Items in the sorted ItemList.
     *
     * @return the number of Items in the sorted ItemList.
     */
    public int getSortedItemCount() {
        return sortedOrder.size();
    }

    /**
     * Returns the ArrayList containing the sorted indexes of items in the ItemList.
     *
     * @return the ArrayList containing the sorted indexes of items in the ItemList.
     */
    public ArrayList<Integer> getSortedOrder() {
        return sortedOrder;
    }

    /**
     * Adds an Item to the ItemList.
     *
     * @param itemType A String denoting the type of Item to be added. Can be "retail" or "operational".
     * @param itemName The name of the Item to be added.
     * @param itemDescription The description of the Item to be added.
     * @param itemQuantity The quantity of the Item to be added.
     * @param itemExpirationDate The expiration date of the Item to be added.
     * @param itemSalePrice The sale price of the Item to be added.
     * @param itemCostPrice The cost price of the Item to be added.
     * @param itemThreshold The quantity threshold of the Item to be added.
     * @return The result String to indicate that a new Item was added.
     */
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
        sortedOrder.add(beforeSize);
        itemList.add(item);
        assert itemList.size() == (beforeSize + 1);
        assert sortedOrder.size() == (beforeSize + 1);

        String output = "Noted! I have added the following item into your inventory:" + System.lineSeparator()
                + System.lineSeparator() + item;
        return output;
    }

    /**
     * Updates an Item in the ItemList by its name.
     *
     * @param itemName The name of the item to be updated.
     * @param itemDescription The new description of the item to be updated.
     * @param itemQuantity The new quantity of the item to be updated.
     * @param itemExpirationDate The new expiration date of the item to be updated.
     * @param itemSalePrice The new sale price of the item to be updated.
     * @param itemCostPrice The new cost price of the item to be updated.
     * @param itemThreshold The new quantity threshold of the item to be updated.
     * @return The result String indicating that the given item was updated.
     * @throws InvalidCommandException If invalid parameters are provided.
     */
    public String updateItemDataByName (String itemName, String itemDescription, int itemQuantity,
                                  LocalDate itemExpirationDate, double itemSalePrice, double itemCostPrice,
                                  int itemThreshold) throws InvalidCommandException {
        Item item = findItemByName(itemName);

        updateItemData(item, itemDescription, itemQuantity, itemExpirationDate, itemSalePrice, itemCostPrice,
                itemThreshold);

        String output = "I have updated your item information. Do check the following if it is correct."
                + System.lineSeparator() + System.lineSeparator() + item;
        return output;
    }

    /**
     * Updates an Item in the ItemList by its index in the inner List.
     *
     * @param index The index of the Item in the inner List.
     * @param itemDescription The new description of the item to be updated.
     * @param itemQuantity The new quantity of the item to be updated.
     * @param itemExpirationDate The new expiration date of the item to be updated.
     * @param itemSalePrice The new sale price of the item to be updated.
     * @param itemCostPrice The new cost price of the item to be updated.
     * @param itemThreshold The new quantity threshold of the item to be updated.
     * @return The result String indicating that the given item was updated.
     * @throws InvalidCommandException If invalid parameters are provided.
     */
    public String updateItemDataByIndex (int index, String itemDescription, int itemQuantity,
                                  LocalDate itemExpirationDate, double itemSalePrice, double itemCostPrice,
                                  int itemThreshold) throws InvalidCommandException {
        //Item item = itemList.get(index - 1);
        Item item = itemList.get(sortedOrder.get(index - 1));

        updateItemData(item, itemDescription, itemQuantity, itemExpirationDate, itemSalePrice, itemCostPrice,
                itemThreshold);

        String output = "I have updated the your item information. Do check the following if it is correct."
                + System.lineSeparator() + System.lineSeparator() + item;
        return output;
    }

    /**
     * Private helper method to update an Item with the provided parameters.
     * Calls other private helpers to update individual attributes.
     */
    private void updateItemData(Item item, String itemDescription, int itemQuantity, LocalDate itemExpirationDate,
                                double itemSalePrice, double itemCostPrice, int itemThreshold)
            throws InvalidCommandException {
        updateItemDescription(item, itemDescription);
        updateItemQuantity(item, itemQuantity);
        updateItemExpirationDate(item, itemExpirationDate);
        updateItemCostPrice(item, itemCostPrice);
        updateItemSalePrice(item, itemSalePrice);
        updateItemThreshold(item, itemThreshold);
    }

    private void updateItemDescription(Item item, String itemDescription) {
        if (itemDescription != null) {
            logger.info("Attempting to update item description");
            item.setItemDescription(itemDescription);
        }
    }
    private void updateItemQuantity(Item item, int itemQuantity) {
        if (itemQuantity != Integer.MIN_VALUE) {
            logger.info("Attempting to update item quantity");
            item.setItemQuantity(itemQuantity);
        }
    }

    private void updateItemExpirationDate(Item item, LocalDate itemExpirationDate) throws InvalidCommandException {
        if (itemExpirationDate != LocalDate.MIN) {
            logger.info("Attempting to update item expiration date");
            if (item instanceof PerishableOperationalItem) {
                ((PerishableOperationalItem) item).setItemExpirationDate(itemExpirationDate);
            } else if (item instanceof PerishableRetailItem) {
                ((PerishableRetailItem) item).setItemExpirationDate(itemExpirationDate);
            } else {
                throw new InvalidCommandException("This item is not a perishable and has no expiry date.");
            }
        }
    }
    private void updateItemSalePrice(Item item, double itemSalePrice) {

        if (itemSalePrice != Double.MIN_VALUE) {
            logger.info("Attempting to update item sale price");
            if (item instanceof RetailItem) {
                ((RetailItem) item).setItemSalePrice(itemSalePrice);
            }
        }
    }

    private void updateItemCostPrice(Item item, double itemCostPrice) {
        if (itemCostPrice != Double.MIN_VALUE) {
            logger.info("Attempting to update item cost price");
            item.setItemCostPrice(itemCostPrice);
        }
    }

    private void updateItemThreshold(Item item, int itemThreshold) {
        if (itemThreshold != Integer.MIN_VALUE) {
            logger.info("Attempting to update item threshold");
            item.setItemThreshold(itemThreshold);
        }
    }

    /**
     * Finds an item in the sorted item list by its name.
     *
     * @param itemName The name of the Item to be found.
     * @return The Item with the given name.
     * @throws InvalidCommandException If an Item with the provided name does not exist in the ItemList.
     */
    public Item findItemByName(String itemName) throws InvalidCommandException {
        Item currentItem;
        for (int i = 0; i < sortedOrder.size(); i++) {
            currentItem = itemList.get(sortedOrder.get(i));
            if (currentItem.getItemName().trim().equals(itemName)) {
                return currentItem;
            }
        }
        throw new InvalidCommandException("Item with name '" + itemName + "' not found.");
    }

    private String sellOrRestock(Item item, int quantityToUpdateBy, String command) throws InvalidCommandException {
        String alertText = "";
        int currentQuantity = item.getItemQuantity();
        switch (command) {
        case RestockCommand.COMMAND:
            currentQuantity += quantityToUpdateBy;
            item.setItemQuantity(currentQuantity);

            int totalUnitsPurchased = item.getTotalUnitsPurchased();
            item.setTotalUnitsPurchased(totalUnitsPurchased + quantityToUpdateBy);

            break;
        case SellCommand.COMMAND:
            if (quantityToUpdateBy > currentQuantity) {
                throw new InvalidCommandException("You do not have enough to sell the stated quantity.");
            }
            currentQuantity -= quantityToUpdateBy;
            item.setItemQuantity(currentQuantity);

            RetailItem retailItem = (RetailItem)item;
            int itemThreshold = retailItem.getItemThreshold();

            if (currentQuantity < itemThreshold) {
                alertText = alertItemQuantity(retailItem);
            }

            int totalUnitsSold = retailItem.getTotalUnitsSold();
            retailItem.setTotalUnitsSold(totalUnitsSold + quantityToUpdateBy);
            break;
        default:
            throw new InvalidCommandException("Invalid argument!");
        }

        String output = "Great! I have updated the quantity of the item for you:" + System.lineSeparator()
                + System.lineSeparator() + item
                + alertText;

        return output;
    }

    /**
     * Sells or Restocks an Item.
     *
     * @param itemName The name of the Item to be sold/restocked.
     * @param itemQuantity The quantity to be sold/restocked.
     * @param command A string representing the type of operation to be done.
     * @return A String showing the result of the sell/restock operation.
     * @throws InvalidCommandException If provided item quantity is invalid (out of bounds).
     */
    public String sellOrRestockItem(String itemName, int itemQuantity, String command) throws InvalidCommandException{
        Item item = findItemByName(itemName);
        String output = sellOrRestock(item, itemQuantity, command);
        return output;
    }

    /**
     * Sells or Restocks an Item.
     *
     * @param index The index of the Item to be sold/restocked.
     * @param itemQuantity The quantity to be sold/restocked.
     * @param command A string representing the type of operation to be done.
     * @return A String showing the result of the sell/restock operation.
     * @throws InvalidCommandException If provided item quantity is invalid (out of bounds).
     */
    public String sellOrRestockItem(int index, int itemQuantity, String command) throws InvalidCommandException {
        Item item = itemList.get(sortedOrder.get(index - 1));
        return sellOrRestock(item, itemQuantity, command);
    }


    /**
     * Generates an alert that the quantity for an Item has fallen below its given threshold.
     *
     * @param item The Item to be flagged out.
     * @return A String result which indicates that the Item's quantity has fallen below its threshold.
     */
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
        int temp;

        if (sortedOrder.get(index - 1) <= -1) {
            return "Item has already been deleted!";
        }

        Item tempItem = itemList.remove((sortedOrder.get(index - 1).intValue()));
        assert itemList.size() == (beforeSize - 1);

        temp = sortedOrder.get(index - 1);
        sortedOrder.set(index - 1, -1);

        for (int i = 0; i < sortedOrder.size(); i++){
            if (sortedOrder.get(i) > temp) {
                sortedOrder.set(i, sortedOrder.get(i) - 1);
            }
        }

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

        for (int i = 0; i < sortedOrder.size(); i ++) {
            if (sortedOrder.get(i) == -1) {
                continue;
            }

            currentItem = itemList.get(sortedOrder.get(i));
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

        sortedOrder.clear();
        for (Item item: itemList) {
            sortedOrder.add(index - 1);
            output += index + ". " + item.toString() + System.lineSeparator() + System.lineSeparator();
            index++;
        }

        return output;
    }

    /**
     * Returns a string representation of all the items in the list sorted by item cost price. Each item's string
     * representation is obtained by calling its 'toString' method.
     *
     * @param itemList the inventory to print.
     * @return A concatenated string of all item representations in the sorted list, each on a new line.
     */
    public String printListSortedByCostPrice(List<Item> itemList) {
        int index = 1;
        String output = "";

        logger.info("Sorting inventory by expiry date...");
        ArrayList<Item> sortedList = (ArrayList<Item>) itemList.stream()
                .sorted(new ItemComparatorByCostPrice())
                .collect(toList());

        logger.info("Updating sortedOrder...");
        updateSortedOrder(itemList, sortedList);

        assert sortedOrder.size() == sortedList.size();

        logger.info("Printing sorted list...");
        for (Item item: sortedList) {
            output += index + ". " + item.toString() + System.lineSeparator() + System.lineSeparator();
            index++;
        }

        return output;
    }

    /**
     * Returns a string representation of all the items in the list sorted by item expiry date. Each item's string
     * representation is obtained by calling its 'toString' method.
     *
     * @param itemList the inventory to print.
     * @return A concatenated string of all item representations in the sorted list, each on a new line.
     */
    public String printListSortedByExpiryDate(List<Item> itemList) {
        int index = 1;
        String output = "";

        logger.info("Sorting inventory by expiry date...");
        ArrayList<Item> sortedList = (ArrayList<Item>) itemList.stream()
                .filter((t) -> t instanceof PerishableOperationalItem || t instanceof PerishableRetailItem)
                .sorted(new ItemComparatorByExpiryDate())
                .collect(toList());

        logger.info("Updating sortedOrder...");
        updateSortedOrder(itemList, sortedList);

        assert sortedOrder.size() == sortedList.size();

        logger.info("Printing sorted list...");
        for (Item item: sortedList) {
            output += index + ". " + item.toString() + System.lineSeparator() + System.lineSeparator();
            index++;
        }

        return output;
    }

    /**
     * Returns a string representation of all the items in the list sorted by item sale price. Each item's string
     * representation is obtained by calling its 'toString' method.
     *
     * @param itemList the inventory to print.
     * @return A concatenated string of all item representations in the sorted list, each on a new line.
     */
    public String printListSortedBySalePrice(List<Item> itemList) {
        int index = 1;
        String output = "";

        logger.info("Sorting inventory by sale price...");
        ArrayList<Item> sortedList = (ArrayList<Item>) itemList.stream()
                .filter((t) -> t instanceof RetailItem)
                .sorted(new ItemComparatorBySalePrice())
                .collect(toList());

        logger.info("Updating sortedOrder...");
        updateSortedOrder(itemList, sortedList);

        assert sortedOrder.size() == sortedList.size();

        logger.info("Printing sorted list...");
        for (Item item: sortedList) {
            output += index + ". " + item.toString() + System.lineSeparator() + System.lineSeparator();
            index++;
        }

        return output;
    }

    /**
     * Returns a string representation of all the retail items in the list sorted by their profits.
     * Each item's string representation is obtained by calling its `toString` method, and the profit
     * for each item is displayed alongside it. This method is specifically for sorting retail items,
     * which are the only items in this inventory that can generate profits.
     *
     * @param itemList the inventory to print, which should contain retail items.
     * @return A concatenated string of all retail item representations in the sorted list, each on a new line,
     *         along with their respective profits formatted to two decimal places.
     */
    public String printListSortedByProfit(List<Item> itemList) {
        int index = 1;
        String output = "";

        logger.info("Sorting inventory by profits earned for each item...");
        ArrayList<Item> sortedList = (ArrayList<Item>) itemList.stream()
                .filter((t) -> t instanceof RetailItem)
                .sorted(new ItemComparatorByProfit())
                .collect(toList());

        logger.info("Updating sortedOrder...");
        updateSortedOrder(itemList, sortedList);

        assert sortedOrder.size() == sortedList.size();

        logger.info("Printing sorted list...");
        for (Item item: sortedList) {
            RetailItem retailItem = (RetailItem) item;
            output += index + ". " + retailItem.toString() + System.lineSeparator()
                    + String.format("\tProfit: %.2f", retailItem.getItemProfit())
                    + System.lineSeparator() + System.lineSeparator();
            index++;
        }

        return output;
    }

    /**
     * Updates the sortedOrder ArrayList to enable commands that uses item index to reference the
     * latest sorted inventory that was printed.
     *
     * @param itemList the unsorted inventory ArrayList.
     * @param sortedList the sorted inventory ArrayList.
     */
    private void updateSortedOrder(List<Item> itemList, ArrayList<Item> sortedList) {
        logger.info("Clearing sortedOrder...");
        sortedOrder.clear();
        for (int i = 0; i < sortedList.size(); i++) {
            sortedOrder.add(itemList.indexOf(sortedList.get(i)));
        }
    }

    private ArrayList<Integer> initializeSortedOrder(List<Item> itemList) {
        logger.info("Generating initial sortedOrder...");

        ArrayList<Integer> sortedOrder = new ArrayList<>();
        for (int i = 0; i < itemList.size(); i++) {
            sortedOrder.add(i);
        }

        return sortedOrder;
    }

    /**
     * Deprecated.
     * Returns the String representation of the ItemList.
     *
     * @return The String representation of the ItemList.
     */
    @Override
    public String toString() {
        return itemList.toString();
    }
}
