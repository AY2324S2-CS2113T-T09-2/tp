package seedu.binbash.item;

/**
 * Represents an Operational Item in the ItemList (inventory).
 * Operational Items are Items meant for internal use, to ensure business functionality.
 * These Items are not meant to be sold, and thus, have no sale price.
 */
public class OperationalItem extends Item {

    public OperationalItem(String itemName, String itemDescription, int itemQuantity, double itemCostPrice) {
        this(itemName, itemDescription, itemQuantity, itemCostPrice, -1);
    }

    public OperationalItem(String itemName, String itemDescription, int itemQuantity, double itemCostPrice,
                           int itemThreshold) {
        super(itemName, itemDescription, itemQuantity, itemCostPrice, itemThreshold);
    }

    @Override
    public String toString() {
        return "[O] " + super.toString() + System.lineSeparator()
                + String.format("\tthreshold: %d", itemThreshold);
    }
}
