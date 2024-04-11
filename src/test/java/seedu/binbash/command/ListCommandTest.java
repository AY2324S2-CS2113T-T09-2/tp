package seedu.binbash.command;

import org.junit.jupiter.api.Test;

import seedu.binbash.enums.SortOptionEnum;
import seedu.binbash.inventory.ItemList;
import seedu.binbash.item.Item;
import seedu.binbash.item.RetailItem;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListCommandTest {
    @Test
    void execute_listCommandWithTwoItemsInItemList_correctPrintFormatForBothItems() {
        ItemList itemList = new ItemList(new ArrayList<Item>());

        itemList.addItem("retail", "testItem1", "Test item 1", 2,
                LocalDate.of(1999, 1, 1), 4.00, 5.00, 6);
        itemList.addItem("retail", "testItem2", "Test item 2", 6,
                LocalDate.of(1999, 1, 1), 8.00, 9.00, 10);

        ListCommand listCommand = new ListCommand();

        listCommand.execute(itemList);
        String actualOutput = listCommand.getExecutionUiOutput();

        String expectedOutput = "1. [P][R] testItem1" + System.lineSeparator()
                + "\tdescription: Test item 1" + System.lineSeparator()
                + "\tquantity: 2" + System.lineSeparator()
                + "\tcost price: $5.00" + System.lineSeparator()
                + "\tsale price: $4.00" + System.lineSeparator()
                + "\tthreshold: 6" + System.lineSeparator()
                + "\texpiry date: 01-01-1999" + System.lineSeparator()
                + System.lineSeparator()
                + "2. [P][R] testItem2" + System.lineSeparator()
                + "\tdescription: Test item 2" + System.lineSeparator()
                + "\tquantity: 6" + System.lineSeparator()
                + "\tcost price: $9.00" + System.lineSeparator()
                + "\tsale price: $8.00" + System.lineSeparator()
                + "\tthreshold: 10" + System.lineSeparator()
                + "\texpiry date: 01-01-1999" + System.lineSeparator()
                + System.lineSeparator();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void execute_listCommandWithEmptyItemList_returnsEmptyOutput() {
        ItemList itemList = new ItemList(new ArrayList<Item>());
        ListCommand listCommand = new ListCommand();

        listCommand.execute(itemList);
        String actualOutput = listCommand.getExecutionUiOutput();

        String expectedOutput = "";

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void execute_sortByCostPrice_returnsSortedList() {
        ArrayList<Item> inventory = new ArrayList<Item>();
        ItemList itemList = new ItemList(inventory);
        ListCommand listCommandCostPrice = new ListCommand(SortOptionEnum.COST);

        itemList.addItem("retail", "testItem1", "Test item 1", 2,
                LocalDate.of(2024, 1, 1), 10.00, 5.00, 10);
        itemList.addItem("retail", "testItem2", "Test item 2", 2,
                LocalDate.of(2024, 1, 1), 3.00, 2.00, 10);

        listCommandCostPrice.execute(itemList);
        String actualOutput = listCommandCostPrice.getExecutionUiOutput();

        String expectedOutput = "1. [P][R] testItem2" + System.lineSeparator()
                + "\tdescription: Test item 2" + System.lineSeparator()
                + "\tquantity: 2" + System.lineSeparator()
                + "\tcost price: $2.00" + System.lineSeparator()
                + "\tsale price: $3.00" + System.lineSeparator()
                + "\tthreshold: 10" + System.lineSeparator()
                + "\texpiry date: 01-01-2024" + System.lineSeparator()
                + System.lineSeparator()
                + "2. [P][R] testItem1" + System.lineSeparator()
                + "\tdescription: Test item 1" + System.lineSeparator()
                + "\tquantity: 2" + System.lineSeparator()
                + "\tcost price: $5.00" + System.lineSeparator()
                + "\tsale price: $10.00" + System.lineSeparator()
                + "\tthreshold: 10" + System.lineSeparator()
                + "\texpiry date: 01-01-2024" + System.lineSeparator()
                + System.lineSeparator();

        assertEquals(expectedOutput,actualOutput);
    }

    @Test
    void execute_sortByExpiryDate_returnsSortedList() {
        ArrayList<Item> inventory = new ArrayList<Item>();
        ItemList itemList = new ItemList(inventory);
        ListCommand listCommandCostPrice = new ListCommand(SortOptionEnum.EXPIRY);

        itemList.addItem("retail", "testItem1", "Test item", 2,
                LocalDate.of(2024, 1, 5), 3.00, 2.00, 10);
        itemList.addItem("retail", "testItem2", "Test item", 2,
                LocalDate.of(2024, 1, 1), 3.00, 2.00, 10);

        listCommandCostPrice.execute(itemList);
        String actualOutput = listCommandCostPrice.getExecutionUiOutput();

        String expectedOutput = "1. [P][R] testItem2" + System.lineSeparator()
                + "\tdescription: Test item" + System.lineSeparator()
                + "\tquantity: 2" + System.lineSeparator()
                + "\tcost price: $2.00" + System.lineSeparator()
                + "\tsale price: $3.00" + System.lineSeparator()
                + "\tthreshold: 10" + System.lineSeparator()
                + "\texpiry date: 01-01-2024" + System.lineSeparator()
                + System.lineSeparator()
                + "2. [P][R] testItem1" + System.lineSeparator()
                + "\tdescription: Test item" + System.lineSeparator()
                + "\tquantity: 2" + System.lineSeparator()
                + "\tcost price: $2.00" + System.lineSeparator()
                + "\tsale price: $3.00" + System.lineSeparator()
                + "\tthreshold: 10" + System.lineSeparator()
                + "\texpiry date: 05-01-2024" + System.lineSeparator()
                + System.lineSeparator();

        assertEquals(expectedOutput,actualOutput);
    }

    @Test
    void execute_sortByProfit_returnsSortedList() {
        ArrayList<Item> inventory = new ArrayList<Item>();
        RetailItem testItem1 = new RetailItem("testItem1", "Test item 1", 10,
                10.00, 5.00, 10);
        RetailItem testItem2 = new RetailItem("testItem2", "Test item 2", 10,
                3.00, 2.00, 10);
        testItem1.setTotalUnitsSold(5);
        testItem1.setTotalUnitsPurchased(5);
        testItem2.setTotalUnitsSold(1);
        testItem2.setTotalUnitsPurchased(1);
        inventory.add(testItem1);
        inventory.add(testItem2);

        ItemList itemList = new ItemList(inventory);
        ListCommand listCommandCostPrice = new ListCommand(SortOptionEnum.PROFIT);

        listCommandCostPrice.execute(itemList);
        String actualOutput = listCommandCostPrice.getExecutionUiOutput();

        String expectedOutput = "1. [R] testItem2" + System.lineSeparator()
                + "\tdescription: Test item 2" + System.lineSeparator()
                + "\tquantity: 10" + System.lineSeparator()
                + "\tcost price: $2.00" + System.lineSeparator()
                + "\tsale price: $3.00" + System.lineSeparator()
                + "\tthreshold: 10" + System.lineSeparator()
                + "\tProfit: 1.00" + System.lineSeparator()
                + System.lineSeparator()
                + "2. [R] testItem1" + System.lineSeparator()
                + "\tdescription: Test item 1" + System.lineSeparator()
                + "\tquantity: 10" + System.lineSeparator()
                + "\tcost price: $5.00" + System.lineSeparator()
                + "\tsale price: $10.00" + System.lineSeparator()
                + "\tthreshold: 10" + System.lineSeparator()
                + "\tProfit: 25.00" + System.lineSeparator()
                + System.lineSeparator();

        assertEquals(expectedOutput,actualOutput);
    }

    @Test
    void execute_sortBySalePrice_returnsSortedList() {
        ArrayList<Item> inventory = new ArrayList<Item>();
        ItemList itemList = new ItemList(inventory);
        ListCommand listCommandCostPrice = new ListCommand(SortOptionEnum.SALE);

        itemList.addItem("retail", "testItem1", "Test item 1", 2,
                LocalDate.of(2024, 1, 1), 10.00, 2.00, 10);
        itemList.addItem("retail", "testItem2", "Test item 2", 2,
                LocalDate.of(2024, 1, 1), 3.00, 2.00, 10);

        listCommandCostPrice.execute(itemList);
        String actualOutput = listCommandCostPrice.getExecutionUiOutput();

        String expectedOutput = "1. [P][R] testItem2" + System.lineSeparator()
                + "\tdescription: Test item 2" + System.lineSeparator()
                + "\tquantity: 2" + System.lineSeparator()
                + "\tcost price: $2.00" + System.lineSeparator()
                + "\tsale price: $3.00" + System.lineSeparator()
                + "\tthreshold: 10" + System.lineSeparator()
                + "\texpiry date: 01-01-2024" + System.lineSeparator()
                + System.lineSeparator()
                + "2. [P][R] testItem1" + System.lineSeparator()
                + "\tdescription: Test item 1" + System.lineSeparator()
                + "\tquantity: 2" + System.lineSeparator()
                + "\tcost price: $2.00" + System.lineSeparator()
                + "\tsale price: $10.00" + System.lineSeparator()
                + "\tthreshold: 10" + System.lineSeparator()
                + "\texpiry date: 01-01-2024" + System.lineSeparator()
                + System.lineSeparator();

        assertEquals(expectedOutput,actualOutput);
    }

    @Test
    void execute_sortByExpiryDateNoPerishables_returnsEmptyList() {
        ArrayList<Item> inventory = new ArrayList<Item>();
        ItemList itemList = new ItemList(inventory);
        ListCommand listCommandExpiryDate = new ListCommand(SortOptionEnum.EXPIRY);

        itemList.addItem("retail", "testItem1", "Test item 1", 2,
                LocalDate.MIN, 10.00, 2.00, 10);
        itemList.addItem("retail", "testItem2", "Test item 2", 2,
                LocalDate.MIN, 3.00, 2.00, 10);

        listCommandExpiryDate.execute(itemList);
        String actualOutput = listCommandExpiryDate.getExecutionUiOutput();

        String expectedOutput = "";

        assertEquals(expectedOutput,actualOutput);
    }

    @Test
    void execute_sortBySalePriceNoRetail_returnsEmptyList() {
        ArrayList<Item> inventory = new ArrayList<Item>();
        ItemList itemList = new ItemList(inventory);
        ListCommand listCommandCostPrice = new ListCommand(SortOptionEnum.SALE);

        itemList.addItem("operational", "testItem1", "Test item 1", 2,
                LocalDate.MIN, 10.00, 2.00, 10);
        itemList.addItem("operational", "testItem2", "Test item 2", 2,
                LocalDate.MIN, 3.00, 2.00, 10);

        listCommandCostPrice.execute(itemList);
        String actualOutput = listCommandCostPrice.getExecutionUiOutput();

        String expectedOutput = "";

        assertEquals(expectedOutput,actualOutput);
    }
}
