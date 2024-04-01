package seedu.binbash.inventory;

import seedu.binbash.item.Item;
import seedu.binbash.item.PerishableRetailItem;
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

    public SearchAssistant searchByCostPrice(double costPriceField, boolean isSearchLessThan) {
        if (costPriceField == Double.MIN_VALUE || costPriceField == Double.MAX_VALUE) {
            return this;
        }
        foundItems = foundItems.stream()
            .filter(item -> isSearchLessThan ?
                    item.getItemCostPrice() <= costPriceField : item.getItemCostPrice() > costPriceField)
            .collect(Collectors.toCollection(ArrayList::new));
        return this;
    }

    public SearchAssistant searchBySalePrice(double salePriceField, boolean isSearchLessThan) {
        if (salePriceField == Double.MIN_VALUE || salePriceField == Double.MAX_VALUE) {
            return this;
        }
        foundItems = foundItems.stream()
            .filter(item -> item instanceof RetailItem)
            .filter(item -> isSearchLessThan ?
                    ((RetailItem) item).getItemSalePrice() < salePriceField :
                    ((RetailItem) item).getItemSalePrice() > salePriceField)
            .collect(Collectors.toCollection(ArrayList::new));
        return this;
    }

    public SearchAssistant searchByExpiryDate(LocalDate expiryDateField) {
        if (expiryDateField == LocalDate.MAX) {
            return this;
        }
        foundItems = foundItems.stream()
            .filter(item -> item instanceof PerishableRetailItem ?
                    LocalDate.parse(((PerishableRetailItem) item).getItemExpirationDate(),
                        DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                    .isBefore(expiryDateField) : false)
            .collect(Collectors.toCollection(ArrayList::new));
        return this;
    }
}
