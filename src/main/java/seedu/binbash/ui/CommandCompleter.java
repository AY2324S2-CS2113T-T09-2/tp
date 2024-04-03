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
    private static final boolean IS_COMPLETE_CANDIDATE = true;
    private static final int OPTION_COMPLETER_START_POS = 1;

    private static ArgumentCompleter addCompleter = new ArgumentCompleter(new StringsCompleter(
                new Candidate("add", "add", "inventory management", "add an item to be tracked",
                    null, null, IS_COMPLETE_CANDIDATE, 1)));
    private static ArgumentCompleter listCompleter = new ArgumentCompleter(new StringsCompleter(
                new Candidate("list", "list", "inventory management", "list inventory",
                    null, null, IS_COMPLETE_CANDIDATE, 6)));

    private static ArgumentCompleter restockCompleter = new ArgumentCompleter(new StringsCompleter(
                new Candidate("restock", "restock", "item management", "restock an item",
                    null, null, IS_COMPLETE_CANDIDATE, 2)));
    private static ArgumentCompleter sellCompleter = new ArgumentCompleter(new StringsCompleter(
                new Candidate("sell", "sell", "item management", "sell an item",
                    null, null, IS_COMPLETE_CANDIDATE, 3)));
    private static ArgumentCompleter updateCompleter = new ArgumentCompleter(new StringsCompleter(
                new Candidate("update", "update", "item management", "update an item's fields",
                    null, null, IS_COMPLETE_CANDIDATE, 3)));
    private static ArgumentCompleter searchCompleter = new ArgumentCompleter(new StringsCompleter(
                new Candidate("search", "search", "item management", "search for an item",
                    null, null, IS_COMPLETE_CANDIDATE, 4)));
    private static ArgumentCompleter deleteCompleter = new ArgumentCompleter(new StringsCompleter(
                new Candidate("delete", "delete", "item management", "delete item",
                    null, null, IS_COMPLETE_CANDIDATE, 5)));

    private static ArgumentCompleter profitCompleter = new ArgumentCompleter(new StringsCompleter(
                new Candidate("profit", "profit", "others", "displays total profit",
                    null, null, IS_COMPLETE_CANDIDATE, 7)));
    private static ArgumentCompleter byeCompleter = new ArgumentCompleter(new StringsCompleter(
                new Candidate("exit", "exit", "others", "exits application",
                    null, null, IS_COMPLETE_CANDIDATE, 7)));

    // note the order of completers to get depends on the order in which command option descriptions
    // are added in Parser.getAllCommandsOptionDescriptions
    public CommandCompleter(ArrayList<ArrayList<OptDesc>> allCommandsOptionDescriptions) {
        super(addCompleter, searchCompleter, restockCompleter, sellCompleter,
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
