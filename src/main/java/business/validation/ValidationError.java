package business.validation;

import java.util.Optional;

/**
 *
 * Validation error to be use a return value of validate method of IFieldValidator intefrace
 * Created by Alexander Pushkarev.
 *
 * 15.2.18
 */
public class ValidationError {

    private String errorMessage;

    private ValidationError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * To be used for non empty error code
     * @param errorMessage
     * @return
     */
    public static Optional<ValidationError> getOptionalValidationError(String errorMessage) {
        return Optional.of(new ValidationError(errorMessage));
    }

    /**
     * To be used if no errors were spotted
     * @return
     */
    public static Optional<ValidationError> getEmptyValidationError() {
        return Optional.empty();
    }
}
