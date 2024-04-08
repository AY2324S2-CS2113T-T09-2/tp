package seedu.binbash.parser;

import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.TypeHandler;
import org.jline.builtins.Completers.OptDesc;

import seedu.binbash.command.UpdateCommand;
import seedu.binbash.exceptions.InvalidArgumentException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
     * @throws InvalidArgumentException If an invalid argument is encountered during parsing.
     * @throws ParseException If an error occurs during parsing.
     */
    public UpdateCommand parse(String[] commandArgs) throws InvalidArgumentException, ParseException {
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

    private void hasThresholdOption(CommandLine commandLine, UpdateCommand updateCommand)
            throws InvalidArgumentException {
        String threshold = commandLine.getOptionValue("threshold");
        long itemThreshold;

        try {
            itemThreshold = TypeHandler.createNumber(threshold).longValue();
        } catch (ParseException e) {
            // Thrown when provided value > Long.MAX_VALUE or < Long.MIN_VALUE
            throw new InvalidArgumentException("Threshold value is invalid!");
        }

        // Valid Long is provided, now ensure value is within Integer bounds.
        if (itemThreshold < 0) {
            throw new InvalidArgumentException("Threshold must be must be at least 0.");
        } else if (itemThreshold > Integer.MAX_VALUE) {
            throw new InvalidArgumentException("Threshold value is too large!");
        }
        updateCommand.setItemThreshold((int) itemThreshold);
        setHasOption(true);
    }

    private void hasExpirationDateOption(CommandLine commandLine, UpdateCommand updateCommand)
            throws InvalidArgumentException {
        try {
            LocalDate itemExpiryDate = LocalDate.parse(commandLine.getOptionValue("expiry-date"),
                    Parser.EXPECTED_INPUT_DATE_FORMAT);
            updateCommand.setItemExpirationDate(itemExpiryDate);
        } catch (DateTimeParseException e) {
            throw new InvalidArgumentException("Please enter a valid date with the format as such: dd-mm-yyyy");
        }
        setHasOption(true);
    }

    private void hasSalePriceOption(CommandLine commandLine, UpdateCommand updateCommand)
            throws InvalidArgumentException, ParseException {
        String salePrice = commandLine.getOptionValue("sale-price");
        double itemSalePrice = TypeHandler.createNumber(salePrice).doubleValue();
        if (itemSalePrice < 0) {
            throw new InvalidArgumentException("Sale price must be at least 0.");
        } else if (itemSalePrice == Double.MAX_VALUE) {
            throw new InvalidArgumentException("Sale price is too large!");
        }
        updateCommand.setItemSalePrice(itemSalePrice);
        setHasOption(true);
    }

    private void hasCostPriceOption(CommandLine commandLine, UpdateCommand updateCommand)
            throws InvalidArgumentException, ParseException {
        String costPrice = commandLine.getOptionValue("cost-price");
        double itemCostPrice = TypeHandler.createNumber(costPrice).doubleValue();
        if (itemCostPrice < 0) {
            throw new InvalidArgumentException("Cost price must be at least 0.");
        } else if (itemCostPrice == Double.MAX_VALUE) {
            throw new InvalidArgumentException("Cost price is too large!");
        }
        updateCommand.setItemCostPrice(itemCostPrice);
        setHasOption(true);
    }

    private void hasQuantityOption(CommandLine commandLine, UpdateCommand updateCommand)
            throws InvalidArgumentException {
        String quantity = commandLine.getOptionValue("quantity");
        long itemQuantity;

        try {
            itemQuantity = TypeHandler.createNumber(quantity).longValue();
        } catch (ParseException e) {
            // Thrown when provided value > Long.MAX_VALUE or < Long.MIN_VALUE
            throw new InvalidArgumentException("Quantity value is invalid!");
        }

        if (itemQuantity < 0) {
            throw new InvalidArgumentException("Quantity must be at least 0.");
        } else if (itemQuantity > Integer.MAX_VALUE) {
            throw new InvalidArgumentException("Quantity value is too large!");
        }
        updateCommand.setItemQuantity((int) itemQuantity);
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

