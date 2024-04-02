package seedu.binbash.item;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Perishable Retail Item in the ItemList (inventory).
 * Retail Items are Items meant to be sold, and affect the business' profit margins.
 * Since these Items are meant to be sold, they will have a sale price.
 * Perishable Retail Items have an expiration date.
 */
public class PerishableRetailItem extends RetailItem {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private LocalDate itemExpirationDate;

    public PerishableRetailItem(String itemName, String itemDescription, int itemQuantity,
                                LocalDate itemExpirationDate, double itemSalePrice, double itemCostPrice,
                                int itemThreshold) {
        super(itemName, itemDescription, itemQuantity, itemSalePrice, itemCostPrice, itemThreshold);
        this.itemExpirationDate = itemExpirationDate;
    }

    /**
     * Returns the expiration date of the Item.
     *
     * @return Item expiration date, formatted as a String in dd-MM-yyyy format.
     */
    public String getItemExpirationDate() {
        return itemExpirationDate.format(DATE_TIME_FORMATTER);
    }

    public void setItemExpirationDate(LocalDate itemExpirationDate) {
        this.itemExpirationDate = itemExpirationDate;
    }

    public LocalDate getLocalDateItemExpirationDate() {
        return itemExpirationDate;
    }

    @Override
    public String toString() {
        return "[P]" + super.toString() + System.lineSeparator() +
                String.format("\texpiry date: %s", itemExpirationDate.format(DATE_TIME_FORMATTER));
    }
}
