package business.validation;

import java.util.Optional;

/**
 * Created by Alexander Pushkarev.
 *
 * 15.2.18
 */
public interface IFieldValidator {

    default String getFieldName() {
        return "Field";
    }

    /**
     * Cool default method to be used in most places
     */
    default Optional<ValidationError> validate(String strToValidate) {
        if(strToValidate == null || strToValidate.isEmpty()) {
            return ValidationError.getOptionalValidationError(
                    getFieldName() + " cannot be empty");
        }
        return ValidationError.getEmptyValidationError();
    }


}
