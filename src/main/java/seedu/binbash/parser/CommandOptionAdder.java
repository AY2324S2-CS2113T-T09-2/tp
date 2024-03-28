package seedu.binbash.parser;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class CommandOptionAdder {
    Options options;

    public CommandOptionAdder(Options options) {
        this.options = options;
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
}
