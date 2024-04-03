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
                      double itemSalePrice, double itemCostPrice) {
        this(itemName, itemDescription, itemQuantity, itemSalePrice, itemCostPrice, -1);
    }

    public RetailItem(String itemName, String itemDescription, int itemQuantity,
                      double itemSalePrice, double itemCostPrice, int itemThreshold) {
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

    /**
     * Sets the sale price of the Item.
     *
     * @param itemSalePrice The new sale price of the Item.
     */
    public void setItemSalePrice(double itemSalePrice) {
        assert itemSalePrice >= 0.0;
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
        assert totalUnitsSold >= 0;
        this.totalUnitsSold = totalUnitsSold;
    }

    /**
     * Computes the profit margin of the current Item.
     * Profit is calculated using the formula: ( total_revenue - total_cost )
     *
     * @return Profit of the current Item as a Double value.
     */
    public double getItemProfit() {
        double totalRevenue = this.itemSalePrice * this.totalUnitsSold;
        double totalCost = this.itemCostPrice * this.totalUnitsPurchased;

        return totalRevenue - totalCost;
    }

    @Override
    public String toString() {
        return "[R] " + super.toString() + System.lineSeparator()
                + String.format("\tsale price: $%.2f", itemSalePrice) + System.lineSeparator()
                + String.format("\tthreshold: %d", itemThreshold);
    }
}
