package seedu.binbash;

import seedu.binbash.storage.Storage;
import seedu.binbash.ui.Ui;
import seedu.binbash.command.Command;
import seedu.binbash.command.ByeCommand;
import seedu.binbash.exceptions.BinBashException;

public class BinBash {
    private Ui userInterface;
    private ItemList itemList;
    private Parser inputParser;
    private Storage storage;

    public BinBash() {
        userInterface = new Ui();
        storage = new Storage();
        itemList = new ItemList(storage.loadData());
        inputParser = new Parser(itemList);
    }

    private void run() {
        userInterface.greet();
        userInterface.talk(itemList.getProfitMargin());

        while (userInterface.isUserActive()) {
            String userInput = userInterface.readUserCommand();
            try {
                Command userCommand = inputParser.parseCommand(userInput);

                if (userCommand instanceof ByeCommand) {
                    userInterface.setUserAsInactive();
                }

                userCommand.execute();
                userInterface.talk(userCommand.getExecutionUiOutput());
                storage.saveToStorage(itemList.getItemList());

                //optional code for me to test, may remove if u wish
                //userInterface.talk(itemList.getProfitMargin());

            } catch (BinBashException e) {
                userInterface.talk(e.getMessage());
            }
        }
    }
    
    /**
     * Main entry-point for the BinBash application.
     */
    public static void main(String[] args) {
        new BinBash().run();
    }
}
