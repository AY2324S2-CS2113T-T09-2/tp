package seedu.binbash.command;

import org.junit.jupiter.api.Test;

import seedu.binbash.inventory.ItemList;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListCommandTest {
    @Test
    void execute_listCommandWithTwoItemsInItemList_correctPrintFormatForBothItems() {
        ItemList itemList = new ItemList();

        itemList.addItem("retail", "testItem1", "Test item 1", 2,
                LocalDate.of(1999, 1, 1), 4.00, 5.00);
        itemList.addItem("retail", "testItem2", "Test item 2", 6,
                LocalDate.of(1999, 1, 1), 8.00, 9.00);

        ListCommand listCommand = new ListCommand();

        listCommand.execute(itemList);
        String actualOutput = listCommand.getExecutionUiOutput();

        String expectedOutput = "1. [P][R] testItem1" + System.lineSeparator() +
                "\tdescription: Test item 1" + System.lineSeparator() +
                "\tquantity: 2" + System.lineSeparator() +
                "\tcost price: $5.00" + System.lineSeparator() +
                "\tsale price: $4.00" + System.lineSeparator() +
                "\texpiry date: 01-01-1999" + System.lineSeparator() +
                System.lineSeparator() +
                "2. [P][R] testItem2" + System.lineSeparator() +
                "\tdescription: Test item 2" + System.lineSeparator() +
                "\tquantity: 6" + System.lineSeparator() +
                "\tcost price: $9.00" + System.lineSeparator() +
                "\tsale price: $8.00" + System.lineSeparator() +
                "\texpiry date: 01-01-1999" + System.lineSeparator() +
                System.lineSeparator();

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void execute_listCommandWithEmptyItemList_returnsEmptyOutput() {
        ItemList itemList = new ItemList();
        ListCommand listCommand = new ListCommand();

        listCommand.execute(itemList);
        String actualOutput = listCommand.getExecutionUiOutput();

        String expectedOutput = "";

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void execute_sortByCostPrice_returnsSortedList() {
        ItemList itemList = new ItemList();
        ListCommand listCommandCostPrice = new ListCommand("c");

        itemList.addItem("retail", "testItem1", "Test item 1", 2,
                LocalDate.of(2024, 1, 1), 10.00, 5.00);
        itemList.addItem("retail", "testItem2", "Test item 2", 2,
                LocalDate.of(2024, 1, 1), 3.00, 2.00);

        listCommandCostPrice.execute(itemList);
        String actualOutput = listCommandCostPrice.getExecutionUiOutput();

        String expectedOutput = "1. [P][R] testItem2" + System.lineSeparator() +
                "\tdescription: Test item 2" + System.lineSeparator() +
                "\tquantity: 2" + System.lineSeparator() +
                "\tcost price: $2.00" + System.lineSeparator() +
                "\tsale price: $3.00" + System.lineSeparator() +
                "\texpiry date: 01-01-2024" + System.lineSeparator() +
                System.lineSeparator() +
                "2. [P][R] testItem1" + System.lineSeparator() +
                "\tdescription: Test item 1" + System.lineSeparator() +
                "\tquantity: 2" + System.lineSeparator() +
                "\tcost price: $5.00" + System.lineSeparator() +
                "\tsale price: $10.00" + System.lineSeparator() +
                "\texpiry date: 01-01-2024" + System.lineSeparator() +
                System.lineSeparator();

        assertEquals(expectedOutput,actualOutput);
    }

    @Test
    void execute_sortByExpiryDate_returnsSortedList() {
        ItemList itemList = new ItemList();
        ListCommand listCommandCostPrice = new ListCommand("e");

        itemList.addItem("retail", "testItem", "Test item", 2,
                LocalDate.of(2024, 1, 5), 3.00, 2.00);
        itemList.addItem("retail", "testItem", "Test item", 2,
                LocalDate.of(2024, 1, 1), 3.00, 2.00);

        listCommandCostPrice.execute(itemList);
        String actualOutput = listCommandCostPrice.getExecutionUiOutput();

        String expectedOutput = "1. [P][R] testItem" + System.lineSeparator() +
                "\tdescription: Test item" + System.lineSeparator() +
                "\tquantity: 2" + System.lineSeparator() +
                "\tcost price: $2.00" + System.lineSeparator() +
                "\tsale price: $3.00" + System.lineSeparator() +
                "\texpiry date: 01-01-2024" + System.lineSeparator() +
                System.lineSeparator() +
                "2. [P][R] testItem" + System.lineSeparator() +
                "\tdescription: Test item" + System.lineSeparator() +
                "\tquantity: 2" + System.lineSeparator() +
                "\tcost price: $2.00" + System.lineSeparator() +
                "\tsale price: $3.00" + System.lineSeparator() +
                "\texpiry date: 05-01-2024" + System.lineSeparator() +
                System.lineSeparator();

        assertEquals(expectedOutput,actualOutput);
    }

    @Test
    void execute_sortBySalePrice_returnsSortedList() {
        ItemList itemList = new ItemList();
        ListCommand listCommandCostPrice = new ListCommand("s");

        itemList.addItem("retail", "testItem1", "Test item 1", 2, LocalDate.of(2024, 1, 1), 10.00, 2.00);
        itemList.addItem("retail", "testItem2", "Test item 2", 2, LocalDate.of(2024, 1, 1), 3.00, 2.00);

        listCommandCostPrice.execute(itemList);
        String actualOutput = listCommandCostPrice.getExecutionUiOutput();

        String expectedOutput = "1. [P][R] testItem2" + System.lineSeparator() +
                "\tdescription: Test item 2" + System.lineSeparator() +
                "\tquantity: 2" + System.lineSeparator() +
                "\tcost price: $2.00" + System.lineSeparator() +
                "\tsale price: $3.00" + System.lineSeparator() +
                "\texpiry date: 01-01-2024" + System.lineSeparator() +
                System.lineSeparator() +
                "2. [P][R] testItem1" + System.lineSeparator() +
                "\tdescription: Test item 1" + System.lineSeparator() +
                "\tquantity: 2" + System.lineSeparator() +
                "\tcost price: $2.00" + System.lineSeparator() +
                "\tsale price: $10.00" + System.lineSeparator() +
                "\texpiry date: 01-01-2024" + System.lineSeparator() +
                System.lineSeparator();

        assertEquals(expectedOutput,actualOutput);
    }
}
