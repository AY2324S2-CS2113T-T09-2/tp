package seedu.binbash.parser;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

import seedu.binbash.command.UpdateCommand;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class UpdateCommandParser extends DefaultParser {
    protected static final DateTimeFormatter EXPECTED_INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public UpdateCommandParser() {
        options = new Options();
        new CommandOptionAdder(options)
                .addItemNameAndIndexOptionGroup()
                .addDescriptionOption(false, "Update description of item")
                .addQuantityOption(false, "Update quantity of item")
                .addCostPriceOption(false, "Update cost price of item")
                .addSalePriceOption(false, "Update sale price of item")
                .addExpirationDateOption(false,
                        "Update expiry date of item")
                .addThresholdOption(false,
                        "Update threshold of item");
    }

    public UpdateCommand parse(String[] commandArgs) throws ParseException {
        CommandLine commandLine = new DefaultParser().parse(options, commandArgs);

        boolean hasOption = false;
        UpdateCommand updateCommand;

        updateCommand = getUpdateCommand(commandLine);

        if (commandLine.hasOption("description")) {
            hasOption = hasDescriptionOption(commandLine, updateCommand);
        }
        if (commandLine.hasOption("quantity")) {
            hasOption = hasQuantityOption(commandLine, updateCommand);
        }
        if (commandLine.hasOption("cost-price")) {
            hasOption = hasCostPriceOption(commandLine, updateCommand);
        }
        if (commandLine.hasOption("sale-price")) {
            hasOption = hasSalePriceOption(commandLine, updateCommand);
        }
        if (commandLine.hasOption("expiry-date")) {
            hasOption = hasExpirationDateOption(commandLine, updateCommand);
        }
        if (commandLine.hasOption("threshold")) {
            hasOption = hasThresholdOption(commandLine, updateCommand);
        }
        if (!hasOption) {
            throw new ParseException("At least one of -n, -d, -c, -s, -e option required");
        }
        return updateCommand;
    }

    private static boolean hasThresholdOption(CommandLine commandLine, UpdateCommand updateCommand)
            throws ParseException {
        boolean hasOption;
        String threshold = commandLine.getOptionValue("threshold");
        int itemThreshold = Integer.parseInt(threshold);
        if (itemThreshold < 0) {
            throw new ParseException("Threshold must be must be at least 0.");
        } else if (itemThreshold == Integer.MAX_VALUE) {
            throw new ParseException("Your threshold is too large");
        }
        updateCommand.setItemThreshold(itemThreshold);
        hasOption = true;

        return hasOption;
    }

    private static boolean hasExpirationDateOption(CommandLine commandLine, UpdateCommand updateCommand)
            throws ParseException {
        boolean hasOption;
        try {
            LocalDate itemExpiryDate = LocalDate.parse(commandLine.getOptionValue("expiry-date"),
                    EXPECTED_INPUT_DATE_FORMAT);
            updateCommand.setItemExpirationDate(itemExpiryDate);
        } catch (DateTimeParseException e) {
            throw new ParseException(e.getMessage());
        }
        hasOption = true;
        return hasOption;
    }

    private static boolean hasSalePriceOption(CommandLine commandLine, UpdateCommand updateCommand)
            throws ParseException {
        boolean hasOption;
        String salePrice = commandLine.getOptionValue("sale-price");
        double itemSalePrice = Double.parseDouble(salePrice);
        if (itemSalePrice < 0) {
            throw new ParseException("Sale price must be at least 0.");
        } else if (itemSalePrice == Integer.MAX_VALUE) {
            throw new ParseException("Your sale price is too large");
        }
        updateCommand.setItemSalePrice(itemSalePrice);
        hasOption = true;

        return hasOption;
    }

    private static boolean hasCostPriceOption(CommandLine commandLine, UpdateCommand updateCommand)
            throws ParseException {
        boolean hasOption;
        String costPrice = commandLine.getOptionValue("cost-price");
        double itemCostPrice = Double.parseDouble(costPrice);
        if (itemCostPrice < 0) {
            throw new ParseException("Cost price must be at least 0.");
        } else if (itemCostPrice == Integer.MAX_VALUE) {
            throw new ParseException("Your cost price is too large");
        }
        updateCommand.setItemCostPrice(itemCostPrice);
        hasOption = true;

        return hasOption;
    }

    private static boolean hasQuantityOption(CommandLine commandLine, UpdateCommand updateCommand)
            throws ParseException {
        boolean hasOption;
        String quantity = commandLine.getOptionValue("quantity");
        int itemQuantity = Integer.parseInt(quantity);
        if (itemQuantity < 0) {
            throw new ParseException("Quantity must be at least 0.");
        } else if (itemQuantity == Integer.MAX_VALUE) {
            throw new ParseException("Your quantity is too large");
        }
        updateCommand.setItemQuantity(itemQuantity);
        hasOption = true;

        return hasOption;
    }


    private static boolean hasDescriptionOption(CommandLine commandLine, UpdateCommand updateCommand) {
        boolean hasOption;
        String itemDescription = String.join(" ", commandLine.getOptionValues("description"));
        updateCommand.setItemDescription(itemDescription);
        hasOption = true;
        return hasOption;
    }

    private static UpdateCommand getUpdateCommand(CommandLine commandLine) {
        UpdateCommand updateCommand;
        if (commandLine.hasOption("name")) {
            String itemName = String.join(" ", commandLine.getOptionValues("name"));
            updateCommand = new UpdateCommand(itemName);
        } else {
            int index = Integer.parseInt(commandLine.getOptionValue("index"));
            updateCommand = new UpdateCommand(index);
            updateCommand.setIsIndex();
        }
        return updateCommand;
    }
}

