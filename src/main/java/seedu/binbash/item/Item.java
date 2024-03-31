package seedu.binbash.item;

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
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public double getItemCostPrice() {
        return itemCostPrice;
    }

    public int getItemThreshold() {
        return itemThreshold;
    }

    public void setItemThreshold(Integer itemThreshold) {
        this.itemThreshold = itemThreshold;
    }

    public int getTotalUnitsPurchased() {
        return totalUnitsPurchased;
    }

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
