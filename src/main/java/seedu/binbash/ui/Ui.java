package seedu.binbash.ui;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.EndOfFileException;
import org.jline.reader.UserInterruptException;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import seedu.binbash.parser.Parser;
import seedu.binbash.logger.BinBashLogger;

import java.io.IOException;

public class Ui {
    private static final String NEWLINE = System.lineSeparator();
    private static final String LOGO = "  ____  _       ____            _" + NEWLINE +
            " | __ )(_)_ __ | __ )  __ _ ___| |__" + NEWLINE +
            " |  _ \\| | '_ \\|  _ \\ / _` / __| '_ \\" + NEWLINE +
            " | |_) | | | | | |_) | (_| \\__ \\ | | |" + NEWLINE +
            " |____/|_|_| |_|____/ \\__,_|___/_| |_|" + NEWLINE + NEWLINE;
    private static final String WELCOME_MESSAGE = "Welcome to BinBash!";
    private static final String LINE_DIVIDER = "-------------------------------------------------------------";
    private static final BinBashLogger UILOGGER = new BinBashLogger(Ui.class.getName());

    private static LineReader inputReader;
    private static boolean isUserActive;

    public Ui(Parser parser) {
        CommandCompleter uiCommandCompleter = new CommandCompleter(parser.getAllCommandsOptionDescriptions());
        System.setProperty("org.jline.terminal.exec.redirectPipeCreationMode", "native");
        try {
            Terminal userTerminal = TerminalBuilder.builder()
                .system(true)
                .dumb(true) // TODO: omit and catch using logger
                .build();
            inputReader = LineReaderBuilder.builder()
                .terminal(userTerminal)
                .completer(uiCommandCompleter)
                .build();
        } catch (IOException e) {
            UILOGGER.info("failed to get system terminal!");
            throw new RuntimeException(e);
        }
        isUserActive = true;
    }

    public boolean isUserActive() {
        return isUserActive;
    }

    public void setUserAsInactive() {
        isUserActive = false;
    }

    public String readUserCommand() {
        assert isUserActive();
        try {
            String userInput = inputReader.readLine("binbash> ");
            UILOGGER.info("received raw user input: " + userInput);
            return userInput;
        } catch (EndOfFileException | UserInterruptException e) {
            UILOGGER.info("received EOF / interrupt exception");
            return "bye";
        }
    }

    public void greet() {
        talk(LOGO + WELCOME_MESSAGE);
    }

    public void talk(String line) {
        System.out.println(LINE_DIVIDER + NEWLINE + line + NEWLINE + LINE_DIVIDER);
    }
}
