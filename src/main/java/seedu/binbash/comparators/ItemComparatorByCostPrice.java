package seedu.binbash.comparators;

import seedu.binbash.item.Item;
import seedu.binbash.item.PerishableOperationalItem;
import seedu.binbash.item.PerishableRetailItem;

import java.util.Comparator;

public class ItemComparatorByCostPrice implements Comparator<Item> {
    @Override
    public int compare(Item item1, Item item2) {
        if (item1.getItemCostPrice() < item2.getItemCostPrice()) {
            return -1;
        } else if (item1.getItemCostPrice() > item2.getItemCostPrice()) {
            return 1;
        }

        return compareByItemType(item1, item2);
    }

    private int compareByItemType (Item item1, Item item2) {
        if (item1 instanceof PerishableRetailItem && item2 instanceof PerishableOperationalItem) {
        if (item1 instanceof RetailItem && item2 instanceof OperationalItem) {
            return -1;
        } else if (item1 instanceof PerishableOperationalItem && item2 instanceof PerishableRetailItem) {
        } else if (item1 instanceof OperationalItem && item2 instanceof RetailItem) {
            return 1;
        }

        return 0;
    }
}
