package seedu.binbash.inventory;

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

public class ItemList {
    private static final BinBashLogger logger = new BinBashLogger(ItemList.class.getName());
    private double totalRevenue;
    private double totalCost;
    private final ArrayList<Item> itemList;
    private ArrayList<Integer> sortedOrder;
    private SearchAssistant searchAssistant;

    public ItemList(ArrayList<Item> itemList) {
        this.itemList = itemList;
        this.sortedOrder = new ArrayList<Integer>();
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

    public ArrayList<Integer> getSortedOrder() {
        return sortedOrder;
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
        sortedOrder.add(beforeSize);
        itemList.add(item);
        assert itemList.size() == (beforeSize + 1);
        assert sortedOrder.size() == (beforeSize + 1);

        String output = "Noted! I have added the following item into your inventory:" + System.lineSeparator()
                + System.lineSeparator() + item;
        return output;
    }

    public String updateItemDataByName (String itemName, String itemDescription, int itemQuantity,
                                  LocalDate itemExpirationDate, double itemSalePrice, double itemCostPrice,
                                  int itemThreshold) throws InvalidCommandException {
        Item item = findItemByName(itemName);

        updateItemData(item, itemDescription, itemQuantity, itemExpirationDate, itemSalePrice, itemCostPrice,
                itemThreshold);

        String output = "I have updated the your item information. Do check the following if it is correct."
                + System.lineSeparator() + System.lineSeparator() + item;
        return output;
    }

    public String updateItemDataByIndex (int index, String itemDescription, int itemQuantity,
                                  LocalDate itemExpirationDate, double itemSalePrice, double itemCostPrice,
                                  int itemThreshold) throws InvalidCommandException {
        Item item = itemList.get(index - 1);

        updateItemData(item, itemDescription, itemQuantity, itemExpirationDate, itemSalePrice, itemCostPrice,
                itemThreshold);

        String output = "I have updated the your item information. Do check the following if it is correct."
                + System.lineSeparator() + System.lineSeparator() + item;;
        return output;
    }

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

    public void updateItemExpirationDate(Item item, LocalDate itemExpirationDate) throws InvalidCommandException {
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

    public Item findItemByName(String itemName) throws InvalidCommandException {
        for (Item item : itemList) {
            if (item.getItemName().trim().equals(itemName.trim())) {
                return item;
            }
        }
        throw new InvalidCommandException("Item with name '" + itemName + "' not found.");
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
        Item tempItem = itemList.remove((sortedOrder.get(index - 1).intValue()));
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
        for (int i = 0; i < sortedOrder.size(); i ++) {
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

    @Override
    public String toString() {
        return itemList.toString();
    }
}
