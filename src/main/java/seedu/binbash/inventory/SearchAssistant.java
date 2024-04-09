package seedu.binbash.inventory;

import seedu.binbash.item.Item;
import seedu.binbash.item.PerishableRetailItem;
import seedu.binbash.item.PerishableOperationalItem;
import seedu.binbash.item.RetailItem;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * A search assistant with a number of helper methods to search through an item list
 * based on an arbitrary number of fields.
 */
public class SearchAssistant {
    private ArrayList<Item> foundItems;

    /**
     * Note that the list of items to search through has to be explicitly set by the caller.
     */
    public void setFoundItems(ArrayList<Item> foundItems) {
        this.foundItems = foundItems;
    }

    public ArrayList<Item> getFoundItems() {
        return foundItems;
    }

    /**
     * Returns at most the desired number of items found
     *
     * @param numberOfResults The number of results desired
     * @return A list of the first numberOfResults items in foundItems if at least numberOfResults items 
     *     in foundItems, the full foundItems list otherwise
     */
    public ArrayList<Item> getFoundItems(int numberOfResults) {
        if (numberOfResults > foundItems.size()) {
            return foundItems;
        }
        return new ArrayList<Item>(foundItems.subList(0, numberOfResults));
    }

    /**
     * Searches for items whose names contain the specified substring, ignoring case.
     * If the given string is empty, no filtering is applied.
     *
     * @param nameField The given string.
     * @return The current instance of SearchAssistant.
     */
    public SearchAssistant searchByName(String nameField) {
        if (nameField.equals("")) {
            return this;
        }
        foundItems = foundItems.stream()
            .filter(item -> item.getItemName().toLowerCase().contains(nameField.toLowerCase()))
            .collect(Collectors.toCollection(ArrayList::new));
        return this;
    }

    /**
     * Searches for items whose names exactly match the specified string, with case sensitivity.
     * If the given string is empty, no filtering is applied.
     *
     * @param nameField The exact name to search for in the item names.
     * @return The current instance of SearchAssistant with the filtered list of items.
     */
    public SearchAssistant searchByExactName(String nameField) {
        if (nameField.equals("")) {
            return this;
        }
        foundItems = foundItems.stream()
                .filter(item -> item.getItemName().equals(nameField))
                .collect(Collectors.toCollection(ArrayList::new));
        return this;
    }

    /**
     * Finds all items that contain a given string in their description.
     *
     * @param descriptionField The given string.
     * @return The current instance of SearchAssistant.
     */
    public SearchAssistant searchByDescription(String descriptionField) {
        if (descriptionField.equals("")) {
            return this;
        }
        foundItems = foundItems.stream()
            .filter(item -> item.getItemDescription().toLowerCase().contains(descriptionField.toLowerCase()))
            .collect(Collectors.toCollection(ArrayList::new));
        return this;
    }

    /**
     * Finds all items with quantities between a certain range, inclusive.
     *
     * @param from The lower bound of this range.
     * @param to The upper bound of this range.
     * @return The current instance of SearchAssistant.
     */
    public SearchAssistant searchByQuantityBetween(int from, int to) {
        searchByQuantityFrom(from);
        searchByQuantityTo(to);
        return this;
    }

    /**
     * Finds all items whose quantities are equal to or above a given value.
     *
     * @param from The given price.
     * @return The current instance of SearchAssistant.
     */
    public SearchAssistant searchByQuantityFrom(int from) {
        if (from < 0) {
            return this;
        }
        foundItems = foundItems.stream()
            .filter(item -> item.getItemQuantity() >= from)
            .collect(Collectors.toCollection(ArrayList::new));
        return this;
    }

    /**
     * Finds all items whose quantities are equal to or below a given price.
     *
     * @param to The given price.
     * @return The current instance of SearchAssistant.
     */
    public SearchAssistant searchByQuantityTo(int to) {
        if (to == Integer.MAX_VALUE) {
            return this;
        }
        foundItems = foundItems.stream()
            .filter(item -> item.getItemQuantity() <= to)
            .collect(Collectors.toCollection(ArrayList::new));
        return this;
    }

    /**
     * Finds all items that cost between two values, inclusive.
     *
     * @param from The first value.
     * @param to The second value.
     * @return The current instance of SearchAssistant.
     */
    public SearchAssistant searchByCostPriceBetween(double from, double to) {
        searchByCostPriceFrom(from);
        searchByCostPriceTo(to);
        return this;
    }

    /**
     * Finds all items that cost as much as or more than a given price.
     *
     * @param fromPrice The given price.
     * @return The current instance of SearchAssistant.
     */
    public SearchAssistant searchByCostPriceFrom(double fromPrice) {
        if (fromPrice == Double.MIN_VALUE) {
            return this;
        }
        foundItems = foundItems.stream()
            .filter(item -> item.getItemCostPrice() >= fromPrice)
            .collect(Collectors.toCollection(ArrayList::new));
        return this;
    }

    /**
     * Finds all items that cost as much as or less than a given price.
     *
     * @param toPrice The given price.
     * @return The current instance of SearchAssistant.
     */
    public SearchAssistant searchByCostPriceTo(double toPrice) {
        if (toPrice == Double.MAX_VALUE) {
            return this;
        }
        foundItems = foundItems.stream()
            .filter(item -> item.getItemCostPrice() <= toPrice)
            .collect(Collectors.toCollection(ArrayList::new));
        return this;
    }

    /**
     * Finds all items that sell for between two values, inclusive.
     *
     * @param from The first value.
     * @param to The second value.
     * @return The current instance of SearchAssistant.
     */
    public SearchAssistant searchBySalePriceBetween(double from, double to) {
        searchBySalePriceFrom(from);
        searchBySalePriceTo(to);
        return this;
    }

    /**
     * Finds all items that sell for as much or more than a given price.
     * Note that items without a sale price are not considered to match this query, and will be filtered out.
     *
     * @param fromPrice The given price.
     * @return The current instance of SearchAssistant.
     */
    public SearchAssistant searchBySalePriceFrom(double fromPrice) {
        if (fromPrice == Double.MIN_VALUE) {
            return this;
        }
        foundItems = foundItems.stream()
            .filter(item -> item instanceof RetailItem)
            .filter(item -> ((RetailItem) item).getItemSalePrice() >= fromPrice)
            .collect(Collectors.toCollection(ArrayList::new));
        return this;
    }

    /**
     * Finds all items that sell for as much or less than a given price.
     * Note that items without a sale price are not considered to match this query, and will be filtered out.
     *
     * @param toPrice The given price.
     * @return The current instance of SearchAssistant.
     */
    public SearchAssistant searchBySalePriceTo(double toPrice) {
        if (toPrice == Double.MAX_VALUE) {
            return this;
        }
        foundItems = foundItems.stream()
            .filter(item -> item instanceof RetailItem)
            .filter(item -> ((RetailItem) item).getItemSalePrice() <= toPrice)
            .collect(Collectors.toCollection(ArrayList::new));
        return this;
    }

    /**
     * Finds all items that expire between two dates, inclusive.
     *
     * @param from The start date.
     * @param to The end date.
     * @return The current instance of SearchAssistant.
     */
    public SearchAssistant searchByExpiryDateBetween(LocalDate from, LocalDate to) {
        searchByExpiryDateFrom(from);
        searchByExpiryDateTo(to);
        return this;
    }

    /**
     * Finds all items that expire on or after a given date.
     * Note that items without an expiry date are not considered to match this query, and will be filtered out.
     *
     * @param fromDate The given date.
     * @return The current instance of SearchAssistant.
     */
    public SearchAssistant searchByExpiryDateFrom(LocalDate fromDate) {
        if (fromDate == LocalDate.MIN) {
            return this;
        }
        foundItems = foundItems.stream()
            .filter(item -> item instanceof PerishableRetailItem || item instanceof PerishableOperationalItem)
            .filter(item -> item instanceof PerishableRetailItem?
                    !LocalDate.parse(((PerishableRetailItem) item).getItemExpirationDate(),
                        DateTimeFormatter.ofPattern("dd-MM-yyyy")).isBefore(fromDate) :
                    !LocalDate.parse(((PerishableOperationalItem) item).getItemExpirationDate(),
                        DateTimeFormatter.ofPattern("dd-MM-yyyy")).isBefore(fromDate))
            .collect(Collectors.toCollection(ArrayList::new));
        return this;
    }

    /**
     * Finds all items that expire on or before a given date.
     * Note that items without an expiry date are not considered to match this query, and will be filtered out.
     *
     * @param toDate The given date.
     * @return The current instance of SearchAssistant.
     */
    public SearchAssistant searchByExpiryDateTo(LocalDate toDate) {
        if (toDate == LocalDate.MAX) {
            return this;
        }
        foundItems = foundItems.stream()
            .filter(item -> item instanceof PerishableRetailItem || item instanceof PerishableOperationalItem)
            .filter(item -> item instanceof PerishableRetailItem?
                    !LocalDate.parse(((PerishableRetailItem) item).getItemExpirationDate(),
                        DateTimeFormatter.ofPattern("dd-MM-yyyy")).isAfter(toDate) :
                    !LocalDate.parse(((PerishableOperationalItem) item).getItemExpirationDate(),
                        DateTimeFormatter.ofPattern("dd-MM-yyyy")).isAfter(toDate))
            .collect(Collectors.toCollection(ArrayList::new));
        return this;
    }
}
