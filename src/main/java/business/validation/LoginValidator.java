package business.validation;

import java.util.Optional;

/**
 * Created by Alexander Pushkarev.
 *
 * 15.2.18
 */
public class LoginValidator implements IFieldValidator {

    @Override
    public String getFieldName() {
        return "Login";
    }

}
