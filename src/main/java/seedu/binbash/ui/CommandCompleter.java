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
import java.util.function.Function;

public class CommandCompleter extends AggregateCompleter {
    private static NullCompleter deleteOptionCompleter = NullCompleter.INSTANCE;
    private static NullCompleter listOptionCompleter = NullCompleter.INSTANCE;
    private static NullCompleter searchOptionCompleter = NullCompleter.INSTANCE;
    private static NullCompleter byeOptionCompleter = NullCompleter.INSTANCE;

    private static Candidate addCommandCandidate = new Candidate("add", "add", "inventory management",
            "add an item", "suffixadd", "keyadd", true);
    private static ArgumentCompleter addCompleter = new ArgumentCompleter(new StringsCompleter(addCommandCandidate));
    private static ArgumentCompleter deleteCompleter = new ArgumentCompleter(new StringsCompleter("delete"),
            deleteOptionCompleter);
    private static ArgumentCompleter listCompleter = new ArgumentCompleter(new StringsCompleter("list"),
            listOptionCompleter);
    private static ArgumentCompleter searchCompleter = new ArgumentCompleter(new StringsCompleter("search"),
            searchOptionCompleter);
    private static ArgumentCompleter byeCompleter = new ArgumentCompleter(new StringsCompleter("bye"),
            byeOptionCompleter);

    private static OptionCompleter addOptionCompleter;

    public CommandCompleter(ArrayList<OptDesc> addCommandOptionDescriptions) {
        super(addCompleter, new StringsCompleter("search"));
        addCompleter.getCompleters().add(new OptionCompleter(
                    addCommandOptionDescriptions, 1));
    }
}
