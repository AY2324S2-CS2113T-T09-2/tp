package seedu.binbash.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.EndOfFileException;
import org.jline.reader.UserInterruptException;
import org.jline.builtins.Completers.OptDesc;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import seedu.binbash.command.QuotesCommand;
import seedu.binbash.inventory.ItemList;
import seedu.binbash.logger.BinBashLogger;
import java.io.IOException;
import java.util.ArrayList;

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
    private static final Random RANDOM = new Random();

    private static LineReader inputReader;
    private static boolean isUserActive;

    private static final String[] CUSTOM_MESSAGES = {
        "Hope you have a good day of inventory management.",
        "Have a nice day!",
        "Welcome back to BinBash!",
        "Make today amazing!",
        "Enjoy your time with BinBash!",
        "Wishing you a productive day!",
        "Get ready for some BinBash fun!",
        "Let's make today great!",
        "Time to conquer your inventory!",
        "Sending positive vibes your way!",
        "Welcome to another BinBash adventure!",
        "Stay positive and keep BinBashing!",
        "Today's a good day to organize!",
        "You've got this!",
        "Hope your day is BinBash-tastic!",
        "Get ready to rock your inventory!",
        "BinBash is here to help!",
        "Hope you find everything you need!",
        "Have a wonderful day ahead!",
        "Start your day with a smile!",
        "Sending you BinBash blessings!",
        "Let's make magic happen!",
        "Embrace the BinBash journey!",
        "You're awesome, just like BinBash!",
        "Seize the day with BinBash!",
        "Welcome to BinBash excellence!",
        "Time to unleash your inventory power!",
        "Hope you have a blast with BinBash!",
        "Today's a good day to BinBash!",
        "Stay motivated and BinBash on!",
        "Enjoy every moment with BinBash!",
        "Make today memorable!",
        "Let's create some BinBash miracles!",
        "Believe in yourself and BinBash!",
        "Welcome to the world of BinBash!",
        "Today's forecast: BinBash brilliance!",
        "BinBash wishes you a fantastic day!",
        "May your day be filled with BinBash joy!",
        "Let's make today legendary!",
        "Stay positive and BinBash strong!"
    };

    public Ui(ArrayList<ArrayList<OptDesc>> allCommandsOptionDescriptions) {
        System.setProperty("org.jline.terminal.exec.redirectPipeCreationMode", "native");
        try {
            Terminal userTerminal = TerminalBuilder.builder()
                    .system(true)
                    .dumb(true) // TODO: omit and catch using logger
                    .build();
            inputReader = LineReaderBuilder.builder()
                    .terminal(userTerminal)
                    .completer(new CommandCompleter(allCommandsOptionDescriptions))
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
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        String startupMessage = "BinBash started on: " + formattedDateTime;
        talk(startupMessage + NEWLINE + LOGO + WELCOME_MESSAGE);
    }

    public void talk(String line) {
        System.out.println(LINE_DIVIDER + NEWLINE + line + NEWLINE + LINE_DIVIDER);
    }

    public String getRandomMessage() {
        int randomIndex = RANDOM.nextInt(CUSTOM_MESSAGES.length);
        return CUSTOM_MESSAGES[randomIndex];
    }

    public void handleUserInput(String userInput, ItemList itemList) {
        if (userInput.trim().equalsIgnoreCase("quotes")) {
            QuotesCommand quoteCommand = new QuotesCommand();
            quoteCommand.setUi(this); // Assuming 'this' refers to the current Ui object
            quoteCommand.execute(itemList);
        }
    }
}
