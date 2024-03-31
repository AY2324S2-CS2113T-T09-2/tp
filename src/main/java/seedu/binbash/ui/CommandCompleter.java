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
    private static Candidate addCommandCandidate = new Candidate("add", "add", "inventory management",
            "add item", null, null, true);
    private static Candidate searchCommandCandidate = new Candidate("search", "search", "item management",
            "search an item", null, null, true);
    private static Candidate restockCommandCandidate = new Candidate("restock", "restock", "item management",
            "restock an item", null, null, true);

    private static NullCompleter deleteOptionCompleter = NullCompleter.INSTANCE;
    private static NullCompleter listOptionCompleter = NullCompleter.INSTANCE;
    private static NullCompleter searchOptionCompleter = NullCompleter.INSTANCE;
    private static NullCompleter byeOptionCompleter = NullCompleter.INSTANCE;

    private static ArgumentCompleter addCompleter = new ArgumentCompleter(new StringsCompleter(addCommandCandidate));
    private static ArgumentCompleter searchCompleter = new ArgumentCompleter(new StringsCompleter(searchCommandCandidate));
    private static ArgumentCompleter restockCompleter = new ArgumentCompleter(new StringsCompleter(restockCommandCandidate));

    private static ArgumentCompleter deleteCompleter = new ArgumentCompleter(new StringsCompleter("delete"),
            deleteOptionCompleter);
    private static ArgumentCompleter listCompleter = new ArgumentCompleter(new StringsCompleter("list"),
            listOptionCompleter);
    private static ArgumentCompleter byeCompleter = new ArgumentCompleter(new StringsCompleter("bye"),
            byeOptionCompleter);

    // note the order of completers to get depends on the order in which command parsers
    // were declared in Parser.java
    public CommandCompleter(ArrayList<ArrayList<OptDesc>> allCommandsOptionDescriptions) {
        super(addCompleter, searchCompleter, restockCompleter,
                deleteCompleter, listCompleter, byeCompleter);
        addCompleter.getCompleters().add(new OptionCompleter(
                    allCommandsOptionDescriptions.get(0), 1));
        restockCompleter.getCompleters().add(new OptionCompleter(
                    allCommandsOptionDescriptions.get(1), 1));
        searchCompleter.getCompleters().add(new OptionCompleter(
                    allCommandsOptionDescriptions.get(2), 1));
    }
}
