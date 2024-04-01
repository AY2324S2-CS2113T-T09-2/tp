package seedu.binbash.comparators;

import seedu.binbash.item.Item;
import seedu.binbash.item.PerishableOperationalItem;
import seedu.binbash.item.PerishableRetailItem;
import seedu.binbash.item.RetailItem;

import java.util.Comparator;

public class ItemComparatorBySalePrice implements Comparator<Item> {
    private RetailItem retailItem1;
    private RetailItem retailItem2;

    @Override
    public int compare(Item item1, Item item2) {
        retailItem1 = (RetailItem) item1 ;
        retailItem2 = (RetailItem) item2;

        if (retailItem1.getItemSalePrice() < retailItem2.getItemSalePrice()) {
            return -1;
        } else if (retailItem1.getItemSalePrice() > retailItem2.getItemCostPrice()) {
            return 1;
        }

        return compareByPerishType(retailItem1, retailItem2);
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
