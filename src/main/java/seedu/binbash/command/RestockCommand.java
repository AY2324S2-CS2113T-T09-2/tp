package seedu.binbash.command;

import seedu.binbash.ItemList;

import java.util.regex.Pattern;

public class RestockCommand extends Command{
    public static final String COMMAND = "restock";
    public static final Pattern COMMAND_FORMAT = Pattern.compile(
            "restock\\s+"
                    + "n/(?<itemName>.+?)(?=q/)"
                    + "q/(?<restockQuantity>.+)"
    );
    private final String itemName;
    private final int restockQuantity;

    public RestockCommand(ItemList itemList, String itemName, int restockQuantity) {
        super(itemList);
        this.itemName = itemName;
        this.restockQuantity = restockQuantity;

        commandLogger.fine(String.format(
                "Creating Restock Command... itemName: %s, restockQuantity: %d",
                itemName,
                restockQuantity
        ));
    }

    @Override
    public boolean execute() {
        executionUiOutput = itemList.updateItemQuantity(itemName, restockQuantity, COMMAND);
        return true;
    }
}
