package seedu.binbash.item;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PerishableOperationalItem extends OperationalItem {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private LocalDate itemExpirationDate;

    public PerishableOperationalItem(String itemName, String itemDescription, int itemQuantity,
                                     LocalDate itemExpirationDate, double itemCostPrice, Integer itemThreshold) {
        super(itemName, itemDescription, itemQuantity, itemCostPrice, itemThreshold);
        this.itemExpirationDate = itemExpirationDate;
    }

    public String getItemExpirationDate() {
        return itemExpirationDate.format(DATE_TIME_FORMATTER);
    }

    public LocalDate getLocalDateItemExpirationDate() {
        return itemExpirationDate;
    }

    public void setItemExpirationDate(LocalDate itemExpirationDate) {
        this.itemExpirationDate = itemExpirationDate;
    }

    @Override
    public String toString() {
        return "[P] " + super.toString() + System.lineSeparator() +
                String.format("\texpiry date: %s", itemExpirationDate.format(DATE_TIME_FORMATTER));
    }
}
