package seedu.binbash.comparators;

import org.junit.jupiter.api.Test;
import seedu.binbash.item.PerishableRetailItem;
import seedu.binbash.item.RetailItem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemComparatorByProfitTest {

    @Test
    public void compare_itemsWithDifferentProfits_SortedByDescendingProfit() {
        List<RetailItem> itemList = new ArrayList<>();
        RetailItem item1 = new RetailItem("Item1", "Description1", 10, 15.0, 5.0);
        item1.setTotalUnitsPurchased(10);
        item1.setTotalUnitsSold(10);

        RetailItem item2 = new RetailItem("Item2", "Description2", 20, 10.0, 4.0);
        item2.setTotalUnitsPurchased(15);
        item2.setTotalUnitsSold(15);

        PerishableRetailItem item3 = new PerishableRetailItem("Item3", "Description3", 15, LocalDate.now(), 20.0, 6.0);
        item3.setTotalUnitsPurchased(10);
        item3.setTotalUnitsSold(10);

        itemList.add(item1);
        itemList.add(item2);
        itemList.add(item3);

        itemList.sort(new ItemComparatorByProfit());

        assertEquals("Item3", itemList.get(0).getItemName());
        assertEquals("Item1", itemList.get(1).getItemName());
        assertEquals("Item2", itemList.get(2).getItemName());
    }

    @Test
    public void compare_itemsWithEqualProfits_sortedByInsertionOrder() {
        List<RetailItem> itemList = new ArrayList<>();
        RetailItem item1 = new RetailItem("Item1", "Description1", 10, 15.0, 5.0);
        item1.setTotalUnitsPurchased(10);
        item1.setTotalUnitsSold(10);

        RetailItem item2 = new RetailItem("Item2", "Description2", 10, 15.0, 5.0);
        item2.setTotalUnitsPurchased(10);
        item2.setTotalUnitsSold(10);

        itemList.add(item1);
        itemList.add(item2);

        itemList.sort(new ItemComparatorByProfit());

        assertEquals("Item1", itemList.get(0).getItemName());
        assertEquals("Item2", itemList.get(1).getItemName());
    }

    @Test
    public void compare_itemsWithNegativeProfit_sortedByDescendingProfit() {
        List<RetailItem> itemList = new ArrayList<>();
        RetailItem item1 = new RetailItem("Item1", "Description1", 10, 5.0, 15.0);
        item1.setTotalUnitsPurchased(10);
        item1.setTotalUnitsSold(10);

        RetailItem item2 = new RetailItem("Item2", "Description2", 10, 10.0, 5.0);
        item2.setTotalUnitsPurchased(10);
        item2.setTotalUnitsSold(10);

        itemList.add(item1);
        itemList.add(item2);

        itemList.sort(new ItemComparatorByProfit());

        assertEquals("Item2", itemList.get(0).getItemName());
        assertEquals("Item1", itemList.get(1).getItemName());
    }
}
