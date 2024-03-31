package seedu.binbash.item;

/**
 * Abstract Item class, which represents an Item that can be stored in the ItemList (inventory).
 * Subclasses can extend Item in order to add properties that are specific to different Item types.
 */
public abstract class Item {
    protected final String itemName;
    protected final String itemDescription;
    protected int itemQuantity;
    protected final double itemCostPrice;
    protected int totalUnitsPurchased;

    /**
     * Constructs an Item. Provided parameters must be non-null.
     */
    protected Item(String itemName, String itemDescription, int itemQuantity, double itemCostPrice) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemQuantity = itemQuantity;
        this.itemCostPrice = itemCostPrice;
        this.totalUnitsPurchased = itemQuantity;
    }

    /**
     * Returns the name of the Item.
     *
     * @return Item name String.
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Returns the description of the Item.
     *
     * @return Item description String.
     */
    public String getItemDescription() {
        return itemDescription;
    }

    /**
     * Returns the quantity of the Item.
     *
     * @return Integer value representing the Item quantity.
     */
    public int getItemQuantity() {
        return itemQuantity;
    }

    /**
     * Sets the quantity of the Item.
     *
     * @param itemQuantity The new Item quantity value.
     */
    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    /**
     * Returns the cost price of the Item.
     *
     * @return Double value representing the Item cost price.
     */
    public double getItemCostPrice() {
        return itemCostPrice;
    }

    /**
     * Returns the number of units purchased of the Item.
     *
     * @return Integer value representing the number of units purchased of the Item.
     */
    public int getTotalUnitsPurchased() {
        return totalUnitsPurchased;
    }

    /**
     * Sets the number of units purchased of the Item.
     *
     * @param totalUnitsPurchased The number of units purchased of the Item.
     */
    public void setTotalUnitsPurchased(int totalUnitsPurchased) {
        this.totalUnitsPurchased = totalUnitsPurchased;
    }

    @Override
    public String toString() {
        return String.format("%s" + System.lineSeparator() +
                        "\tdescription: %s" + System.lineSeparator() +
                        "\tquantity: %d" + System.lineSeparator() +
                        "\tcost price: $%.2f",
                itemName,
                itemDescription,
                itemQuantity,
                itemCostPrice
        );
    }
}
