package seedu.binbash.parser;

import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.TypeHandler;
import org.jline.builtins.Completers.OptDesc;

import seedu.binbash.command.UpdateCommand;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class UpdateCommandParser extends DefaultParser {
    private ArrayList<OptDesc> optionDescriptions;
    private boolean hasOption;

    public UpdateCommandParser() {
        options = new Options();
        optionDescriptions = new ArrayList<>();
        new CommandOptionAdder(options, optionDescriptions)
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

    public ArrayList<OptDesc> getOptionDecriptions() {
        return optionDescriptions;
    }

    public void setHasOption(boolean hasOption) {
        this.hasOption = hasOption;
    }

    public UpdateCommand parse(String[] commandArgs) throws ParseException {
        CommandLine commandLine = new DefaultParser().parse(options, commandArgs);

        boolean hasOption = false;
        UpdateCommand updateCommand;

        updateCommand = getUpdateCommand(commandLine);

        if (commandLine.hasOption("description")) {
            hasDescriptionOption(commandLine, updateCommand);
        }
        if (commandLine.hasOption("quantity")) {
            hasQuantityOption(commandLine, updateCommand);
        }
        if (commandLine.hasOption("cost-price")) {
            hasCostPriceOption(commandLine, updateCommand);
        }
        if (commandLine.hasOption("sale-price")) {
            hasSalePriceOption(commandLine, updateCommand);
        }
        if (commandLine.hasOption("expiry-date")) {
            hasExpirationDateOption(commandLine, updateCommand);
        }
        if (commandLine.hasOption("threshold")) {
            hasThresholdOption(commandLine, updateCommand);
        }
        if (!hasOption) {
            throw new ParseException("At least one of -n, -d, -c, -s, -e option required");
        }
        return updateCommand;
    }

    private void hasThresholdOption(CommandLine commandLine, UpdateCommand updateCommand)
            throws ParseException {
        boolean hasOption;
        String threshold = commandLine.getOptionValue("threshold");
        int itemThreshold = TypeHandler.createNumber(threshold).intValue();
        if (itemThreshold < 0) {
            throw new ParseException("Threshold must be must be at least 0.");
        } else if (itemThreshold == Integer.MAX_VALUE) {
            throw new ParseException("Your threshold is too large");
        }
        updateCommand.setItemThreshold(itemThreshold);
        setHasOption(true);
    }

    private void hasExpirationDateOption(CommandLine commandLine, UpdateCommand updateCommand)
            throws ParseException {
        boolean hasOption;
        try {
            LocalDate itemExpiryDate = LocalDate.parse(commandLine.getOptionValue("expiry-date"),
                    Parser.EXPECTED_INPUT_DATE_FORMAT);
            updateCommand.setItemExpirationDate(itemExpiryDate);
        } catch (DateTimeParseException e) {
            throw new ParseException(e.getMessage());
        }
        setHasOption(true);
    }

    private void hasSalePriceOption(CommandLine commandLine, UpdateCommand updateCommand)
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
        setHasOption(true);
    }

    private void hasCostPriceOption(CommandLine commandLine, UpdateCommand updateCommand)
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
        setHasOption(true);
    }

    private void hasQuantityOption(CommandLine commandLine, UpdateCommand updateCommand)
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
        setHasOption(true);
    }

    private void hasDescriptionOption(CommandLine commandLine, UpdateCommand updateCommand) {
        boolean hasOption;
        String itemDescription = String.join(" ", commandLine.getOptionValues("description"));
        updateCommand.setItemDescription(itemDescription);
        setHasOption(true);
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

