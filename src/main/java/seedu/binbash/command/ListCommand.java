package seedu.binbash.command;

import seedu.binbash.enums.SortOptionEnum;
import seedu.binbash.inventory.ItemList;
import seedu.binbash.item.Item;
import seedu.binbash.logger.BinBashLogger;

import java.util.List;

/**
 * Represents the execution of the list command that will display the inventory in a certain order.
 */
public class ListCommand extends Command {
    private SortOptionEnum sortOption;

    /**
     * Constructs a ListCommand object that will print the unsorted inventory.
     */
    public ListCommand() {
        commandLogger = new BinBashLogger(ListCommand.class.getName());
        commandLogger.info("Creating List Command...");
        this.sortOption =  SortOptionEnum.NONE;
    }

    /**
     * Constructs a ListCommand object that will print the sorted inventory based on sort option.
     *
     * @param sortOption sorting method used to sort the inventory.
     */
    public ListCommand(SortOptionEnum sortOption) {
        commandLogger = new BinBashLogger(ListCommand.class.getName());
        commandLogger.info("Creating List Command with sort type " + sortOption + "...");
        this.sortOption = sortOption;
    }

    /**
     * Executes the list command to print the inventory in a certain order.
     *
     * @param itemList ItemList object that the command will execute on.
     * @return true if the command execution is successful.
     */
    public boolean execute(ItemList itemList) {
        List<Item> itemArrayList = itemList.getItemList();
        switch(sortOption) {
        case EXPIRY:
            commandLogger.info("Executing list sort by expiry date...");
            executionUiOutput = itemList.printListSortedByExpiryDate(itemArrayList);
            break;
        case COST:
            commandLogger.info("Executing list sort by cost price...");
            executionUiOutput = itemList.printListSortedByCostPrice(itemArrayList);
            break;
        case SALE:
            commandLogger.info("Executing list sort by sale price...");
            executionUiOutput = itemList.printListSortedBySalePrice(itemArrayList);
            break;
        case PROFIT:
            commandLogger.info("Executing list sort by profits earned...");
            executionUiOutput = itemList.printListSortedByProfit(itemArrayList);
            break;
        default:
            commandLogger.info("Executing list unsorted...");
            executionUiOutput = itemList.printList(itemArrayList);
        }

        return true;
    }
}
