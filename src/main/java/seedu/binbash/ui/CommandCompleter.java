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

public class CommandCompleter implements Completer {
    private static NullCompleter deleteOptionCompleter = NullCompleter.INSTANCE;
    private static NullCompleter listOptionCompleter = NullCompleter.INSTANCE;
    private static NullCompleter searchOptionCompleter = NullCompleter.INSTANCE;
    private static NullCompleter byeOptionCompleter = NullCompleter.INSTANCE;
    //private static Function<String, Collection<OptDesc>> addCommandOptions = str -> {
    //    return Arrays.asList(new OptDesc("-n", "--name", "item name", NullCompleter.INSTANCE),
    //            new OptDesc("-q", "--quantity", "item quantity", new StringsCompleter()),
    //            new OptDesc("-d", "--description", "item description", new StringsCompleter()),
    //            new OptDesc("-e", "--expiry", "expiration date", new StringsCompleter()),
    //            new OptDesc("-s", "--sale", "sale price", new StringsCompleter()),
    //            new OptDesc("-c", "--cost", "cost price", new StringsCompleter()));
    //};

    private static Candidate addCommandCandidate = new Candidate("add", "<item name>", "general commands",
            "add an item", "suffixadd", "keyadd", true);
    private static ArgumentCompleter addCompleter;
    private static ArgumentCompleter deleteCompleter = new ArgumentCompleter(new StringsCompleter("delete"),
            deleteOptionCompleter);
    private static ArgumentCompleter listCompleter = new ArgumentCompleter(new StringsCompleter("list"),
            listOptionCompleter);
    private static ArgumentCompleter searchCompleter = new ArgumentCompleter(new StringsCompleter("search"),
            searchOptionCompleter);
    private static ArgumentCompleter byeCompleter = new ArgumentCompleter(new StringsCompleter("bye"),
            byeOptionCompleter);

    private static OptionCompleter addOptionCompleter;
    private final Collection<Completer> completers;

    public CommandCompleter(ArrayList<OptDesc> addCommandOptionDescriptions) {
        addOptionCompleter = new OptionCompleter(Arrays.asList(NullCompleter.INSTANCE),
                addCommandOptionDescriptions,
                1);
        addCompleter = new ArgumentCompleter(new StringsCompleter(addCommandCandidate), addOptionCompleter);
        completers = Arrays.asList(addCompleter, deleteCompleter, listCompleter, searchCompleter, byeCompleter);
    }

    @Override
    public void complete(LineReader reader, final ParsedLine line, final List<Candidate> candidates) {
        completers.forEach(c -> c.complete(reader, line, candidates));
    }
}
