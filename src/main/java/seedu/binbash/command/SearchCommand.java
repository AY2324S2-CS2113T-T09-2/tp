package seedu.binbash.command;

import seedu.binbash.item.Item;
import seedu.binbash.ItemList;

import java.util.ArrayList;
import java.time.LocalDate;

public class SearchCommand extends Command {
    private String nameField = "";
    private String descriptionField = "";
    private double costPriceField = 0.00;
    private double salePriceField = 0.00;
    private LocalDate expiryDateField = LocalDate.MAX;
    private int numberOfResults = 1;

    public SearchCommand() {
        commandLogger.fine(String.format(
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

    public boolean execute(ItemList itemList) {
        ArrayList<Item> foundItems = itemList.getSearchAssistant(numberOfResults)
            .searchByName(nameField)
            .searchByDescription(descriptionField)
            .searchByCostPrice(costPriceField)
            .searchBySalePrice(salePriceField)
            .searchByExpiryDate(expiryDateField)
            .getFoundItems();
        executionUiOutput = itemList.printList(foundItems);
        return true;
    }
}
