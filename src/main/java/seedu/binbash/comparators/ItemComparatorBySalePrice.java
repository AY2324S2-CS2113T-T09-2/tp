package seedu.binbash.comparators;

import seedu.binbash.item.Item;
import seedu.binbash.item.PerishableRetailItem;
import seedu.binbash.item.RetailItem;

import java.util.Comparator;

/**
 * Represents a comparator used to sort items based on their sale price.
 */
public class ItemComparatorBySalePrice implements Comparator<Item> {
    private RetailItem retailItem1;
    private RetailItem retailItem2;

    /**
     * Compares two objects of the RetailItem class based on sale price.
     * First condition - Item with lower sale price will be placed before item with higher sale price.
     * Second condition - Perishable items will be placed before non-perishable items.
     *
     * @param item1 the first object to be compared.
     * @param item2 the second object to be compared.
     * @return compare results.
     */
    public int compare(Item item1, Item item2) {
        retailItem1 = (RetailItem) item1 ;
        retailItem2 = (RetailItem) item2;

        if (retailItem1.getItemSalePrice() < retailItem2.getItemSalePrice()) {
            return -1;
        } else if (retailItem1.getItemSalePrice() > retailItem2.getItemSalePrice()) {
            return 1;
        }

        return compareByPerishType(retailItem1, retailItem2);
    }

    /**
     * Compares two objects of RetailItem class based on perishable item type.
     * First condition - Perishable items will be placed before non-perishable items.
     * Third condition - Items of lower quantity will be placed before items of higher quantity.
     *
     * @param item1 the first object to be compared.
     * @param item2 the second object to be compared.
     * @return compare results.
     */
    private int compareByPerishType(RetailItem item1, RetailItem item2) {
        if (item1 instanceof PerishableRetailItem && !(item2 instanceof PerishableRetailItem)) {
            return -1;
        } else if (!(item1 instanceof PerishableRetailItem) && item2 instanceof PerishableRetailItem) {
            return 1;
        }

        return compareQty(item1.getItemQuantity(), item2.getItemQuantity());
    }

    /**
     * Compares the value of two integers based. The lower quantity will be placed before the higher quantity.
     *
     * @param qty1 the quantity of the first item.
     * @param qty2 the quantity of the second item.
     * @return compare results.
     */
    private int compareQty(int qty1, int qty2) {
        if (qty1 < qty2) {
            return -1;
        } else if (qty1 > qty2) {
            return 1;
        }

        return 0;
    }
}
