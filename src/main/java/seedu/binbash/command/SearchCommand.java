package seedu.binbash.command;

import seedu.binbash.item.Item;
import seedu.binbash.inventory.ItemList;
import seedu.binbash.logger.BinBashLogger;

import java.util.ArrayList;
import java.time.LocalDate;

/**
 * Represents the execution of a search command that searches through the user's inventory to return matching results.
 */
public class SearchCommand extends Command {
    private String nameField = "";
    private String descriptionField = "";
    private int[] quantityRange = {Integer.MIN_VALUE, Integer.MAX_VALUE};
    private double[] costPriceRange = {Double.MIN_VALUE, Double.MAX_VALUE};
    private double[] salePriceRange = {Double.MIN_VALUE, Double.MAX_VALUE};
    private LocalDate[] expiryDateRange = {LocalDate.MIN, LocalDate.MAX};
    private int numberOfResults = Integer.MAX_VALUE;

    public SearchCommand() {
        commandLogger = new BinBashLogger(SearchCommand.class.getName());
        commandLogger.info(String.format(
                "Creating Search Command..."
        ));
    }

    /**
     * Sets the name field of the search command.
     * The SearchAssistant will search for items using the name provided in this field.
     *
     * @param nameField The item name to search for.
     */
    public void setNameField(String nameField) {
        this.nameField = nameField;
    }

    /**
     * Sets the description field of the search command.
     * The SearchAssistant will search for items using the description provided in this field.
     *
     * @param descriptionField The item description to search for.
     */
    public void setDescriptionField(String descriptionField) {
        this.descriptionField = descriptionField;
    }

    /**
     * Sets the quantity range of the search command.
     * The SearchAssistant will search for items using the quantity range provided.
     *
     * @param quantityRange The item quantity range to search for.
     */
    public void setQuantityRange(int[] quantityRange) {
        this.quantityRange = quantityRange;
    }

    /**
     * Sets the cost price range of the search command.
     * The SearchAssistant will search for items using the cost price range provided.
     *
     * @param costPriceRange The item quantity range to search for.
     */
    public void setCostPriceRange(double[] costPriceRange) {
        this.costPriceRange = costPriceRange;
    }

    /**
     * Sets the sale price range of the search command.
     * The SearchAssistant will search for items using the sale price range provided.
     *
     * @param salePriceRange The item quantity range to search for.
     */
    public void setSalePriceRange(double[] salePriceRange) {
        this.salePriceRange = salePriceRange;
    }

    /**
     * Sets the expiry date range of the search command.
     * The SearchAssistant will search for items using the expiry date range provided.
     *
     * @param expiryDateRange The item quantity range to search for.
     */
    public void setExpiryDateRange(LocalDate[] expiryDateRange) {
        this.expiryDateRange = expiryDateRange;
    }

    /**
     * Sets the maximum number of search results to show.
     *
     * @param numberOfResults The maximum number of search results to be shown.
     */
    public void setNumberOfResults(int numberOfResults) {
        this.numberOfResults = numberOfResults;
    }

    /**
     * Finds all items that match a given query then converts them into a string to present to the user.
     *
     * @param itemList The itemList instance with which to search through the master item list.
     * @return True if the number of found items is no more than numberOfResults, false otherwise.
     */
    public boolean execute(ItemList itemList) {
        ArrayList<Item> foundItems = itemList.getSearchAssistant()
            .searchByName(nameField)
            .searchByDescription(descriptionField)
            .searchByQuantityBetween(quantityRange[0], quantityRange[1])
            .searchByCostPriceBetween(costPriceRange[0], costPriceRange[1])
            .searchBySalePriceBetween(salePriceRange[0], salePriceRange[1])
            .searchByExpiryDateBetween(expiryDateRange[0], expiryDateRange[1])
            .getFoundItems(numberOfResults);
        executionUiOutput = itemList.printList(foundItems);
        return foundItems.size() <= numberOfResults;
    }
}
