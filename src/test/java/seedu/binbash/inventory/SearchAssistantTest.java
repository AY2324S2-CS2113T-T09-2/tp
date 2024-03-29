package seedu.binbash.inventory;

import seedu.binbash.item.Item;
import seedu.binbash.item.OperationalItem;
import seedu.binbash.item.PerishableOperationalItem;
import seedu.binbash.item.RetailItem;
import seedu.binbash.item.PerishableRetailItem;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.time.LocalDate;

public class SearchAssistantTest {
    private final SearchAssistant searchAssistant = new SearchAssistant();

    @BeforeEach
    public void setUp() {
        ArrayList<Item> testItemList = new ArrayList<>();
        testItemList.add(new OperationalItem("light bulb", "a light bulb used in the warehouse",
                    100, 0.20));
        testItemList.add(new PerishableOperationalItem("battery", "a battery for in-store use", 500,
                    LocalDate.of(2024, 1, 16), 0.10));
        testItemList.add(new RetailItem("black pen", "zebra sarasa black 0.5", 50, 1.00, 0.70));
        testItemList.add(new PerishableRetailItem("banana", "cavendish banana", 30,
                    LocalDate.of(2024, 1, 4), 0.50, 0.40));
        testItemList.add(new PerishableRetailItem("milk", "meiji full fat whole milk", 10,
                    LocalDate.of(2024, 2, 3), 5, 3.50));
        searchAssistant.setFoundItems(testItemList);
        searchAssistant.setNumberOfResults(testItemList.size());
    }

    @Test
    public void searchByName_banana_success() {
        ArrayList<Item> foundItems = searchAssistant.searchByName("banana")
            .getFoundItems();
        String foundItemName = foundItems.get(0).getItemName();
        Assertions.assertTrue(foundItemName.contains("banana"));
    }

    @Test
    public void searchByDescription_sarasa_success() {
        ArrayList<Item> foundItems = searchAssistant.searchByDescription("sarasa")
            .getFoundItems();
        String foundItemDescription = foundItems.get(0).getItemDescription();
        Assertions.assertTrue(foundItemDescription.contains("sarasa"));
    }

    @Test
    public void searchByCostPrice_everythingMoreThan50Cents_foundPenAndMilk() {
        ArrayList<Item> foundItems = searchAssistant.searchByCostPrice(0.50)
            .getFoundItems();
        String firstItemFound = foundItems.get(0).getItemName();
        String secondItemFound = foundItems.get(1).getItemName();
        Assertions.assertTrue(firstItemFound.equals("black pen") &&
                secondItemFound.equals("milk") && foundItems.size() == 2);
    }

    @Test
    public void searchBySalePrice_everythingLessThan60Cents_foundBanana() {
        ArrayList<Item> foundItems = searchAssistant.searchBySalePrice(-0.60)
            .getFoundItems();
        String firstItemFound = foundItems.get(0).getItemName();
        Assertions.assertTrue(firstItemFound.equals("banana") &&
                foundItems.size() == 1);
    }

    @Test
    public void searchByExpiryDate_everythingBefore20Jan_foundBanana() {
        ArrayList<Item> foundItems = searchAssistant.searchByExpiryDate(LocalDate.of(2024, 1, 20))
            .getFoundItems();
        String firstItemFound = foundItems.get(0).getItemName();
        Assertions.assertTrue(firstItemFound.equals("banana") && foundItems.size() == 1);
    }
}
