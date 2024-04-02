package seedu.binbash.comparators;

import seedu.binbash.item.*;

import java.util.Comparator;

/**
 * Represents a comparator used to sort items based on their cost price.
 */
public class ItemComparatorByCostPrice implements Comparator<Item> {

    /**
     * Compares two objects of the Item class based on their cost price.
     * First condition - Item with the lower cost price will be placed before item with the higher cost price.
     * Second condition - Retail items will be placed before operational items.
     * Third condition - Item with lower quantity will be placed before item with higher quantity.
     *
     * @param item1 the first object to be compared.
     * @param item2 the second object to be compared.
     * @return compare results.
     */
    public int compare(Item item1, Item item2) {
        if (item1.getItemCostPrice() < item2.getItemCostPrice()) {
            return -1;
        } else if (item1.getItemCostPrice() > item2.getItemCostPrice()) {
            return 1;
        }

        return compareByItemType(item1, item2);
    }

    /**
     * Compares two objects of the Item class based on their Item type.
     * First condition - Retail items will be placed before operational items.
     * Second condition - Item with lower quantity will be placed before item with higher quantity.
     *
     * @param item1 the first object to be compared.
     * @param item2 the second object to be compared.
     * @return compare results.
     */
    private int compareByItemType (Item item1, Item item2) {
        if (item1 instanceof RetailItem && item2 instanceof OperationalItem) {
            return -1;
        } else if (item1 instanceof OperationalItem && item2 instanceof RetailItem) {
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
