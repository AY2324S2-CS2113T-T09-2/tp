package seedu.binbash.parser;

import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.CommandLine;
import org.jline.builtins.Completers.OptDesc;

import seedu.binbash.command.UpdateCommand;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Parses command line arguments for creating an UpdateCommand.
 */
public class UpdateCommandParser extends DefaultParser {
    private ArrayList<OptDesc> optionDescriptions;
    private boolean hasOption;

    /**
     * Creates a new UpdateCommandParser with the necessary options and option descriptions.
     */
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

    /**
     * Gets the option descriptions for the UpdateCommandParser.
     *
     * @return The list of option descriptions.
     */
    public ArrayList<OptDesc> getOptionDecriptions() {
        return optionDescriptions;
    }

    public void setHasOption(boolean hasOption) {
        this.hasOption = hasOption;
    }

    /**
     * Parses the command line arguments to create an UpdateCommand.
     *
     * @param commandArgs The command line arguments.
     * @return The parsed UpdateCommand.
     * @throws ParseException If an error occurs during parsing.
     */
    public UpdateCommand parse(String[] commandArgs) throws ParseException {
        CommandLine commandLine = new DefaultParser().parse(options, commandArgs);
        UpdateCommand updateCommand = getUpdateCommand(commandLine);

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

    private void hasThresholdOption(CommandLine commandLine, UpdateCommand updateCommand) throws ParseException {
        String threshold = commandLine.getOptionValue("threshold");
        int itemThreshold = Parser.parseIntOptionValue(threshold, "threshold");
        if (itemThreshold < 0) {
            throw new ParseException("Threshold must be must be at least 0.");
        }
        updateCommand.setItemThreshold(itemThreshold);
        setHasOption(true);
    }

    private void hasExpirationDateOption(CommandLine commandLine, UpdateCommand updateCommand) throws ParseException {
        LocalDate itemExpiryDate = Parser.parseDateOptionValue(commandLine.getOptionValue("expiry-date"),
                "expiry date");
        updateCommand.setItemExpirationDate(itemExpiryDate);
        setHasOption(true);
    }

    private void hasSalePriceOption(CommandLine commandLine, UpdateCommand updateCommand) throws ParseException {
        String salePrice = commandLine.getOptionValue("sale-price");
        double itemSalePrice = Parser.parseDoubleOptionValue(salePrice, "sale price");
        if (itemSalePrice < 0) {
            throw new ParseException("Sale price must be at least 0.");
        }
        updateCommand.setItemSalePrice(itemSalePrice);
        setHasOption(true);
    }

    private void hasCostPriceOption(CommandLine commandLine, UpdateCommand updateCommand) throws ParseException {
        String costPrice = commandLine.getOptionValue("cost-price");
        double itemCostPrice = Parser.parseDoubleOptionValue(costPrice, "cost price");
        if (itemCostPrice < 0) {
            throw new ParseException("Cost price must be at least 0.");
        }
        updateCommand.setItemCostPrice(itemCostPrice);
        setHasOption(true);
    }

    private void hasQuantityOption(CommandLine commandLine, UpdateCommand updateCommand) throws ParseException {
        String quantity = commandLine.getOptionValue("quantity");
        int itemQuantity = Parser.parseIntOptionValue(quantity, "quantity");
        if (itemQuantity < 0) {
            throw new ParseException("Quantity must be at least 0.");
        }
        updateCommand.setItemQuantity(itemQuantity);
        setHasOption(true);
    }

    private void hasDescriptionOption(CommandLine commandLine, UpdateCommand updateCommand) {
        String itemDescription = String.join(" ", commandLine.getOptionValues("description"));
        updateCommand.setItemDescription(itemDescription);
        setHasOption(true);
    }

    private UpdateCommand getUpdateCommand(CommandLine commandLine) {
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

