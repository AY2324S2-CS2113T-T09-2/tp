package seedu.binbash.comparators;

import seedu.binbash.item.Item;
import seedu.binbash.item.RetailItem;

import java.util.Comparator;

/**
 * Comparator for comparing two RetailItem (and PerishableRetailItem) objects based on their profits, used for
 * sorting a collection of items in descending order of profits.
 */
public class ItemComparatorByProfit implements Comparator<Item> {

    private RetailItem retailItem1;
    private RetailItem retailItem2;

    /**
     * Compares two objects of the RetailItem class based on their profits.
     *
     * @param item1 The first object to be compared.
     * @param item2 The second object to be compared.
     * @return A negative integer if the first item's profit is greater than the second item's profit,
     * positive integer if the first item's profit is less than or equals to the second item's profit,
     */
    public int compare(Item item1, Item item2) {
        retailItem1 = (RetailItem) item1 ;
        retailItem2 = (RetailItem) item2;

        if (retailItem1.getItemProfit() > retailItem2.getItemProfit()) {
            return -1;
        } else {
            return 1;
        }
    }
}
