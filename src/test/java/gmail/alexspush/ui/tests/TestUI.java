package gmail.alexspush.ui.tests;

import gmail.alexspush.ui.driver.SelenideApplicationDriver;
import gmail.alexspush.ui.page.SelenideMainPage;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static org.junit.Assert.assertTrue;

/**
 * Created by Mikhail Makeikin on 3/24/18.
 *
 * Tests that implemented all test cases from document test_cases.xlsx
 *
 */
public class TestUI extends SelenideApplicationDriver{

    private SelenideMainPage mainPage = SelenideMainPage.INSTANCE;
    private String userName = "User";
    private String fullName = "Name";
    private String password = "123abc";

    //Positive tests
    @Test//test case 1
    public void createUserWithValidValues(){
        mainPage.setUserName(userName);
        mainPage.setFullname(fullName);
        mainPage.setPassword(password);
        mainPage.clickSubmit();
        assertTrue("There wrong message after create user!",
                mainPage.getStatus().equals("Status: user " + userName + " was created"));
    }

    @Test//test case 2
    public void disallowEmptyUsername(){
        mainPage.setUserName("");
        mainPage.clickSubmit();
        assertTrue("There wrong message for empty 'Username' field!",
                mainPage.getStatus().equals("Status: Login cannot be empty"));
    }

    @Test//test case 3
    public void disallowEmptyFullName(){
        mainPage.setUserName(userName);
        mainPage.setFullname("");
        mainPage.clickSubmit();
        assertTrue("There wrong message for empty 'Full name' field!",
                mainPage.getStatus().equals("Status: Full name cannot be empty"));
    }

    @Test//test case 4
    public void disallowEmptyPassword(){
        mainPage.setUserName(userName);
        mainPage.setFullname(fullName);
        mainPage.setPassword("");
        mainPage.clickSubmit();
        assertTrue("There wrong message for empty 'Password' field!",
                mainPage.getStatus().equals("Status: Password cannot be empty"));
    }

    //Negative tests
    @Test//test case 5
    public void disallowWeakPassword(){
        String passwordInvalid1 = "123ab";
        String passwordInvalid2 = "Abcdef";

        mainPage.setUserName(userName);
        mainPage.setFullname(fullName);
        mainPage.setPassword(passwordInvalid1);
        mainPage.clickSubmit();
        assertTrue("There wrong message for invalid password!",
                mainPage.getStatus().equals("Status: Password does not conform rules"));
        mainPage.setUserName(userName);
        mainPage.setFullname(fullName);
        mainPage.setPassword(passwordInvalid2);
        mainPage.clickSubmit();
        assertTrue("There wrong message for invalid password!",
                mainPage.getStatus().equals("Status: Password does not conform rules"));
    }

    @Test//test case 6
    public void negativeAddedUsersDisplayedAfterPageRefresh(){
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

    @Test//test case 7
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
