package seedu.binbash.parser;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;

public class CommandOptionAdder {
    Options options;

    public CommandOptionAdder(Options options) {
        this.options = options;
    }

    private Option getRetailItemOption() {
        Option reItemOption = Option.builder("re")
                .hasArg(false)
                .required(true)
                .longOpt("retail")
                .desc("Add a Retail Item.")
                .argName("retail")
                .build();

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

        return sortSaleOption;
    }

    CommandOptionAdder addListTypeOptionGroup() {
        OptionGroup listTypeOptionGroup = new OptionGroup()
                .addOption(sortByCostPriceOption())
                .addOption(sortByExpirationDateOption())
                .addOption(sortBySalePriceOption());

        listTypeOptionGroup.setRequired(false);
        options.addOptionGroup(listTypeOptionGroup);
        return this;
    }

    CommandOptionAdder addItemTypeOptionGroup() {
        OptionGroup itemTypeOptionGroup = new OptionGroup()
                .addOption(getRetailItemOption())
                .addOption(getOperationalItemOption());

        itemTypeOptionGroup.setRequired(true);
        options.addOptionGroup(itemTypeOptionGroup);
        return this;
    }

    CommandOptionAdder addNameOption(boolean isRequired, String description) {
        Option nameOption = Option.builder("n")
                .hasArgs() // potentially more than 1 input
                .required(isRequired)
                .longOpt("name")
                .desc(description)
                .build();
        options.addOption(nameOption);
        return this;
    }

    CommandOptionAdder addDescriptionOption(boolean isRequired, String description) {
        Option descOption = Option.builder("d")
                .hasArgs()
                .required(isRequired)
                .longOpt("description")
                .desc(description)
                .build();
        options.addOption(descOption);
        return this;
    }

    CommandOptionAdder addCostPriceOption(boolean isRequired, String description) {
        Option costOption = Option.builder("c")
                .hasArg(true)
                .required(isRequired)
                .numberOfArgs(1)
                .longOpt("cost-price")
                .desc(description)
                .build();
        options.addOption(costOption);
        return this;
    }

    CommandOptionAdder addQuantityOption(boolean isRequired, String description) {
        Option quantOption = Option.builder("q")
                .hasArg(true)
                .required(isRequired)
                .numberOfArgs(1)
                .longOpt("quantity")
                .desc(description)
                .build();
        options.addOption(quantOption);
        return this;
    }

    CommandOptionAdder addSalePriceOption(boolean isRequired, String description) {
        Option saleOption = Option.builder("s")
                .hasArg(true)
                .required(isRequired)
                .numberOfArgs(1)
                .longOpt("sale-price")
                .desc(description)
                .build();
        options.addOption(saleOption);
        return this;
    }

    CommandOptionAdder addExpirationDateOption(boolean isRequired, String description) {
        Option expiryOption = Option.builder("e")
                .hasArg(true)
                .required(isRequired)
                .numberOfArgs(1)
                .longOpt("expiry-date")
                .desc(description)
                .build();
        options = options.addOption(expiryOption);
        return this;
    }

    CommandOptionAdder addListOption(boolean isRequired, String description) {
        Option nameOption = Option.builder("l")
                .hasArg()
                .argName("n")
                .required(isRequired)
                .longOpt("list")
                .desc(description)
                .build();
        options.addOption(nameOption);
        return this;
    }
}
