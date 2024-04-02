package seedu.binbash.command;

import seedu.binbash.inventory.ItemList;
import seedu.binbash.logger.BinBashLogger;

/**
 * Represents the execution of the list command that will display the inventory in a certain order.
 */
public class ListCommand extends Command {
    private String sortType;

    /**
     * Constructs a ListCommand object that will print the unsorted inventory.
     */
    public ListCommand() {
        commandLogger = new BinBashLogger(ListCommand.class.getName());
        commandLogger.info("Creating List Command...");
        this.sortType =  "none";
    }

    /**
     * Constructs a ListCommand object that will print the sorted inventory based on sort option.
     *
     * @param sortType sorting method used to sort the inventory.
     */
    public ListCommand(String sortType) {
        commandLogger = new BinBashLogger(ListCommand.class.getName());
        commandLogger.info("Creating List Command with sort type " + sortType + "...");
        this.sortType = sortType;
    }

    /**
     * Executes the list command to print the inventory in a certain order.
     *
     * @param itemList ItemList object that the command will execute on.
     * @return true if the command execution is successful.
     */
    public boolean execute(ItemList itemList) {
        switch(sortType) {
        case "e":
            executionUiOutput = itemList.printListSortedByExpiryDate(itemList.getItemList());
            break;
        case "c":
            executionUiOutput = itemList.printListSortedByCostPrice(itemList.getItemList());
            break;
        case "s":
            executionUiOutput = itemList.printListSortedBySalePrice(itemList.getItemList());
            break;
        default:
            executionUiOutput = itemList.printList(itemList.getItemList());
        }

        return true;
    }
}
