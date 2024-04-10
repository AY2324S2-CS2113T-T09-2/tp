package seedu.binbash.ui;

import org.jline.builtins.Completers.OptionCompleter;
import org.jline.builtins.Completers.OptDesc;
import org.jline.reader.Candidate;
import org.jline.reader.impl.completer.ArgumentCompleter;
import org.jline.reader.impl.completer.AggregateCompleter;
import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.reader.impl.completer.NullCompleter;

import seedu.binbash.parser.CommandOptionAdder;

import java.util.ArrayList;

/**
 * Suggests and provides descriptions on commands and command options.
 */
public class CommandCompleter extends AggregateCompleter {
    private static final String COMMAND_GROUP_INVENTORY = "Inventory Management";
    private static final String COMMAND_GROUP_IN_OUT = "Item Inflow-Outflow Management";
    private static final String COMMAND_GROUP_QUERY = "Inventory Query";
    private static final String COMMAND_GROUP_OTHERS = "Others";
    private static final String SUFFIX = null;
    private static final String KEY = null;
    private static final boolean IS_COMPLETE_CANDIDATE = true;
    private static final int FIRST_SORTED_ORDER_ON_DISPLAY = 1;
    private static final int SECOND_SORTED_ORDER_ON_DISPLAY = 2;
    private static final int THIRD_SORTED_ORDER_ON_DISPLAY = 3;
    private static final int FOURTH_SORTED_ORDER_ON_DISPLAY = 4;
    private static final int FIFTH_SORTED_ORDER_ON_DISPLAY = 5;
    private static final int SIXTH_SORTED_ORDER_ON_DISPLAY = 6;
    private static final int SEVENTH_SORTED_ORDER_ON_DISPLAY = 7;
    private static final int EIGHTH_SORTED_ORDER_ON_DISPLAY = 8;
    private static final int NINTH_SORTED_ORDER_ON_DISPLAY = 9;
    private static final int OPTION_COMPLETER_START_POS = 1;

    private static ArgumentCompleter addCompleter = new ArgumentCompleter(new StringsCompleter(
                new Candidate("add", "add", COMMAND_GROUP_INVENTORY, "add an item to the inventory",
                    SUFFIX, KEY, IS_COMPLETE_CANDIDATE, FIRST_SORTED_ORDER_ON_DISPLAY)));
    private static ArgumentCompleter updateCompleter = new ArgumentCompleter(new StringsCompleter(
                new Candidate("update", "update", COMMAND_GROUP_INVENTORY, "update an item's fields",
                    SUFFIX, KEY, IS_COMPLETE_CANDIDATE, SECOND_SORTED_ORDER_ON_DISPLAY)));
    private static ArgumentCompleter deleteCompleter = new ArgumentCompleter(new StringsCompleter(
                new Candidate("delete", "delete", COMMAND_GROUP_INVENTORY, "delete an item from the inventory",
                    SUFFIX, KEY, IS_COMPLETE_CANDIDATE, THIRD_SORTED_ORDER_ON_DISPLAY)));

    private static ArgumentCompleter restockCompleter = new ArgumentCompleter(new StringsCompleter(
                new Candidate("restock", "restock", COMMAND_GROUP_IN_OUT, "restock an item",
                    SUFFIX, KEY, IS_COMPLETE_CANDIDATE, FOURTH_SORTED_ORDER_ON_DISPLAY)));
    private static ArgumentCompleter sellCompleter = new ArgumentCompleter(new StringsCompleter(
                new Candidate("sell", "sell", COMMAND_GROUP_IN_OUT, "sell an item",
                    SUFFIX, KEY, IS_COMPLETE_CANDIDATE, FIFTH_SORTED_ORDER_ON_DISPLAY)));

    private static ArgumentCompleter searchCompleter = new ArgumentCompleter(new StringsCompleter(
                new Candidate("search", "search", COMMAND_GROUP_QUERY, "search for an item",
                    SUFFIX, KEY, IS_COMPLETE_CANDIDATE, SIXTH_SORTED_ORDER_ON_DISPLAY)));
    private static ArgumentCompleter listCompleter = new ArgumentCompleter(new StringsCompleter(
                new Candidate("list", "list", COMMAND_GROUP_QUERY, "list inventory",
                    SUFFIX, KEY, IS_COMPLETE_CANDIDATE, SEVENTH_SORTED_ORDER_ON_DISPLAY)));
    private static ArgumentCompleter profitCompleter = new ArgumentCompleter(new StringsCompleter(
                new Candidate("profit", "profit", COMMAND_GROUP_QUERY, "display total profit",
                    SUFFIX, KEY, IS_COMPLETE_CANDIDATE, EIGHTH_SORTED_ORDER_ON_DISPLAY)));

    private static ArgumentCompleter byeCompleter = new ArgumentCompleter(new StringsCompleter(
                new Candidate("exit", "exit", COMMAND_GROUP_OTHERS, "exits application",
                    SUFFIX, KEY, IS_COMPLETE_CANDIDATE, NINTH_SORTED_ORDER_ON_DISPLAY)));

    /**
     * Calls on parent constructor with every completer, then updates each individual completer.
     * Note the order of command options to get depends on the order in which those descriptions were added in Parser.
     *
     * @param allCommandsOptionDescriptions A list of option descriptions for all commands in this program.
     * @return The current instance of CommandCompleter.
     */
    public CommandCompleter() {
        super(addCompleter, searchCompleter, restockCompleter, sellCompleter, profitCompleter,
                deleteCompleter, listCompleter, updateCompleter, byeCompleter);
        ArrayList<ArrayList<OptDesc>> allCommandsOptionDescriptions = new CommandOptionAdder()
            .getAllCommandsOptionDescriptions();
        addCompleter.getCompleters().add(new OptionCompleter(
                    allCommandsOptionDescriptions.get(0), OPTION_COMPLETER_START_POS));
        restockCompleter.getCompleters().add(new OptionCompleter(
                    allCommandsOptionDescriptions.get(1), OPTION_COMPLETER_START_POS));
        sellCompleter.getCompleters().add(new OptionCompleter(
                    allCommandsOptionDescriptions.get(2), OPTION_COMPLETER_START_POS));
        updateCompleter.getCompleters().add(new OptionCompleter(
                    allCommandsOptionDescriptions.get(3), OPTION_COMPLETER_START_POS));
        searchCompleter.getCompleters().add(new OptionCompleter(
                    allCommandsOptionDescriptions.get(4), OPTION_COMPLETER_START_POS));
        listCompleter.getCompleters().add(new OptionCompleter(
                    allCommandsOptionDescriptions.get(5), OPTION_COMPLETER_START_POS));
        deleteCompleter.getCompleters().add(new OptionCompleter(
                    allCommandsOptionDescriptions.get(6), OPTION_COMPLETER_START_POS));
        profitCompleter.getCompleters().add(NullCompleter.INSTANCE);
        byeCompleter.getCompleters().add(NullCompleter.INSTANCE);
    }
}
