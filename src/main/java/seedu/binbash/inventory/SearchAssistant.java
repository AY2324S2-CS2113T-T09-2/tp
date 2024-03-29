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
    private int numberOfResults;

    public void setFoundItems(ArrayList<Item> foundItems) {
        this.foundItems = foundItems;
    }

    public void setNumberOfResults(int numberOfResults) {
        this.numberOfResults = numberOfResults;
    }

    public ArrayList<Item> getFoundItems() {
        return foundItems;
    }

    public SearchAssistant searchByName(String nameField) {
        if (foundItems.size() >= numberOfResults) {
            return this;
        }
        foundItems = (ArrayList<Item>) foundItems.stream()
            .filter(item -> item.getItemName().contains(nameField))
            .limit(numberOfResults)
            .collect(Collectors.toList());
        return this;
    }

    public SearchAssistant searchByDescription(String descriptionField) {
        if (foundItems.size() >= numberOfResults) {
            return this;
        }
        foundItems = (ArrayList<Item>) foundItems.stream()
            .filter(item -> item.getItemDescription().contains(descriptionField))
            .limit(numberOfResults)
            .collect(Collectors.toList());
        return this;
    }

    public SearchAssistant searchByCostPrice(double costPriceField) {
        if (foundItems.size() >= numberOfResults) {
            return this;
        }
        foundItems = (ArrayList<Item>) foundItems.stream()
            .filter(item -> (costPriceField < 0) ? item.getItemCostPrice() < (-1 * costPriceField) : 
                    item.getItemCostPrice() > costPriceField)
            .limit(numberOfResults)
            .collect(Collectors.toList());
        return this;
    }

    public SearchAssistant searchBySalePrice(double salePriceField) {
        if (foundItems.size() >= numberOfResults) {
            return this;
        }
        foundItems = (ArrayList<Item>) foundItems.stream()
            .filter(item -> (salePriceField < 0) ?
                    item instanceof RetailItem && ((RetailItem) item).getItemSalePrice() < (-1 * salePriceField) :
                    salePriceField == 0 ||
                     (item instanceof RetailItem && ((RetailItem) item).getItemSalePrice() > salePriceField))
            .limit(numberOfResults)
            .collect(Collectors.toList());
        return this;
    }

    public SearchAssistant searchByExpiryDate(LocalDate expiryDateField) {
        if (foundItems.size() >= numberOfResults) {
            return this;
        }
        foundItems = (ArrayList<Item>) foundItems.stream()
            .filter(item -> item instanceof PerishableRetailItem ?
                    LocalDate.parse(((PerishableRetailItem) item).getItemExpirationDate(),
                        DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                    .isBefore(expiryDateField) : true)
            .limit(numberOfResults)
            .collect(Collectors.toList());
        return this;
    }
}
