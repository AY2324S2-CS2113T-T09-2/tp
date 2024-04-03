package seedu.binbash.item;

/**
 * Abstract Item class, which represents an Item that can be stored in the ItemList (inventory).
 * Subclasses can extend Item in order to add properties that are specific to different Item types.
 */
public abstract class Item {
    protected String itemName;
    protected String itemDescription;
    protected int itemQuantity;
    protected double itemCostPrice;
    protected int totalUnitsPurchased;
    protected int itemThreshold;
    protected boolean isAlert;

    public Item(String itemName, String itemDescription, int itemQuantity, double itemCostPrice,
                int itemThreshold) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemQuantity = itemQuantity;
        this.itemCostPrice = itemCostPrice;
        this.itemThreshold = itemThreshold;
        this.totalUnitsPurchased = itemQuantity;
        this.isAlert = false;
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
     * Sets the name of the Item.
     *
     * @param itemName The new Item name.
     */
    public void setItemName(String itemName) {
        assert !itemName.isEmpty();
        this.itemName = itemName;
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
     * Sets the description of the Item.
     *
     * @param itemDescription The new Item description.
     */
    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
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
        assert itemQuantity >= 0;
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
     * Sets the cost price of the Item.
     *
     * @param itemCostPrice The new Item cost price.
     */
    public void setItemCostPrice(double itemCostPrice) {
        assert itemCostPrice >= 0.0;
        this.itemCostPrice = itemCostPrice;
    }

    /**
     * Returns the quantity threshold of the item.
     *
     * @return Integer value representing the Item quantity threshold.
     */
    public int getItemThreshold() {
        return itemThreshold;
    }

    /**
     * Sets the quantity threshold of the Item.
     *
     * @param itemThreshold The new Item quantity threshold.
     */
    public void setItemThreshold(int itemThreshold) {
        assert itemThreshold >= 0;
        this.itemThreshold = itemThreshold;
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
        assert totalUnitsPurchased >= 0;
        this.totalUnitsPurchased = totalUnitsPurchased;
    }

    /**
     * Sets the isAlert status of the Item.
     * This value determines if the Item quantity is running below its quantity threshold value.
     *
     * @param isAlert The new isAlert boolean value.
     */
    public void setAlert(boolean isAlert) {
        this.isAlert = isAlert;
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
