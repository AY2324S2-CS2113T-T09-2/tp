package seedu.binbash.command;

import seedu.binbash.ItemList;
import seedu.binbash.logger.BinBashLogger;

/**
 * Represents the execution of a command entered by the user. Serves as an abstract to which
 * all BinBash commands will inherit. Execute method will be implemented by subclasses.
 */
public abstract class Command {
    protected ItemList itemList;
    protected BinBashLogger commandLogger;
    protected String executionUiOutput;
    protected boolean hasToSave = false;

    protected Command() {
    }

    /**
     * Returns the outcome of the command execution that will be printed to the text UI.
     *
     * @return the outcome message as a String.
     */
    public String getExecutionUiOutput() {
        return executionUiOutput;
    }

    /**
     * Returns whether the command executed will require BinBash to save its memory contents to the storage file.
     *
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
