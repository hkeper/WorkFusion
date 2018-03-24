package business.validation;

import java.util.Optional;

/**
 * Created by Alexander Pushkarev.
 *
 * 15.2.18
 */
public class FullUserNameValidator implements IFieldValidator {

    @Override
    public String getFieldName() {
        return "Full name";
    }
}
