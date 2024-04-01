package seedu.binbash.comparators;

import seedu.binbash.item.Item;
import seedu.binbash.item.PerishableOperationalItem;
import seedu.binbash.item.PerishableRetailItem;

import java.util.Comparator;


public class ItemComparatorByExpiryDate implements Comparator<Item> {
    private PerishableOperationalItem perishOp1;
    private PerishableOperationalItem perishOp2;
    private PerishableRetailItem perishRe1;
    private PerishableRetailItem perishRe2;

    @Override
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

    private int compareOpToOp (PerishableOperationalItem item1, PerishableOperationalItem item2) {
        if (item1.getItemExpirationDateLocalDate().equals(item2.getItemExpirationDateLocalDate())) {
            return compareQty(item1.getItemQuantity(), item2.getItemQuantity());
        } else if (item1.getItemExpirationDateLocalDate().isBefore(item2.getItemExpirationDateLocalDate())) {
            return -1;
        }

        return 1;
    }

    private int compareOpToRe (PerishableOperationalItem item1, PerishableRetailItem item2) {
        if (item1.getItemExpirationDateLocalDate().equals(item2.getItemExpirationDateLocalDate())) {
            return 1;
        } else if (item1.getItemExpirationDateLocalDate().isBefore(item2.getItemExpirationDateLocalDate())) {
            return -1;
        }

        return 1;
    }

    private int compareReToRe (PerishableRetailItem item1, PerishableRetailItem item2) {
        if (item1.getItemExpirationDateLocalDate().equals(item2.getItemExpirationDateLocalDate())) {
            return compareQty(item1.getItemQuantity(), item2.getItemQuantity());
        } else if (item1.getItemExpirationDateLocalDate().isBefore(item2.getItemExpirationDateLocalDate())) {
            return -1;
        }

        return 1;
    }

    private int compareReToOp (PerishableRetailItem item1, PerishableOperationalItem item2) {
        if (item1.getItemExpirationDateLocalDate().equals(item2.getItemExpirationDateLocalDate())) {
            return -1;
        } else if (item1.getItemExpirationDateLocalDate().isBefore(item2.getItemExpirationDateLocalDate())) {
            return -1;
        }

        return 1;
    }

    private int compareQty(int qty1, int qty2) {
        if (qty1 < qty2) {
            return -1;
        } else if (qty1 > qty2) {
            return 1;
        }

        return 0;
    }
}
