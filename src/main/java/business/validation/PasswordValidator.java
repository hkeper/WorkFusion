package business.validation;

import java.util.Optional;

/**
 * Created by Alexander Pushkarev.
 *
 * Very stupid, clumsy, brute force implementation.
 * Composite pattern would look much nicer here
 * 15.2.18
 */
public class PasswordValidator implements IFieldValidator {

    @Override
    public String getFieldName() {
        return "Password";
    }

    /**
     * Very stupid and non optimal validation, who cares
     */
    @Override
    public Optional<ValidationError> validate(String strToValidate) {
        Optional<ValidationError> error = IFieldValidator.super.validate(strToValidate);
        if (error.isPresent()) {
            return error;
        } else if (!doesConformToComplexityRules(strToValidate)) {
            return ValidationError.getOptionalValidationError(
                    getFieldName() + " does not conform rules");
        }
        return ValidationError.getEmptyValidationError();
    }

    private boolean doesConformToComplexityRules(String strToValidate) {
        return strToValidate.matches(".*\\d.*") &&
                strToValidate.matches(".*\\p{Alpha}.*") &&
                (strToValidate.length()>=6);
    }
}
