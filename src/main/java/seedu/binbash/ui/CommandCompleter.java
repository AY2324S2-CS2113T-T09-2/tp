package seedu.binbash.ui;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;

import org.jline.builtins.Completers.OptionCompleter;
import org.jline.builtins.Completers.OptDesc;
import org.jline.reader.impl.completer.ArgumentCompleter;
import org.jline.reader.impl.completer.AggregateCompleter;
import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.reader.impl.completer.NullCompleter;

public class CommandCompleter extends AggregateCompleter {
    private static NullCompleter deleteOptionCompleter = NullCompleter.INSTANCE;
    private static NullCompleter listOptionCompleter = NullCompleter.INSTANCE;
    private static NullCompleter searchOptionCompleter = NullCompleter.INSTANCE;
    private static NullCompleter byeOptionCompleter = NullCompleter.INSTANCE;

    private static Function<String, Collection<OptDesc>> addCommandOptions = str -> {
        return Arrays.asList(new OptDesc("-n", "--name", "item name", NullCompleter.INSTANCE),
                new OptDesc("-q", "--quantity", "item quantity", new StringsCompleter()),
                new OptDesc("-d", "--description", "item description", new StringsCompleter()),
                new OptDesc("-e", "--expiry", "expiration date", new StringsCompleter()),
                new OptDesc("-s", "--sale", "sale price", new StringsCompleter()),
                new OptDesc("-c", "--cost", "cost price", new StringsCompleter()));
    };
    private static OptionCompleter addOptionCompleter = new OptionCompleter(Arrays.asList(
                NullCompleter.INSTANCE),
            addCommandOptions,
            1);

    private static ArgumentCompleter addCompleter = new ArgumentCompleter(new StringsCompleter("add"),
            addOptionCompleter);
    private static ArgumentCompleter deleteCompleter = new ArgumentCompleter(new StringsCompleter("delete"),
            deleteOptionCompleter);
    private static ArgumentCompleter listCompleter = new ArgumentCompleter(new StringsCompleter("list"),
            listOptionCompleter);
    private static ArgumentCompleter searchCompleter = new ArgumentCompleter(new StringsCompleter("search"),
            searchOptionCompleter);
    private static ArgumentCompleter byeCompleter = new ArgumentCompleter(new StringsCompleter("bye"),
            byeOptionCompleter);

    public CommandCompleter() {
        super(addCompleter, deleteCompleter, listCompleter, searchCompleter, byeCompleter);
    }
}
