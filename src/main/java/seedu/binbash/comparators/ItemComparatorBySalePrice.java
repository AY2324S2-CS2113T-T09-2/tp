package seedu.binbash.comparators;

import seedu.binbash.item.Item;
import seedu.binbash.item.PerishableOperationalItem;
import seedu.binbash.item.PerishableRetailItem;
import seedu.binbash.item.RetailItem;

import java.util.Comparator;

public class ItemComparatorBySalePrice implements Comparator<RetailItem> {
    @Override
    public int compare(RetailItem item1, RetailItem item2) {
        if (item1.getItemSalePrice() < item2.getItemSalePrice()) {
            return -1;
        } else if (item1.getItemSalePrice() > item2.getItemCostPrice()) {
            return 1;
        }

        return compareByPerishType(item1, item2);
    }

    private int compareByPerishType(RetailItem item1, RetailItem item2) {
        if (item1 instanceof PerishableRetailItem && !(item2 instanceof PerishableRetailItem)) {
            return -1;
        } else if (!(item1 instanceof PerishableRetailItem) && item2 instanceof PerishableRetailItem) {
            return 1;
        }

        return 0;
    }
}
