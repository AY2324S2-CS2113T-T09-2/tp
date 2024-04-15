package seedu.binbash.quotes;

import java.util.Random;

public class Quotes {
    public static final String[] CUSTOM_MESSAGES = {
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

    private static final Random RANDOM = new Random();

    // Private constructor to prevent instantiation
    private Quotes() {}

    public static String getRandomQuote() {
        int randomIndex = RANDOM.nextInt(CUSTOM_MESSAGES.length);
        return CUSTOM_MESSAGES[randomIndex];
    }
}
