package seedu.binbash.command;

import seedu.binbash.logger.BinBashLogger;
import seedu.binbash.ItemList;

import java.util.regex.Pattern;

public class RestockCommand extends Command{
    public static final String COMMAND = "restock";
    public static final Pattern COMMAND_FORMAT = Pattern.compile(
            "restock\\s+"
                    + "n/(?<itemName>.+?)(?=q/)"
                    + "q/(?<restockQuantity>.+)"
    );
    private static final BinBashLogger binBashLogger = new BinBashLogger(RestockCommand.class.getName());
    private final String itemName;
    private final int restockQuantity;

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

    @Override
    public boolean execute(ItemList itemList) {
        executionUiOutput = itemList.updateItemQuantity(itemName, restockQuantity, COMMAND);
        hasToSave = true;
        return true;
    }
}
