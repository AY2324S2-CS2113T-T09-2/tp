package seedu.binbash.exceptions;

/**
 * Represents a command-specific error that occurred during BinBash execution.
 * Used when improper arguments are passed in by the User.
 */
public class InvalidArgumentException extends BinBashException {

    /**
     * @param errorMessage A descriptive error message detailing the cause of the exception.
     */
    public InvalidArgumentException(String errorMessage) {
        super(errorMessage);
    }
}
