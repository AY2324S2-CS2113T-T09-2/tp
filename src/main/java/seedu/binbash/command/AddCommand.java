package seedu.binbash.command;

import java.time.LocalDate;
import seedu.binbash.inventory.ItemList;
import seedu.binbash.logger.BinBashLogger;

/**
 * Represents the execution of an add command that adds an item to inventory.
 */
public class AddCommand extends Command {

    private final String itemType;
    private final String itemName;
    private final String itemDescription;
    private final int itemQuantity;
    private final LocalDate itemExpirationDate;
    private final double itemSalePrice;
    private final double itemCostPrice;
    private final int itemThreshold;

    public AddCommand( String itemType, String itemName, String itemDescription, int itemQuantity,
                      LocalDate itemExpirationDate, double itemSalePrice, double itemCostPrice, int itemThreshold) {
        commandLogger = new BinBashLogger(AddCommand.class.getName());
        this.itemType = itemType;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemQuantity = itemQuantity;
        this.itemExpirationDate = itemExpirationDate;
        this.itemSalePrice = itemSalePrice;
        this.itemCostPrice = itemCostPrice;
        this.itemThreshold = itemThreshold;

        assert itemName != null && !itemName.trim().isEmpty();
        assert itemQuantity >= 0;

        commandLogger.info(String.format(
                "Creating Add Command... itemName: %s, itemDescription: %s, itemQuantity: %d, itemExpirationDate: %s"
                        + "itemSalePrice: %f, itemCostPrice: %f, itemThreshold: %d",
                itemName,
                itemDescription,
                itemQuantity,
                itemExpirationDate,
                itemSalePrice,
                itemCostPrice,
                itemThreshold
        ));
    }

    @Override
    public boolean execute(ItemList itemList) {
        executionUiOutput = itemList.addItem(itemType, itemName, itemDescription, itemQuantity, itemExpirationDate,
                itemSalePrice, itemCostPrice, itemThreshold);

        hasToSave = true;
        return true;
    }
}
