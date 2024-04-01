package seedu.binbash.comparators;

import seedu.binbash.item.Item;
import seedu.binbash.item.PerishableOperationalItem;
import seedu.binbash.item.PerishableRetailItem;

import java.util.Comparator;

/**
 * Represents a comparator used to sort items based on their expiry date.
 */
public class ItemComparatorByExpiryDate implements Comparator<Item> {
    private PerishableOperationalItem perishOp1;
    private PerishableOperationalItem perishOp2;
    private PerishableRetailItem perishRe1;
    private PerishableRetailItem perishRe2;

    /**
     * Compares two objects of the Item class based on their expiry date.
     * First condition - Item with the earlier expiry date will be placed before item with the later expiry date.
     * Second condition - Retail items will be placed before operational items.
     * Third condition - Item with lower quantity will be placed before items with higher quantity.
     *
     * @param item1 the first object to be compared.
     * @param item2 the second object to be compared.
     * @return compare results.
     */
    public int compare(Item item1, Item item2) {
        if (item1 instanceof PerishableOperationalItem && item2 instanceof PerishableOperationalItem) {
            perishOp1 = (PerishableOperationalItem) item1 ;
            perishOp2 = (PerishableOperationalItem) item2;
            return compareOpToOp(perishOp1, perishOp2);
        } else if (item1 instanceof PerishableOperationalItem && item2 instanceof PerishableRetailItem ) {
            perishOp1 = (PerishableOperationalItem)  item1;
            perishRe2 = (PerishableRetailItem)  item2;
            return compareOpToRe(perishOp1, perishRe2);
        } else if (item1 instanceof PerishableRetailItem && item2 instanceof PerishableRetailItem) {
            perishRe1 = (PerishableRetailItem) item1;
            perishRe2 = (PerishableRetailItem) item2;
            return compareReToRe (perishRe1, perishRe2);
        } else {
            perishRe1 = (PerishableRetailItem) item1;
            perishOp2 = (PerishableOperationalItem) item2;
            return compareReToOp(perishRe1, perishOp2);
        }
    }

    /**
     * Compares two objects of the PerishableOperationalItem class based on their expiry date.
     * First condition - Item with the earlier expiry date will be placed before item with the later expiry date.
     * Second condition - Item with lower quantity will be placed before item with higher quantity.
     *
     * @param item1 the first object to be compared.
     * @param item2 the second object to be compared.
     * @return compare results.
     */
    private int compareOpToOp (PerishableOperationalItem item1, PerishableOperationalItem item2) {
        if (item1.getItemExpirationDateLocalDate().equals(item2.getItemExpirationDateLocalDate())) {
            return compareQty(item1.getItemQuantity(), item2.getItemQuantity());
        } else if (item1.getItemExpirationDateLocalDate().isBefore(item2.getItemExpirationDateLocalDate())) {
            return -1;
        }

        return 1;
    }

    /**
     * Compares objects of the PerishableOperationalItem and PerishableRetailItem classes based on their expiry date.
     * First condition - Items with the earlier expiry date will be placed before item with the later expiry date.
     * Second condition - PerishableRetailItems will be placed before PerishableOperationalItems.
     * Second condition - Item with lower quantity will be placed before item with higher quantity.
     *
     * @param item1 the first object to be compared.
     * @param item2 the second object to be compared.
     * @return compare results.
     */
    private int compareOpToRe (PerishableOperationalItem item1, PerishableRetailItem item2) {
        if (item1.getItemExpirationDateLocalDate().equals(item2.getItemExpirationDateLocalDate())) {
            return 1;
        } else if (item1.getItemExpirationDateLocalDate().isBefore(item2.getItemExpirationDateLocalDate())) {
            return -1;
        }

        return 1;
    }

    /**
     * Compares objects of the PerishableRetailItem class based on their expiry date.
     * First condition - Item with earlier expiry date will be placed before item with later expiry date.
     * Second condition - Item with lower quantity will be placed before item with higher quantity.
     *
     * @param item1 the first object to be compared.
     * @param item2 the second object to be compared.
     * @return compare results.
     */
    private int compareReToRe (PerishableRetailItem item1, PerishableRetailItem item2) {
        if (item1.getItemExpirationDateLocalDate().equals(item2.getItemExpirationDateLocalDate())) {
            return compareQty(item1.getItemQuantity(), item2.getItemQuantity());
        } else if (item1.getItemExpirationDateLocalDate().isBefore(item2.getItemExpirationDateLocalDate())) {
            return -1;
        }

        return 1;
    }

    /**
     * Compares objects of the PerishableOperationalItem and PerishableRetailItem classes based on their expiry date.
     * First condition - Items with the earlier expiry date will be placed before item with the later expiry date.
     * Second condition - PerishableRetailItems will be placed before PerishableOperationalItems.
     * Second condition - Item with lower quantity will be placed before item with higher quantity.
     *
     * @param item1 the first object to be compared.
     * @param item2 the second object to be compared.
     * @return compare results.
     */
    private int compareReToOp (PerishableRetailItem item1, PerishableOperationalItem item2) {
        if (item1.getItemExpirationDateLocalDate().equals(item2.getItemExpirationDateLocalDate())) {
            return -1;
        } else if (item1.getItemExpirationDateLocalDate().isBefore(item2.getItemExpirationDateLocalDate())) {
            return -1;
        }

        return 1;
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
