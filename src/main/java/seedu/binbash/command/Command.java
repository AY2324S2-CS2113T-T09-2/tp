package seedu.binbash.command;

import java.util.logging.Logger;
import seedu.binbash.ItemList;


/**
 * Represents the execution of a command entered by the user. Serves as an abstract to which
 * all BinBash commands will inherit.
 */
public abstract class Command {
    protected ItemList itemList;
    protected Logger commandLogger;
    protected String executionUiOutput;
    protected boolean hasToSave = false;

    protected Command() {
        commandLogger = Logger.getLogger("CommandLogger");
    }

    /**
     * Returns the outcome of the command execution that will be printed to the text UI.
     * @return the outcome message as a String.
     */
    public String getExecutionUiOutput() {
        return executionUiOutput;
    }

    /**
     * Returns whether the command executed will require BinBash to save its memory contents to the storage file.
     * @return true if BinBash needs to save to storage file, else false
     */
    public boolean hasToSave() {
        return hasToSave;
    }

    /**
     * Executes the command with parameters passed and stored prior to execution on the ItemList object
     * passed as a parameter.
     *
     * @param itemList ItemList object that the command will execute on.
     * @return True if command execution is successful, else False
     */
    public abstract boolean execute(ItemList itemList);
}
