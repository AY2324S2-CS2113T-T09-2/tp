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
                .argName("name")
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
                .argName("description")
                .build();

        options.addOption(descOption);
        return this;
    }

    CommandOptionAdder addCostPriceOption(boolean isRequired, String description) {
        Option costOption = Option.builder("c")
                .hasArg(true)
                .required(isRequired)
                .longOpt("cost")
                .desc(description)
                .argName("cost")
                .build();

        options.addOption(costOption);
        return this;
    }

    CommandOptionAdder addQuantityOption(boolean isRequired, String description) {
        Option quantOption = Option.builder("q")
                .hasArg(true)
                .required(isRequired)
                .longOpt("quantity")
                .desc(description)
                .argName("quantity")
                .build();

        options.addOption(quantOption);
        return this;
    }

    CommandOptionAdder addSalePriceOption(boolean isRequired, String description) {
        Option saleOption = Option.builder("s")
                .hasArg(true)
                .required(isRequired)
                .longOpt("salePrice")
                .desc(description)
                .argName("salePrice")
                .build();

        options.addOption(saleOption);
        return this;
    }

    CommandOptionAdder addExpirationDateOption(boolean isRequired, String description) {
        Option expiryOption = Option.builder("e")
                .hasArg(true)
                .required(isRequired)
                .longOpt("expiration")
                .desc(description)
                .argName("expiration")
                .build();

        options = options.addOption(expiryOption);
        return this;
    }
}
