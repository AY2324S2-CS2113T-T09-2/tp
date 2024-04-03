package seedu.binbash.command;

import seedu.binbash.exceptions.InvalidArgumentException;
import seedu.binbash.logger.BinBashLogger;
import seedu.binbash.inventory.ItemList;

import java.util.regex.Pattern;

public class SellCommand extends Command{
    public static final String COMMAND = "sell";
    public static final Pattern COMMAND_FORMAT = Pattern.compile(
            "sell\\s+"
                    + "n/(?<itemName>.+?)(?=q/)"
                    + "q/(?<sellQuantity>.+)"
    );
    private String itemName;
    private final int sellQuantity;
    private int index;
    private boolean isIndex;

    public SellCommand(String itemName, int sellQuantity) {
        this.itemName = itemName;
        this.sellQuantity = sellQuantity;

        commandLogger=  new BinBashLogger(SellCommand.class.getName());
        commandLogger.info(String.format(
                "Creating Sell Command... itemName: %s, sellQuantity: %d",
                itemName,
                sellQuantity
        ));
    }

    public SellCommand(int index, int sellQuantity) {
        commandLogger = new BinBashLogger(SellCommand.class.getName());
        this.index = index;
        this.sellQuantity = sellQuantity;

        commandLogger.info(String.format(
                "Creating Sell Command... index: %d, sellQuantity: %d",
                index,
                sellQuantity
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
            commandLogger.info("Sell identifier is detected as an index");
            try {
                executionUiOutput = itemList.sellOrRestockItem(index, sellQuantity, COMMAND);
            } catch (InvalidArgumentException e) {
                executionUiOutput = e.getMessage();
            }
        } else {
            commandLogger.info("Sell identifier is detected as an item name");
            try {
                executionUiOutput = itemList.sellOrRestockItem(itemName, sellQuantity, COMMAND);
            } catch (InvalidArgumentException e) {
                executionUiOutput = e.getMessage();
            }
        }
        hasToSave = true;
        return true;
    }
}
