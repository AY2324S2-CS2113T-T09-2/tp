package seedu.binbash.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.binbash.item.Item;
import seedu.binbash.item.OperationalItem;
import seedu.binbash.item.PerishableOperationalItem;
import seedu.binbash.item.PerishableRetailItem;
import seedu.binbash.item.RetailItem;

import java.time.LocalDate;
import java.util.ArrayList;

class StorageTest {

    private Storage storage;

    @BeforeEach
    public void setUp() {
        storage = new Storage();
    }

    @Test
    public void generateStorageRepresentationOfSingleItem_operationalItem_correctStringRepresentation() {
        OperationalItem operationalItem = new OperationalItem(
                "item1",
                "description1",
                10,
                5.0,
                2);
        operationalItem.setTotalUnitsPurchased(15);
        String expected = "OperationalItem|item1|description1|10|5.0|15|2| | | |";
        assertEquals(expected, storage.generateStorageRepresentationOfSingleItem(operationalItem));
    }

    @Test
    public void generateStorageRepresentationOfSingleItem_perishableOperationalItem_correctStringRepresentation() {
        PerishableOperationalItem perishableOperationalItem = new PerishableOperationalItem(
                "item2",
                "description2",
                20,
                LocalDate.of(2024, 12, 31),
                10.0,
                5);
        perishableOperationalItem.setTotalUnitsPurchased(25);
        String expected = "PerishableOperationalItem|item2|description2|20|10.0|25|5|31-12-2024| | |";
        assertEquals(expected, storage.generateStorageRepresentationOfSingleItem(perishableOperationalItem));
    }

    @Test
    public void generateStorageRepresentationOfSingleItem_perishableRetailItem_correctStringRepresentation() {
        PerishableRetailItem perishableRetailItem = new PerishableRetailItem(
                "item3",
                "description3",
                30,
                LocalDate.of(2024, 11, 30),
                15.0,
                7.5,
                10);
        perishableRetailItem.setTotalUnitsPurchased(35);
        perishableRetailItem.setTotalUnitsSold(40);
        String expected = "PerishableRetailItem|item3|description3|30|7.5|35|10|30-11-2024|15.0|40|";
        assertEquals(expected, storage.generateStorageRepresentationOfSingleItem(perishableRetailItem));
    }

    @Test
    public void generateStorageRepresentationOfSingleItem_retailItem_correctStringRepresentation() {
        RetailItem retailItem = new RetailItem(
                "item4",
                "description4",
                40,
                20.0,
                10.0,
                15);
        retailItem.setTotalUnitsPurchased(45);
        retailItem.setTotalUnitsSold(50);
        String expected = "RetailItem|item4|description4|40|10.0|45|15| |20.0|50|";
        assertEquals(expected, storage.generateStorageRepresentationOfSingleItem(retailItem));
    }

    @Test
    void convertLineToItem_operationalItemWithValidData_expectOperationalItem() {
        String validOperationalItem =
                "OperationalItem|Hammer|Heavy Duty Hammer|50|19.99|200|20|||";
        Item result = storage.convertLineToItem(validOperationalItem);

        assertInstanceOf(OperationalItem.class, result);
        OperationalItem operationalItem = (OperationalItem) result;
        assertEquals("Hammer", operationalItem.getItemName());
        assertEquals("Heavy Duty Hammer", operationalItem.getItemDescription());
        assertEquals(50, operationalItem.getItemQuantity());
        assertEquals(19.99, operationalItem.getItemCostPrice());
        assertEquals(200, operationalItem.getTotalUnitsPurchased());
        assertEquals(20, operationalItem.getItemThreshold());
    }

    @Test
    void convertLineToItem_perishableOperationalItemWithValidData_expectPerishableOperationalItem() {
        String validPerishableOperationalItem =
                "PerishableOperationalItem|Paint|Blue Paint|100|35.50|500|10|31-12-2024|||";
        Item result = storage.convertLineToItem(validPerishableOperationalItem);

        assertInstanceOf(PerishableOperationalItem.class, result);
        PerishableOperationalItem perishableOperationalItem = (PerishableOperationalItem) result;
        assertEquals("Paint", perishableOperationalItem.getItemName());
        assertEquals("Blue Paint", perishableOperationalItem.getItemDescription());
        assertEquals(100, perishableOperationalItem.getItemQuantity());
        assertEquals(35.50, perishableOperationalItem.getItemCostPrice());
        assertEquals(500, perishableOperationalItem.getTotalUnitsPurchased());
        assertEquals(10, perishableOperationalItem.getItemThreshold());
        assertEquals("31-12-2024", perishableOperationalItem.getItemExpirationDate());
    }

    @Test
    void convertLineToItem_perishableRetailItemWithValidData_expectPerishableRetailItem() {
        String validPerishableRetailItem =
                "PerishableRetailItem|Milk|2L Whole Milk|200|1.99|200|50|31-12-2024|2.99|150|";
        Item result = storage.convertLineToItem(validPerishableRetailItem);

        assertInstanceOf(PerishableRetailItem.class, result);
        PerishableRetailItem perishableRetailItem = (PerishableRetailItem) result;
        assertEquals("Milk", perishableRetailItem.getItemName());
        assertEquals("2L Whole Milk", perishableRetailItem.getItemDescription());
        assertEquals(200, perishableRetailItem.getItemQuantity());
        assertEquals(1.99, perishableRetailItem.getItemCostPrice());
        assertEquals(200, perishableRetailItem.getTotalUnitsPurchased());
        assertEquals(50, perishableRetailItem.getItemThreshold());
        assertEquals("31-12-2024", perishableRetailItem.getItemExpirationDate());
        assertEquals(2.99, perishableRetailItem.getItemSalePrice());
        assertEquals(150, perishableRetailItem.getTotalUnitsSold());
    }

    @Test
    void convertLineToItem_retailItemWithValidData_expectRetailItem() {
        String validRetailItem =
                "RetailItem|Book|Science Fiction Novel|10|5.99|100|5||6.99|80|";
        Item result = storage.convertLineToItem(validRetailItem);

        assertInstanceOf(RetailItem.class, result);
        RetailItem retailItem = (RetailItem) result;
        assertEquals("Book", retailItem.getItemName());
        assertEquals("Science Fiction Novel", retailItem.getItemDescription());
        assertEquals(10, retailItem.getItemQuantity());
        assertEquals(5.99, retailItem.getItemCostPrice());
        assertEquals(100, retailItem.getTotalUnitsPurchased());
        assertEquals(5, retailItem.getItemThreshold());
        assertEquals(6.99, retailItem.getItemSalePrice());
        assertEquals(80, retailItem.getTotalUnitsSold());
    }

    @Test
    void convertLineToItem_invalidItemType_expectNull() {
        String invalidType = "UnknownItem|Item|Description|10|1.99|100|5|||";
        Item result = storage.convertLineToItem(invalidType);

        assertNull(result);
    }

    @Test
    void parseLinesToItemList_invalidNumberFormat_expectCorruptionFlag() {
        ArrayList<String> linesWithInvalidNumber = new ArrayList<>();
        linesWithInvalidNumber.add("OperationalItem|Hammer|Heavy Duty Hammer|notANumber|19.99|200|20|||");

        ArrayList<Item> parsedItems = storage.parseLinesToItemList(linesWithInvalidNumber);

        assertTrue(storage.isCorrupted);
        assertTrue(parsedItems.isEmpty());
    }

    @Test
    void parseLinesToItemList_invalidDateFormat_expectCorruptionFlag() {
        ArrayList<String> linesWithInvalidDate = new ArrayList<>();
        linesWithInvalidDate.add("PerishableOperationalItem|Paint|Blue Paint|100|35.50|500|10|31/12/2024|||");

        ArrayList<Item> parsedItems = storage.parseLinesToItemList(linesWithInvalidDate);

        assertTrue(storage.isCorrupted);
        assertTrue(parsedItems.isEmpty());
    }

    @Test
    void parseLinesToItemList_validInputData_expectItemsAdded() {
        ArrayList<String> linesWithValidData = new ArrayList<>();
        linesWithValidData.add("OperationalItem|Hammer|Heavy Duty Hammer|50|19.99|200|20|||");
        linesWithValidData.add("PerishableOperationalItem|Paint|Blue Paint|100|35.50|500|10|31-12-2024|||");
        linesWithValidData.add("PerishableRetailItem|Milk|2L Whole Milk|200|1.99|200|50|31-12-2024|2.99|150|");
        linesWithValidData.add("RetailItem|Book|Science Fiction Novel|10|5.99|100|5||6.99|80|");

        ArrayList<Item> parsedItems = storage.parseLinesToItemList(linesWithValidData);

        assertFalse(storage.isCorrupted);
        assertEquals(4, parsedItems.size());

        // Further checks could be added here to verify that each item
        // is of the correct type and has the expected properties, but this feels sufficient.
        assertInstanceOf(OperationalItem.class, parsedItems.get(0));
        assertInstanceOf(PerishableOperationalItem.class, parsedItems.get(1));
        assertInstanceOf(PerishableRetailItem.class, parsedItems.get(2));
        assertInstanceOf(RetailItem.class, parsedItems.get(3));
    }
}
