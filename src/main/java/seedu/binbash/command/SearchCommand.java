package seedu.binbash.command;

import seedu.binbash.item.Item;
import seedu.binbash.inventory.ItemList;
import seedu.binbash.logger.BinBashLogger;

import java.util.ArrayList;
import java.time.LocalDate;

public class SearchCommand extends Command {
    private String nameField = "";
    private String descriptionField = "";
    private double[] costPriceRange = {Double.MIN_VALUE, Double.MAX_VALUE};
    private double[] salePriceRange = {Double.MIN_VALUE, Double.MAX_VALUE};
    private LocalDate[] expiryDateRange = {LocalDate.MIN, LocalDate.MAX};
    private int numberOfResults = Integer.MAX_VALUE;
    private ArrayList<Item> foundItems;

    public SearchCommand() {
        commandLogger = new BinBashLogger(SearchCommand.class.getName());
        commandLogger.info(String.format(
                "Creating Search Command..."
        ));
    }

    public void setNameField(String nameField) {
        this.nameField = nameField;
    }

    public void setDescriptionField(String descriptionField) {
        this.descriptionField = descriptionField;
    }

    public void setCostPriceRange(double[] costPriceRange) {
        this.costPriceRange = costPriceRange;
    }

    public void setSalePriceRange(double[] salePriceRange) {
        this.salePriceRange = salePriceRange;
    }

    public void setExpiryDateRange(LocalDate[] expiryDateRange) {
        this.expiryDateRange = expiryDateRange;
    }

    public void setNumberOfResults(int numberOfResults) {
        this.numberOfResults = numberOfResults;
    }

    public ArrayList<Item> getFoundItems() {
        return foundItems;
    }

    public boolean execute(ItemList itemList) {
        foundItems = itemList.getSearchAssistant()
            .searchByName(nameField)
            .searchByDescription(descriptionField)
            .searchByCostPrice(costPriceRange[0], costPriceRange[1])
            .searchBySalePrice(salePriceRange[0], salePriceRange[1])
            .searchByExpiryDateBetween(expiryDateRange[0], expiryDateRange[1])
            .getFoundItems(numberOfResults);
        executionUiOutput = itemList.printList(foundItems);
        return true;
    }
}
