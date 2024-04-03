package seedu.binbash.command;

import seedu.binbash.exceptions.InvalidArgumentException;
import seedu.binbash.logger.BinBashLogger;
import seedu.binbash.inventory.ItemList;

import java.util.regex.Pattern;

/**
 * Represents the execution of a restock command that will increase the quantity of an Item.
 */
public class RestockCommand extends Command{
    public static final String COMMAND = "restock";
    public static final Pattern COMMAND_FORMAT = Pattern.compile(
            "restock\\s+"
                    + "n/(?<itemName>.+?)(?=q/)"
                    + "q/(?<restockQuantity>.+)"
    );
    private String itemName;
    private int restockQuantity;
    private int index;
    private boolean isIndex = false;

    public RestockCommand(String itemName, int restockQuantity) {
        this.itemName = itemName;
        this.restockQuantity = restockQuantity;

        commandLogger = new BinBashLogger(RestockCommand.class.getName());
        commandLogger.info(String.format(
                "Creating Restock Command... itemName: %s, restockQuantity: %d",
                itemName,
                restockQuantity
        ));
    }

    public RestockCommand(int index, int restockQuantity) {
        commandLogger = new BinBashLogger(RestockCommand.class.getName());
        this.index = index;
        this.restockQuantity = restockQuantity;

        commandLogger.info(String.format(
                "Creating Restock Command... index: %d, restockQuantity: %d",
                index,
                restockQuantity
        ));
    }

    public void setIsIndex() {
        this.isIndex = true;
    }

    @Override
    public boolean execute(ItemList itemList) {
        if (isIndex) {
            if (index <= 0 || index > itemList.getItemCount()) {
                commandLogger.info("Index entered is out of bounds");
                executionUiOutput = "Index entered is out of bounds!";
                return true;
            }
            assert index > 0 && index <= itemList.getItemCount();
            commandLogger.info("Restock identifier is detected as an index");
            try {
                executionUiOutput = itemList.sellOrRestockItem(index, restockQuantity, COMMAND);
            } catch (InvalidArgumentException e) {
                executionUiOutput = e.getMessage();
            }
        } else {
            commandLogger.info("Restock identifier is detected as an item name");
            try {
                executionUiOutput = itemList.sellOrRestockItem(itemName, restockQuantity, COMMAND);
            } catch (InvalidArgumentException e) {
                executionUiOutput = e.getMessage();
            }
        }
        hasToSave = true;
        return true;
    }
}
