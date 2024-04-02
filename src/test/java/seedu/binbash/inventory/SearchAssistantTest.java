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
            add(new PerishableRetailItem("Idido Yirgacheffe Ethiopia", "Coffee beans with Q-Grade score 85", 40,
                        LocalDate.of(2024, 11, 24), 30, 20)); // index 8
            add(new OperationalItem("Philips PDIODE 14Lum", "Specialised diodes for corner lighting", 4,
                        82.45)); // index 9
        }
    };

    @BeforeEach
    public void setUp() {
        searchAssistant.setFoundItems(testItemList);
    }

    @Test
    public void searchByName_banana_found2() {
        ArrayList<Item> foundItems = searchAssistant.searchByName("banana")
            .getFoundItems();
        Assertions.assertEquals(foundItems.size(), 2);
        Assertions.assertEquals(foundItems.get(0), testItemList.get(4));
        Assertions.assertEquals(foundItems.get(1), testItemList.get(7));
    }

    @Test
    public void searchByQuantity_between25And40_foundLEDBananaCoffee() {
        ArrayList<Item> foundItems = searchAssistant.searchByQuantityBetween(25, 40)
            .getFoundItems();
        Assertions.assertEquals(foundItems.size(), 3);
        Assertions.assertEquals(foundItems.get(0), testItemList.get(0));
        Assertions.assertEquals(foundItems.get(1), testItemList.get(4));
        Assertions.assertEquals(foundItems.get(2), testItemList.get(8));
    }

    @Test
    public void searchByCostPrice_between4and10_foundLEDShearsAndCoffee() {
        ArrayList<Item> foundItems = searchAssistant.searchByCostPriceBetween(4, 20)
            .getFoundItems();
        Assertions.assertEquals(foundItems.size(), 3);
        Assertions.assertEquals(foundItems.get(0), testItemList.get(0));
        Assertions.assertEquals(foundItems.get(1), testItemList.get(6));
        Assertions.assertEquals(foundItems.get(2), testItemList.get(8));
    }

    @Test
    public void searchBySalePrice_from1To10_foundPenMilkShears() {
        ArrayList<Item> foundItems = searchAssistant.searchBySalePriceBetween(1, 10)
            .getFoundItems();
        Assertions.assertEquals(foundItems.size(), 3);
        Assertions.assertEquals(foundItems.get(0), testItemList.get(3));
        Assertions.assertEquals(foundItems.get(1), testItemList.get(5));
        Assertions.assertEquals(foundItems.get(2), testItemList.get(6));
    }

    @Test
    public void searchByExpiryDate_before20Jan2024_foundBananas() {
        ArrayList<Item> foundItems = searchAssistant.searchByExpiryDateTo(LocalDate.of(2024, 1, 20))
            .getFoundItems();
        Assertions.assertEquals(foundItems.size(), 2);
        Assertions.assertEquals(foundItems.get(0), testItemList.get(4));
        Assertions.assertEquals(foundItems.get(1), testItemList.get(7));
    }

    @Test
    public void searchByDescriptionThenName_warehouse_foundLEDAndBatteries() {
        ArrayList<Item> foundItems = searchAssistant.searchByDescription("warehouse")
            .searchByName("4")
            .getFoundItems();
        Assertions.assertEquals(foundItems.size(), 2);
        Assertions.assertEquals(foundItems.get(0), testItemList.get(0));
        Assertions.assertEquals(foundItems.get(1), testItemList.get(2));
    }

    @Test
    public void searchByQuantityThenExpiryDate_from10After15June2024_foundBatteryCoffee() {
        ArrayList<Item> foundItems = searchAssistant.searchByQuantityFrom(10)
            .searchByExpiryDateFrom(LocalDate.of(2024, 6, 15))
            .getFoundItems();
        Assertions.assertEquals(foundItems.size(), 2);
        Assertions.assertEquals(foundItems.get(0), testItemList.get(2));
        Assertions.assertEquals(foundItems.get(1), testItemList.get(8));
    }

    @Test
    public void searchByCostPriceThenSalePrice_from30CTo1D_foundItems() {
        ArrayList<Item> foundItems = searchAssistant.searchByCostPriceFrom(0.30)
            .searchBySalePriceTo(1)
            .getFoundItems();
        Assertions.assertEquals(foundItems.size(), 3);
        Assertions.assertEquals(foundItems.get(0), testItemList.get(3));
        Assertions.assertEquals(foundItems.get(1), testItemList.get(4));
        Assertions.assertEquals(foundItems.get(2), testItemList.get(7));
    }

    @Test
    public void searchByCostPriceThenExpiryDateBetween_from50CBetween15JanAnd15Feb_foundMilk() {
        ArrayList<Item> foundItems = searchAssistant.searchByCostPriceFrom(0.5)
            .searchByExpiryDateBetween(LocalDate.of(2024, 1, 15), LocalDate.of(2024, 2, 15))
            .getFoundItems();
        Assertions.assertEquals(foundItems.size(), 1);
        Assertions.assertEquals(foundItems.get(0), testItemList.get(5));
    }

    @Test
    public void searchByNameThenCost_philipsMoreThan80Dollars_foundDiode() {
        ArrayList<Item> foundItems = searchAssistant.searchByName("philips")
            .searchByCostPriceFrom(80)
            .getFoundItems();
        Assertions.assertEquals(foundItems.size(), 1);
        Assertions.assertEquals(foundItems.get(0), testItemList.get(9));
    }

    @Test
    public void searchAllFields_placeHolderValues_unmodifiedItemList() {
        ArrayList<Item> foundItems = searchAssistant.searchByName("")
            .searchByDescription("")
            .searchByQuantityBetween(Integer.MIN_VALUE, Integer.MAX_VALUE)
            .searchByCostPriceBetween(Double.MIN_VALUE, Double.MAX_VALUE)
            .searchBySalePriceBetween(Double.MIN_VALUE, Double.MAX_VALUE)
            .searchByExpiryDateBetween(LocalDate.MIN, LocalDate.MAX)
            .getFoundItems(Integer.MAX_VALUE);
        Assertions.assertEquals(testItemList.size(), foundItems.size());
    }
}
