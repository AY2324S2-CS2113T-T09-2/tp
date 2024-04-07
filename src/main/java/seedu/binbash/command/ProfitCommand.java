package seedu.binbash.command;

import seedu.binbash.inventory.ItemList;
import seedu.binbash.logger.BinBashLogger;

/**
 * Represents the execution of a profit command that returns the total profit margin of the user.
 */
public class ProfitCommand extends Command {
    public ProfitCommand() {
        commandLogger = new BinBashLogger(ProfitCommand.class.getName());
        commandLogger.info("Creating Profit Command...");
    }

    public boolean execute(ItemList itemList) {
        double totalProfit = calculateTotalProfit(itemList);
        executionUiOutput = String.format("Total profit: $%.2f%n", totalProfit);
        return true;
    }

    /**
     * Calculates the total lifetime profit margin of the user.
     *
     * @param itemList ItemList object that the command executes on.
     * @return The total profit margin of the user.
     */
    public double calculateTotalProfit(ItemList itemList) {
        double totalRevenue = itemList.getTotalRevenue();
        double totalCost = itemList.getTotalCost();
        return totalRevenue - totalCost;
    }
}
