package seedu.binbash.ui;

import org.jline.builtins.Completers.OptionCompleter;
import org.jline.builtins.Completers.OptDesc;
import org.jline.reader.Candidate;
import org.jline.reader.Completer;
import org.jline.reader.LineReader;
import org.jline.reader.ParsedLine;
import org.jline.reader.impl.completer.ArgumentCompleter;
import org.jline.reader.impl.completer.AggregateCompleter;
import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.reader.impl.completer.NullCompleter;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

public class CommandCompleter extends AggregateCompleter {
    private static NullCompleter deleteOptionCompleter = NullCompleter.INSTANCE;
    private static NullCompleter listOptionCompleter = NullCompleter.INSTANCE;

    // complete completers
    private static ArgumentCompleter addCompleter = new ArgumentCompleter(new StringsCompleter(
                new Candidate("add", "add", "inventory management", "add item", null, null, true, 1)));
    private static ArgumentCompleter restockCompleter = new ArgumentCompleter(new StringsCompleter(
                new Candidate("restock", "restock", "item management", "restock an item", null, null, true, 2)));
    private static ArgumentCompleter sellCompleter = new ArgumentCompleter(new StringsCompleter(
                new Candidate("sell", "sell", "item management", "sell an item", null, null, true, 3)));
    private static ArgumentCompleter searchCompleter = new ArgumentCompleter(new StringsCompleter(
                new Candidate("search", "search", "item management", "search an item", null, null, true, 4)));

    private static ArgumentCompleter byeCompleter = new ArgumentCompleter(new StringsCompleter(
                new Candidate("exit", "exit", "others", "exits application", null, null, true, 7)));

    // incomplete completers
    private static ArgumentCompleter deleteCompleter = new ArgumentCompleter(new StringsCompleter(
                new Candidate("delete", "delete", "item management", "delete item", null, null, true, 5)));
    private static ArgumentCompleter listCompleter = new ArgumentCompleter(new StringsCompleter(
                new Candidate("list", "list", "inventory management", "list inventory", null, null, true, 6)));

    // note the order of completers to get depends on the order in which command option descriptions
    // are added in Parser.getAllCommandsOptionDescriptions
    public CommandCompleter(ArrayList<ArrayList<OptDesc>> allCommandsOptionDescriptions) {
        super(addCompleter, searchCompleter, restockCompleter, sellCompleter,
                deleteCompleter, listCompleter, byeCompleter);
        addCompleter.getCompleters().add(new OptionCompleter(
                    allCommandsOptionDescriptions.get(0), 1));
        restockCompleter.getCompleters().add(new OptionCompleter(
                    allCommandsOptionDescriptions.get(1), 1));
        sellCompleter.getCompleters().add(new OptionCompleter(
                    allCommandsOptionDescriptions.get(2), 1));
        searchCompleter.getCompleters().add(new OptionCompleter(
                    allCommandsOptionDescriptions.get(3), 1));
        byeCompleter.getCompleters().add(NullCompleter.INSTANCE);
    }
}
