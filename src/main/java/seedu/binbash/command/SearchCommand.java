package seedu.binbash.command;

import seedu.binbash.item.Item;
import seedu.binbash.inventory.ItemList;
import seedu.binbash.logger.BinBashLogger;

import java.util.ArrayList;
import java.time.LocalDate;

public class SearchCommand extends Command {
    private String nameField = "";
    private String descriptionField = "";
    private double costPriceField = 0.00;
    private double salePriceField = 0.00;
    private LocalDate expiryDateField = LocalDate.MAX;
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

    public void setCostPriceField(double costPriceField) {
        this.costPriceField = costPriceField;
    }

    public void setSalePriceField(double salePriceField) {
        this.salePriceField = salePriceField;
    }

    public void setExpiryDateField(LocalDate expiryDateField) {
        this.expiryDateField = expiryDateField;
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
            .searchByCostPrice(costPriceField)
            .searchBySalePrice(salePriceField)
            .searchByExpiryDate(expiryDateField)
            .getFoundItems(numberOfResults);
        executionUiOutput = itemList.printList(foundItems);
        return true;
    }
}
