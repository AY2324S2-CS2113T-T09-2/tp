package seedu.binbash.command;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;

import seedu.binbash.item.Item;
import seedu.binbash.item.OperationalItem;
import seedu.binbash.item.PerishableOperationalItem;
import seedu.binbash.item.RetailItem;
import seedu.binbash.item.PerishableRetailItem;
import seedu.binbash.ItemList;

import java.time.LocalDate;
import java.util.ArrayList;

public class SearchCommandTest {
    private final ArrayList<Item> testItemList = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        testItemList.add(new OperationalItem("light bulb", "a light bulb used in the warehouse",
                    100, 0.20));
        testItemList.add(new PerishableOperationalItem("battery", "a battery for in-store use", 500,
                    LocalDate.of(2024, 1, 16), 0.10));
        testItemList.add(new RetailItem("black pen", "zebra sarasa black 0.5", 50, 1.00, 0.70));
        testItemList.add(new PerishableRetailItem("banana", "cavendish banana", 30,
                    LocalDate.of(2024, 1, 4), 0.50, 0.40));
        testItemList.add(new PerishableRetailItem("milk", "meiji full fat whole milk", 10,
                    LocalDate.of(2024, 2, 3), 5, 3.50));
    }

    @Test
    void execute_searchForMilk_found() {
        ItemList itemList = new ItemList(testItemList);
        SearchCommand searchCommand = new SearchCommand();
        searchCommand.setNameField("milk");

        searchCommand.execute(itemList);
        ArrayList<Item> foundItems = searchCommand.getFoundItems();
        String foundItem = foundItems.get(0).getItemName();
        Assertions.assertTrue(foundItem.equals("milk") && foundItems.size() == 1);
    }

    @Test
    void execute_searchForExpiry_foundBanana() {
        ItemList itemList = new ItemList(testItemList);
        SearchCommand searchCommand = new SearchCommand();
        searchCommand.setExpiryDateField(LocalDate.of(2024, 2, 4));

        searchCommand.execute(itemList);
        ArrayList<Item> foundItems = searchCommand.getFoundItems();
        String foundItem = foundItems.get(0).getItemName();
        Assertions.assertTrue(foundItem.equals("banana") && foundItems.size() == 1);
    }
}
