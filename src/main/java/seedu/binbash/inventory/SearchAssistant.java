package seedu.binbash.inventory;

import seedu.binbash.item.Item;
import seedu.binbash.item.PerishableRetailItem;
import seedu.binbash.item.PerishableOperationalItem;
import seedu.binbash.item.RetailItem;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class SearchAssistant {
    private ArrayList<Item> foundItems;

    public void setFoundItems(ArrayList<Item> foundItems) {
        this.foundItems = foundItems;
    }

    public ArrayList<Item> getFoundItems() {
        return foundItems;
    }

    public ArrayList<Item> getFoundItems(int numberOfResults) {
        if (numberOfResults > foundItems.size()) {
            return foundItems;
        }
        return new ArrayList<Item>(foundItems.subList(0, numberOfResults));
    }

    public SearchAssistant searchByName(String nameField) {
        if (nameField.equals("")) {
            return this;
        }
        foundItems = foundItems.stream()
            .filter(item -> item.getItemName().toLowerCase().contains(nameField.toLowerCase()))
            .collect(Collectors.toCollection(ArrayList::new));
        return this;
    }

    public SearchAssistant searchByDescription(String descriptionField) {
        if (descriptionField.equals("")) {
            return this;
        }
        foundItems = foundItems.stream()
            .filter(item -> item.getItemDescription().toLowerCase().contains(descriptionField.toLowerCase()))
            .collect(Collectors.toCollection(ArrayList::new));
        return this;
    }

    public SearchAssistant searchByQuantityBetween(int from, int to) {
        searchByQuantityFrom(from);
        searchByQuantityTo(to);
        return this;
    }

    public SearchAssistant searchByQuantityFrom(int from) {
        if (from < 0) {
            return this;
        }
        foundItems = foundItems.stream()
            .filter(item -> item.getItemQuantity() >= from)
            .collect(Collectors.toCollection(ArrayList::new));
        return this;
    }

    public SearchAssistant searchByQuantityTo(int to) {
        if (to == Integer.MAX_VALUE) {
            return this;
        }
        foundItems = foundItems.stream()
            .filter(item -> item.getItemQuantity() <= to)
            .collect(Collectors.toCollection(ArrayList::new));
        return this;
    }

    public SearchAssistant searchByCostPriceBetween(double from, double to) {
        searchByCostPriceFrom(from);
        searchByCostPriceTo(to);
        return this;
    }

    public SearchAssistant searchByCostPriceFrom(double fromPrice) {
        if (fromPrice == Double.MIN_VALUE) {
            return this;
        }
        foundItems = foundItems.stream()
            .filter(item -> item.getItemCostPrice() >= fromPrice)
            .collect(Collectors.toCollection(ArrayList::new));
        return this;
    }

    public SearchAssistant searchByCostPriceTo(double toPrice) {
        if (toPrice == Double.MAX_VALUE) {
            return this;
        }
        foundItems = foundItems.stream()
            .filter(item -> item.getItemCostPrice() <= toPrice)
            .collect(Collectors.toCollection(ArrayList::new));
        return this;
    }

    public SearchAssistant searchBySalePriceBetween(double from, double to) {
        searchBySalePriceFrom(from);
        searchBySalePriceTo(to);
        return this;
    }

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

    public SearchAssistant searchByExpiryDateBetween(LocalDate from, LocalDate to) {
        searchByExpiryDateFrom(from);
        searchByExpiryDateTo(to);
        return this;
    }

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
