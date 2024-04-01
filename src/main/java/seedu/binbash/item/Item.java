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
    protected Integer itemThreshold;
    protected boolean isAlert;

    public Item(String itemName, String itemDescription, int itemQuantity, double itemCostPrice,
                Integer itemThreshold) {
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

    public void setItemName(String itemName) {
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

    public void setItemCostPrice(double itemCostPrice) {
        this.itemCostPrice = itemCostPrice;
    }

    public int getItemThreshold() {
        return itemThreshold;
    }

    public void setItemThreshold(Integer itemThreshold) {
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
        this.totalUnitsPurchased = totalUnitsPurchased;
    }

    public boolean isAlert() {
        return isAlert;
    }

    public void setAlert(boolean alert) {
        isAlert = alert;
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
