package seedu.binbash.ui;

import org.jline.builtins.Completers.OptionCompleter;
import org.jline.builtins.Completers.OptDesc;
import org.jline.reader.Candidate;
import org.jline.reader.impl.completer.ArgumentCompleter;
import org.jline.reader.impl.completer.AggregateCompleter;
import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.reader.impl.completer.NullCompleter;

import java.util.ArrayList;

public class CommandCompleter extends AggregateCompleter {
    private static final String COMMAND_GROUP_INVENTORY = "Inventory Management";
    private static final String COMMAND_GROUP_IN_OUT = "Inflow / Outflow Actions";
    private static final String COMMAND_GROUP_QUERY = "Inventory Query";
    private static final String COMMAND_GROUP_OTHERS = "Others";
    private static final String SUFFIX = null;
    private static final String KEY = null;
    private static final boolean IS_COMPLETE_CANDIDATE = true;
    private static final int FIRST_SORTED_ORDER_IN_COMMAND_GROUP = 1;
    private static final int SECOND_SORTED_ORDER_IN_COMMAND_GROUP = 2;
    private static final int THIRD_SORTED_ORDER_IN_COMMAND_GROUP = 3;
    private static final int OPTION_COMPLETER_START_POS = 1;

    private static ArgumentCompleter addCompleter = new ArgumentCompleter(new StringsCompleter(
                new Candidate("add", "add", COMMAND_GROUP_INVENTORY, "add an item to the inventory",
                    SUFFIX, KEY, IS_COMPLETE_CANDIDATE, FIRST_SORTED_ORDER_IN_COMMAND_GROUP)));
    private static ArgumentCompleter updateCompleter = new ArgumentCompleter(new StringsCompleter(
                new Candidate("update", "update", COMMAND_GROUP_INVENTORY, "update an item's fields",
                    SUFFIX, KEY, IS_COMPLETE_CANDIDATE, SECOND_SORTED_ORDER_IN_COMMAND_GROUP)));
    private static ArgumentCompleter deleteCompleter = new ArgumentCompleter(new StringsCompleter(
                new Candidate("delete", "delete", COMMAND_GROUP_INVENTORY, "delete an item from the inventory",
                    SUFFIX, KEY, IS_COMPLETE_CANDIDATE, THIRD_SORTED_ORDER_IN_COMMAND_GROUP)));

    private static ArgumentCompleter restockCompleter = new ArgumentCompleter(new StringsCompleter(
                new Candidate("restock", "restock", COMMAND_GROUP_IN_OUT, "restock an item",
                    SUFFIX, KEY, IS_COMPLETE_CANDIDATE, FIRST_SORTED_ORDER_IN_COMMAND_GROUP)));
    private static ArgumentCompleter sellCompleter = new ArgumentCompleter(new StringsCompleter(
                new Candidate("sell", "sell", COMMAND_GROUP_IN_OUT, "sell an item",
                    SUFFIX, KEY, IS_COMPLETE_CANDIDATE, SECOND_SORTED_ORDER_IN_COMMAND_GROUP)));

    private static ArgumentCompleter searchCompleter = new ArgumentCompleter(new StringsCompleter(
                new Candidate("search", "search", COMMAND_GROUP_QUERY, "search for an item",
                    SUFFIX, KEY, IS_COMPLETE_CANDIDATE, FIRST_SORTED_ORDER_IN_COMMAND_GROUP)));
    private static ArgumentCompleter listCompleter = new ArgumentCompleter(new StringsCompleter(
                new Candidate("list", "list", COMMAND_GROUP_QUERY, "list inventory",
                    SUFFIX, KEY, IS_COMPLETE_CANDIDATE, SECOND_SORTED_ORDER_IN_COMMAND_GROUP)));
    private static ArgumentCompleter profitCompleter = new ArgumentCompleter(new StringsCompleter(
                new Candidate("profit", "profit", COMMAND_GROUP_QUERY, "display total profit",
                    SUFFIX, KEY, IS_COMPLETE_CANDIDATE, THIRD_SORTED_ORDER_IN_COMMAND_GROUP)));

    private static ArgumentCompleter byeCompleter = new ArgumentCompleter(new StringsCompleter(
                new Candidate("exit", "exit", COMMAND_GROUP_OTHERS, "exits application",
                    SUFFIX, KEY, IS_COMPLETE_CANDIDATE, FIRST_SORTED_ORDER_IN_COMMAND_GROUP)));

    // note the order of completers to get depends on the order in which command option descriptions
    // are added in Parser.getAllCommandsOptionDescriptions
    public CommandCompleter(ArrayList<ArrayList<OptDesc>> allCommandsOptionDescriptions) {
        super(addCompleter, searchCompleter, restockCompleter, sellCompleter, profitCompleter,
                deleteCompleter, listCompleter, updateCompleter, byeCompleter);
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
