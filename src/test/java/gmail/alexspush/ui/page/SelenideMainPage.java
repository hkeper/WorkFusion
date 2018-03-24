package gmail.alexspush.ui.page;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by Alexander Pushkarev on 9.2.18.
 */
public class SelenideMainPage {

    public static final SelenideMainPage INSTANCE = new SelenideMainPage();

    private static final By USER_USERNAME_FIELD_ID = By.id("username");
    private static final By USER_FULLNAME_FIELD_ID = By.id("name");
    private static final By USER_PASSWORD_FIELD_ID = By.id("password");
    private static final By SUBMIT_BUTTON_ID = By.id("submit");
    private static final By STATUS_FIELD_ID = By.id("status");

     //Brute force and not Thread-Local implementation of Singletone
    //Lazy initialization not needed, will just use static field
    private SelenideMainPage() {
    }

    public void setUserName(String userName) {
        $(USER_USERNAME_FIELD_ID).setValue(userName);
    }
    public void setFullname(String name) {
        $(USER_FULLNAME_FIELD_ID).setValue(name);
    }
    public void setPassword(String pswd) {
        $(USER_PASSWORD_FIELD_ID).setValue(pswd);
    }

    public void clickSubmit() {
        $(SUBMIT_BUTTON_ID).click();
    }

    public String getStatus() {
        return $(STATUS_FIELD_ID).getText();
    }
}
