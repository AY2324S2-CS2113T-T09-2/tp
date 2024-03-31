package seedu.binbash.item;

public class OperationalItem extends Item {
    public OperationalItem(String itemName, String itemDescription, int itemQuantity, double itemCostPrice,
                           Integer itemThreshold) {
        super(itemName, itemDescription, itemQuantity, itemCostPrice, itemThreshold);
    }

    @Override
    public String toString() {
        return "[O] " + super.toString();
    }
}
