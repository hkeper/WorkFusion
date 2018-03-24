package business;

/**
 * Created by Alexander Pushkarev.
 *
 * 15.2.18
 */
public class User {

    private String userLogInName;
    private String fullUserName;
    private String password;

    public String getUserLogInName() {
        return userLogInName;
    }

    public void setUserLogInName(String userLogInName) {
        this.userLogInName = userLogInName;
    }

    public String getFullUserName() {
        return fullUserName;
    }

    public void setFullUserName(String fullUserName) {
        this.fullUserName = fullUserName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
