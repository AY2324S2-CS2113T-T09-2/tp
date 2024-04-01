package seedu.binbash.command;

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
    private static final BinBashLogger binBashLogger = new BinBashLogger(SellCommand.class.getName());
    private final String itemName;
    private final int sellQuantity;

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

    @Override
    public boolean execute(ItemList itemList) {
        executionUiOutput = itemList.updateItemQuantity(itemName, sellQuantity, COMMAND);
        hasToSave = true;
        return true;
    }
}
