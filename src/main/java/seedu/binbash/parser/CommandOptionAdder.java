package seedu.binbash.parser;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import org.jline.builtins.Completers.OptDesc;

import java.util.ArrayList;

/**
 * A utility class to add and save command options, shared between command parsers and Ui
 * command completer classes.
 */
public class CommandOptionAdder {
    private static ArrayList<ArrayList<OptDesc>> allCommandsOptionDescriptions = new ArrayList<>();
    private Options options;
    private ArrayList<OptDesc> optionDescriptions;

    public CommandOptionAdder(Options options) {
        this.options = options;
        optionDescriptions = new ArrayList<>();
    }

    public CommandOptionAdder(Options options, ArrayList<OptDesc> optionDescriptions) {
        this.options = options;
        this.optionDescriptions = optionDescriptions;
    }

    public ArrayList<ArrayList<OptDesc>> getAllCommandsOptionDescriptions() {
        return allCommandsOptionDescriptions;
    }

    /**
     * Saves option descriptions for the current command after all options are added.
     *
     * @param command The name of the command.
     */
    void saveCommandOptionDescriptions(String command) {
        allCommandsOptionDescriptions.add(optionDescriptions);
    }

    private Option getRetailItemOption() {
        Option reItemOption = Option.builder("re")
                .hasArg(false)
                .required(true)
                .longOpt("retail")
                .desc("Add a Retail Item.")
                .argName("retail")
                .build();
        optionDescriptions.add(new OptDesc("-re", "--retail", "retail item"));
        return reItemOption;
    }

    private Option getOperationalItemOption() {
        Option opItemOption = Option.builder("op")
                .hasArg(false)
                .required(true)
                .longOpt("operational")
                .desc("Add an Operational Item.")
                .argName("operational")
                .build();
        optionDescriptions.add(new OptDesc("-op", "--operational", "operational item"));
        return opItemOption;
    }

    private Option sortByCostPriceOption() {
        Option sortCostOption = Option.builder("c")
                .hasArg(false)
                .required(false)
                .longOpt("cost")
                .desc("Sort by cost price.")
                .argName("cost")
                .build();
        optionDescriptions.add(new OptDesc("-c", "--cost", "sort by cost price"));
        return sortCostOption;
    }

    private Option sortByExpirationDateOption() {
        Option sortExpiryOption = Option.builder("e")
                .hasArg(false)
                .required(false)
                .longOpt("expiry")
                .desc("Sort by expiry date.")
                .argName("expiry")
                .build();
        optionDescriptions.add(new OptDesc("-e", "--expiry", "sort by expiry date"));
        return sortExpiryOption;
    }

    private Option sortBySalePriceOption() {
        Option sortSaleOption = Option.builder("s")
                .hasArg(false)
                .required(false)
                .longOpt("sale")
                .desc("Sort by sale price.")
                .argName("sale")
                .build();
        optionDescriptions.add(new OptDesc("-s", "--sale", "sort by sale price"));
        return sortSaleOption;
    }

    private Option sortByProfitOption() {
        Option sortProfitOption = Option.builder("p")
                .hasArg(false)
                .required(false)
                .longOpt("profit")
                .desc("Sort by profits earned for each item.")
                .argName("profit")
                .build();

        return sortProfitOption;
    }

    /**
     * Adds the options for the List command as an option group to options.
     *
     * @return The current instance of CommandOptionAdder.
     */
    CommandOptionAdder addListTypeOptionGroup() {
        OptionGroup listTypeOptionGroup = new OptionGroup()
                .addOption(sortByCostPriceOption())
                .addOption(sortByExpirationDateOption())
                .addOption(sortBySalePriceOption())
                .addOption(sortByProfitOption());
        listTypeOptionGroup.setRequired(false);
        options.addOptionGroup(listTypeOptionGroup);
        return this;
    }

    private Option getItemNameOption() {
        Option opItemOption = Option.builder("n")
                //.hasArg(true)
                .hasArgs() // potentially more than 1 input
                .required(true)
                .longOpt("name")
                .desc("Identify by name")
                .argName("name")
                .build();
        optionDescriptions.add(new OptDesc("-n", "--name", "name of item"));
        return opItemOption;
    }

    private Option getItemIndexOption() {
        Option opItemOption = Option.builder("i")
                .hasArg(true)
                .required(true)
                .longOpt("index")
                .desc("Identify by index")
                .argName("index")
                .build();
        optionDescriptions.add(new OptDesc("-i", "--index", "index id of item"));
        return opItemOption;
    }

    /**
     * Adds the options for specifying an item type as either retail or operational
     * as an option group to options.
     *
     * @return The current instance of CommandOptionAdder.
     */
    CommandOptionAdder addItemTypeOptionGroup() {
        OptionGroup itemTypeOptionGroup = new OptionGroup()
                .addOption(getRetailItemOption())
                .addOption(getOperationalItemOption());
        itemTypeOptionGroup.setRequired(true);
        options.addOptionGroup(itemTypeOptionGroup);
        return this;
    }

    /**
     * Adds the options for specifying an item by either name or index id
     * as an option group to options.
     *
     * @return The current instance of CommandOptionAdder.
     */
    CommandOptionAdder addItemNameAndIndexOptionGroup() {
        OptionGroup itemNameAndInxdexOptionGroup = new OptionGroup()
                .addOption(getItemIndexOption())
                .addOption(getItemNameOption());
        itemNameAndInxdexOptionGroup.setRequired(true);
        options.addOptionGroup(itemNameAndInxdexOptionGroup);
        return this;
    }

    /**
     * Adds the name option and its description to options and optionDescriptions respectively.
     *
     * @param isRequired Whether this option needs to be specified to its parent command.
     * @param description A brief description of the option.
     * @return The current instance of CommandOptionAdder.
     */
    CommandOptionAdder addNameOption(boolean isRequired, String description) {
        Option nameOption = Option.builder("n")
                .hasArgs() // potentially more than 1 input
                .required(isRequired)
                .longOpt("name")
                .desc(description)
                .build();
        options.addOption(nameOption);
        optionDescriptions.add(new OptDesc("-n", "--name", description));
        return this;
    }

    /**
     * Adds the index option and its description to options and optionDescriptions respectively.
     *
     * @param isRequired Whether this option needs to be specified to its parent command.
     * @param description A brief description of the option.
     * @return The current instance of CommandOptionAdder.
     */
    CommandOptionAdder addIndexOption(boolean isRequired, String description) {
        Option nameOption = Option.builder("i")
                .hasArg(true)
                .required(isRequired)
                .longOpt("index")
                .desc(description)
                .argName("index")
                .build();
        options.addOption(nameOption);
        optionDescriptions.add(new OptDesc("-i", "--index", description));
        return this;
    }

    /**
     * Adds the description option and its description to options and optionDescriptions respectively.
     *
     * @param isRequired Whether this option needs to be specified to its parent command.
     * @param description A brief description of the option.
     * @return The current instance of CommandOptionAdder.
     */
    CommandOptionAdder addDescriptionOption(boolean isRequired, String description) {
        Option descOption = Option.builder("d")
                .hasArgs()
                .required(isRequired)
                .longOpt("description")
                .desc(description)
                .build();
        options.addOption(descOption);
        optionDescriptions.add(new OptDesc("-d", "--description", description));
        return this;
    }

    /**
     * Adds the cost price option and its description to options and optionDescriptions respectively.
     *
     * @param isRequired Whether this option needs to be specified to its parent command.
     * @param description A brief description of the option.
     * @return The current instance of CommandOptionAdder.
     */
    CommandOptionAdder addCostPriceOption(boolean isRequired, String description) {
        Option costOption = Option.builder("c")
                .hasArg(true)
                .required(isRequired)
                .numberOfArgs(1)
                .longOpt("cost-price")
                .desc(description)
                .build();
        options.addOption(costOption);
        optionDescriptions.add(new OptDesc("-c", "--cost-price", description));
        return this;
    }

    /**
     * Adds the quantity option and its description to options and optionDescriptions respectively.
     *
     * @param isRequired Whether this option needs to be specified to its parent command.
     * @param description A brief description of the option.
     * @return The current instance of CommandOptionAdder.
     */
    CommandOptionAdder addQuantityOption(boolean isRequired, String description) {
        Option quantOption = Option.builder("q")
                .hasArg(true)
                .required(isRequired)
                .numberOfArgs(1)
                .longOpt("quantity")
                .desc(description)
                .build();
        options.addOption(quantOption);
        optionDescriptions.add(new OptDesc("-q", "--quantity", description));
        return this;
    }

    /**
     * Adds the sale price option and its description to options and optionDescriptions respectively.
     *
     * @param isRequired Whether this option needs to be specified to its parent command.
     * @param description A brief description of the option.
     * @return The current instance of CommandOptionAdder.
     */
    CommandOptionAdder addSalePriceOption(boolean isRequired, String description) {
        Option saleOption = Option.builder("s")
                .hasArg(true)
                .required(isRequired)
                .numberOfArgs(1)
                .longOpt("sale-price")
                .desc(description)
                .build();
        options.addOption(saleOption);
        optionDescriptions.add(new OptDesc("-s", "--sale-price", description));
        return this;
    }

    /**
     * Adds the expiry date option and its description to options and optionDescriptions respectively.
     *
     * @param isRequired Whether this option needs to be specified to its parent command.
     * @param description A brief description of the option.
     * @return The current instance of CommandOptionAdder.
     */
    CommandOptionAdder addExpirationDateOption(boolean isRequired, String description) {
        Option expiryOption = Option.builder("e")
                .hasArg(true)
                .required(isRequired)
                .numberOfArgs(1)
                .longOpt("expiry-date")
                .desc(description)
                .build();
        options = options.addOption(expiryOption);
        optionDescriptions.add(new OptDesc("-e", "--expiry-date", description));
        return this;
    }

    /**
     * Adds the threshold option and its description to options and optionDescriptions respectively.
     *
     * @param isRequired Whether this option needs to be specified to its parent command.
     * @param description A brief description of the option.
     * @return The current instance of CommandOptionAdder.
     */
    CommandOptionAdder addThresholdOption(boolean isRequired, String description) {
        Option thresholdOption = Option.builder("t")
                .hasArg(true)
                .required(isRequired)
                .longOpt("threshold")
                .desc(description)
                .argName("threshold")
                .build();
        options = options.addOption(thresholdOption);
        optionDescriptions.add(new OptDesc("-t", "--threshold", description));
        return this;
    }

    /**
     * Adds the list option and its description to options and optionDescriptions respectively.
     *
     * @param isRequired Whether this option needs to be specified to its parent command.
     * @param description A brief description of the option.
     * @return The current instance of CommandOptionAdder.
     */
    CommandOptionAdder addListOption(boolean isRequired, String description) {
        Option nameOption = Option.builder("l")
                .hasArg()
                .argName("n")
                .required(isRequired)
                .longOpt("list")
                .desc(description)
                .build();
        options.addOption(nameOption);
        optionDescriptions.add(new OptDesc("-l", "--list", description));
        return this;
    }
}
