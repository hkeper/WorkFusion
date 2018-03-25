package gmail.alexspush.ui.tests;

import gmail.alexspush.ui.driver.SelenideApplicationDriver;
import gmail.alexspush.ui.page.SelenideMainPage;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static org.junit.Assert.assertTrue;

/**
 * Created by Mikhail Makeikin on 3/24/18.
 */
public class TestUI extends SelenideApplicationDriver{

    private SelenideMainPage mainPage = SelenideMainPage.INSTANCE;
    private String userName = "User";
    private String fullName = "Name";
    private String password = "1a";

    //Positive tests
    @Test
    public void createUserWithValidValues(){
        mainPage.setUserName(userName);
        mainPage.setFullname(fullName);
        mainPage.setPassword(password);
        mainPage.clickSubmit();
        assertTrue("There wrong message after create user!",
                mainPage.getStatus().equals("Status: user " + userName + " was created"));
    }

    @Test
    public void disallowEmptyUsername(){
        mainPage.setUserName("");
        mainPage.clickSubmit();
        assertTrue("There wrong message for empty 'Username' field!",
                mainPage.getStatus().equals("Status: Login cannot be empty"));
    }

    @Test
    public void disallowEmptyFullName(){
        mainPage.setUserName(userName);
        mainPage.setFullname("");
        mainPage.clickSubmit();
        assertTrue("There wrong message for empty 'Full name' field!",
                mainPage.getStatus().equals("Status: Full name cannot be empty"));
    }

    @Test
    public void disallowEmptyPassword(){
        mainPage.setUserName(userName);
        mainPage.setFullname(fullName);
        mainPage.setPassword("");
        mainPage.clickSubmit();
        assertTrue("There wrong message for empty 'Password' field!",
                mainPage.getStatus().equals("Status: Password cannot be empty"));
    }

    //Negative tests
    @Test
    public void negativeAddedUsersSavedAfterPageRefresh(){
        mainPage.setUserName(userName);
        mainPage.setFullname(fullName);
        mainPage.setPassword(password);
        mainPage.clickSubmit();
        assertTrue("There wrong message after create user!",
                mainPage.getStatus().equals("Status: user " + userName + " was created"));
        pageRefresh();
        assertTrue("There wrong message for about added User!",
                mainPage.getLastUser().equals("Full name: " + fullName +
                        " Username: " + userName +" Password: "+ password));
    }

    @Test
    public void negativeScrollAppearsAfterUsersAdding(){
        Boolean isScrollPresent = false;

        for (int i=0; i<=30; i++) {
            String execScript = "return document.body.scrollHeight > document.body.clientHeight;";
            isScrollPresent = executeJavaScript(execScript);
            if(isScrollPresent) break;
            mainPage.setUserName(userName);
            mainPage.setFullname(fullName);
            mainPage.setPassword(password);
            mainPage.clickSubmit();
        }
        assertTrue("The scroll bar is not present!", isScrollPresent);
        assertTrue("There wrong message for about added User!",
                mainPage.getLastUser().equals("Full name: " + fullName +
                        " Username: " + userName +" Password: "+ password));
    }


}
