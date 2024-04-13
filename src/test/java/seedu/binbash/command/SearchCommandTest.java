package seedu.binbash.command;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import seedu.binbash.item.Item;
import seedu.binbash.item.OperationalItem;
import seedu.binbash.item.PerishableOperationalItem;
import seedu.binbash.item.RetailItem;
import seedu.binbash.item.PerishableRetailItem;
import seedu.binbash.inventory.ItemList;

import java.time.LocalDate;
import java.util.ArrayList;

public class SearchCommandTest {
    private final ArrayList<Item> testItemList = new ArrayList<>() {
        {
            add(new OperationalItem("Philips LED PLL 4P2G", "LED tubes for warehouse lighting", 30,
                    10.20)); // index 0
            add(new OperationalItem("Temus Pallet Jack 2XW10 250KG", "Pallet jack for warehouse use", 2,
                    285)); // index 1
            add(new PerishableOperationalItem("Energax 40W AAA", "Batteries for warehouse use", 500,
                    LocalDate.of(2024, 8, 16), 0.10)); // index 2
            add(new RetailItem("Zebra Sarasa Black 0.5", "Black writing pen", 50,
                    1.00, 0.70)); // index 3
            add(new PerishableRetailItem("Cavendish Banana", "Whole bananas, imported from Malaysia", 30,
                    LocalDate.of(2024, 1, 4), 0.50, 0.40)); // index 4
            add(new PerishableRetailItem("Meiji Full Fat Pasturized 1.5L", "Pasturized whole dairy milk", 10,
                    LocalDate.of(2024, 2, 3), 5, 3.50)); // index 5
            add(new RetailItem("Steely Hans 3P66", "3-inch Shears for gardening", 15,
                    6.50, 4.50)); // index 6
            add(new PerishableRetailItem("Red Banana", "Imported from Indonesia", 20,
                    LocalDate.of(2024, 1, 6), 0.70, 0.60)); // index 7
        }
    };

    @Test
    void execute_searchThroughFields_correctlyListsBatteriesBanana() {
        SearchCommand searchCommand = new SearchCommand();
        searchCommand.setNameField("en");
        searchCommand.setDescriptionField("w");
        int[] quantityRange = {20, Integer.MAX_VALUE};
        searchCommand.setQuantityRange(quantityRange);
        double[] costPriceRange = {Double.MIN_VALUE, 5};
        searchCommand.setCostPriceRange(costPriceRange);

        ItemList dummyItemList = new ItemList(testItemList);
        searchCommand.execute(dummyItemList);
        String actualOutput = searchCommand.getExecutionUiOutput();

        String expectedOutput = dummyItemList.printList(new ArrayList<>() {
            {
                add(testItemList.get(2));
                add(testItemList.get(4));
            }
        });
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void execute_searchForNonExistingItem_returnsEmptyList() {
        SearchCommand searchCommand = new SearchCommand();
        searchCommand.setNameField("NonExistingItemName");
        searchCommand.setDescriptionField("NonExistingDescription");
        int[] quantityRange = {0, 10};
        searchCommand.setQuantityRange(quantityRange);
        double[] costPriceRange = {0, 100};
        searchCommand.setCostPriceRange(costPriceRange);

        ItemList dummyItemList = new ItemList(testItemList);
        searchCommand.execute(dummyItemList);
        String actualOutput = searchCommand.getExecutionUiOutput();

        Assertions.assertTrue(actualOutput.isBlank());
    }

    @Test
    void execute_searchWithEmptyCriteria_returnsAllItems() {
        SearchCommand searchCommand = new SearchCommand();
        ItemList dummyItemList = new ItemList(testItemList);
        searchCommand.execute(dummyItemList);
        String actualOutput = searchCommand.getExecutionUiOutput();

        String expectedOutput = dummyItemList.printList(testItemList);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void execute_searchWithMaxResults_returnsCorrectNumberOfResults() {
        SearchCommand searchCommand = new SearchCommand();
        searchCommand.setNumberOfResults(3); // Set max results to 3
        ItemList dummyItemList = new ItemList(testItemList);
        searchCommand.execute(dummyItemList);
        String actualOutput = searchCommand.getExecutionUiOutput();

        String expectedOutput = dummyItemList.printList(testItemList.subList(0, 3));
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void execute_searchWithNegativeQuantityRange_returnsEmptyList() {
        SearchCommand searchCommand = new SearchCommand();
        int[] negativeQuantityRange = {-10, -5};
        searchCommand.setQuantityRange(negativeQuantityRange);
        ItemList dummyItemList = new ItemList(testItemList);
        searchCommand.execute(dummyItemList);
        String actualOutput = searchCommand.getExecutionUiOutput();

        Assertions.assertTrue(actualOutput.isBlank());
    }

    @Test
    void execute_searchWithNegativeCostPriceRange_returnsEmptyList() {
        SearchCommand searchCommand = new SearchCommand();
        double[] negativeCostPriceRange = {-100, -50};
        searchCommand.setCostPriceRange(negativeCostPriceRange);

        ItemList dummyItemList = new ItemList(testItemList);
        searchCommand.execute(dummyItemList);
        String actualOutput = searchCommand.getExecutionUiOutput();

        Assertions.assertTrue(actualOutput.isBlank());
    }

    @Test
    void execute_searchWithExactCostPrice_returnsMatchingItems() {
        SearchCommand searchCommand = new SearchCommand();
        double[] exactCostPriceRange = {10.20, 10.20}; // Exact cost price of Philips LED PLL 4P2G
        searchCommand.setCostPriceRange(exactCostPriceRange);

        ItemList dummyItemList = new ItemList(testItemList);
        searchCommand.execute(dummyItemList);
        String actualOutput = searchCommand.getExecutionUiOutput();

        String expectedOutput = dummyItemList.printList(new ArrayList<>() {
            {
                add(testItemList.get(0)); // Philips LED PLL 4P2G
            }
        });
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void execute_searchWithNegativeSalePriceRange_returnsEmptyList() {
        SearchCommand searchCommand = new SearchCommand();
        double[] negativeSalePriceRange = {-10, -5};
        searchCommand.setSalePriceRange(negativeSalePriceRange);

        ItemList dummyItemList = new ItemList(testItemList);
        searchCommand.execute(dummyItemList);
        String actualOutput = searchCommand.getExecutionUiOutput();

        Assertions.assertTrue(actualOutput.isBlank());
    }
}
