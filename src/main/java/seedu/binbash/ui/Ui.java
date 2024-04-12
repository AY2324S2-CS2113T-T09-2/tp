package seedu.binbash.ui;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.EndOfFileException;
import org.jline.reader.UserInterruptException;
import org.jline.terminal.TerminalBuilder;

import seedu.binbash.exceptions.BinBashException;
import seedu.binbash.logger.BinBashLogger;

import java.io.IOException;

/**
 * The user facing text interface of the program.
 */
public class Ui {
    private static final String NEWLINE = System.lineSeparator();
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
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

    /**
     * Note this constructor need only be called once as there should be no more than a single
     * instance of UI per program run.
     *
     * @return The current instance of Ui.
     * @throws RuntimeException If fails to get an instance of the system terminal.
     */
    public Ui() {
        System.setProperty("org.jline.terminal.exec.redirectPipeCreationMode", "native");
        try {
            inputReader = LineReaderBuilder.builder()
                .terminal(TerminalBuilder.builder().system(true).dumb(true).build())
                .completer(new CommandCompleter())
                .build();
        } catch (IOException e) {
            UILOGGER.info("failed to get system terminal!");
            throw new RuntimeException(e.getMessage());
        }
        isUserActive = true;
    }

    public boolean isUserActive() {
        return isUserActive;
    }

    public void setUserAsInactive() {
        isUserActive = false;
    }

    /**
     * Returns a non-empty string received by the user.
     *
     * @return "bye" if EOF (^D) signal detected, a non-empty string otherwise.
     * @throws BinBashException if user interrupt (^C) signal detected.
     */
    public String readUserCommand() throws BinBashException {
        try {
            String userCommand = readUserInput();
            while (userCommand.trim().equals("")) {
                userCommand = readUserInput();
            }
            return userCommand;
        } catch (EndOfFileException e) {
            UILOGGER.info("received EOF, exiting gracefully");
            return "bye";
        } catch (UserInterruptException e) {
            UILOGGER.info("received user interrupt, halting application immediately");
            setUserAsInactive();
            throw new BinBashException("");
        }
    }

    /**
     * Prints a greeting message to standard output.
     */
    public void greet() {
        talk(LOGO + WELCOME_MESSAGE);
    }

    /**
     * Prints a response to or acknowledgement of some user command to standard output.
     *
     * @param line The response to print.
     */
    public void talk(String line) {
        if (line == "Bye!") {
            assert !isUserActive;
        }
        System.out.println(LINE_DIVIDER + NEWLINE + line + NEWLINE + LINE_DIVIDER);
    }

    /**
     * Prints a warning of some unexpected application behaviour to standard output.
     *
     * @param line The text to print.
     */
    public void warn(String line) {
        if (!isUserActive) {
            assert line == "";
        }
        System.out.println(ANSI_RED + line + ANSI_RESET);
    }

    private String readUserInput() throws EndOfFileException, UserInterruptException {
        assert isUserActive;
        String userInput = inputReader.readLine("binbash> ");
        UILOGGER.info("received raw user input: " + userInput);
        return userInput;
    }
}
