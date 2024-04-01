package seedu.binbash.item;

/**
 * Represents a Retail Item in the ItemList (inventory).
 * Retail Items are Items meant to be sold, and affect the business' profit margins.
 * Since these Items are meant to be sold, they will have a sale price.
 */
public class RetailItem extends Item {

    private double itemSalePrice;
    private int totalUnitsSold;
    public RetailItem(String itemName, String itemDescription, int itemQuantity,
                      double itemSalePrice, double itemCostPrice, Integer itemThreshold) {
        super(itemName, itemDescription, itemQuantity, itemCostPrice, itemThreshold);
        this.itemSalePrice = itemSalePrice;
        this.totalUnitsSold = 0;
    }

    /**
     * Returns the sale price of the Item.
     *
     * @return Double value representing the Item sale price.
     */
    public double getItemSalePrice() {
        return itemSalePrice;
    }

    public void setItemSalePrice(double itemSalePrice) {
        this.itemSalePrice = itemSalePrice;
    }

    /**
     * Returns the number of units sold of the Item.
     *
     * @return Integer value representing the number of units sold of the Item.
     */
    public int getTotalUnitsSold() {
        return totalUnitsSold;
    }

    /**
     * Sets the number of units sold of the Item.
     *
     * @param totalUnitsSold The number of units sold of the Item.
     */
    public void setTotalUnitsSold(int totalUnitsSold) {
        this.totalUnitsSold = totalUnitsSold;
    }

    @Override
    public String toString() {
        return "[R] " + super.toString() + System.lineSeparator()
                + String.format("\tsale price: $%.2f", itemSalePrice) + System.lineSeparator()
                + String.format("\tthreshold: %d", itemThreshold);
    }
}
