package business.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import business.User;
import business.validation.FullUserNameValidator;
import business.validation.IFieldValidator;
import business.validation.LoginValidator;
import business.validation.PasswordValidator;
import business.validation.ValidationError;
import peristance.IUserRepository;

/**
 * Created by senpay on 15.2.18.
 */
public class UserService {

    private IUserRepository userRepository;

    /**
     * return status string, which is either success or error of some kind
     *
     * @param login
     * @param fullUserName
     * @param password
     * @return
     */
    public String addUser(String login, String fullUserName, String password) {
        Optional<ValidationError> validationError =
                validateFields(login, fullUserName, password);
        if (validationError.isPresent()) {
            return validationError.get().getErrorMessage();
        }

        User user = buildUser(login, fullUserName, password);
        userRepository.saveUser(user);
        return "user " + login + " was created";
    }

    public List<String> getUserInfoList() {
        List<User> users = userRepository.getUsers();
        List<String> userInfo = new ArrayList<>();
        for(User user : users) {
            //Yes, writing code THAT bad. Brute force and no lambdas
            userInfo.add("Name: " + user.getFullUserName() +
            " login: " + user.getUserLogInName() + " password " +
            user.getPassword());
        }
        return userInfo;
    }

    public void setUserRepository(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private Optional<ValidationError> validateFields(String login, String fullUserName, String password) {
        Optional<ValidationError> loginValidationError = validateLogin(login);
        if (loginValidationError.isPresent()) {
            return loginValidationError;
        }

        Optional<ValidationError> fullNameValidationError = validateFullName(fullUserName);
        if (fullNameValidationError.isPresent()) {
            return fullNameValidationError;
        }

        Optional<ValidationError> passwordValidationError = validatePassword(password);
        if (passwordValidationError.isPresent()) {
            return passwordValidationError;
        }

        return ValidationError.getEmptyValidationError();

    }

    private Optional<ValidationError> validateLogin(String login) {
        IFieldValidator validator = new LoginValidator();
        return validator.validate(login);
    }

    private Optional<ValidationError> validateFullName(String fullName) {
        IFieldValidator validator = new FullUserNameValidator();
        return validator.validate(fullName);
    }

    private Optional<ValidationError> validatePassword(String password) {
        IFieldValidator validator = new PasswordValidator();
        return validator.validate(password);
    }

    private User buildUser(String login, String fullUserName, String password) {
        User user = new User();
        user.setUserLogInName(login);
        user.setFullUserName(fullUserName);
        user.setPassword(password);
        return user;
    }
}
