package gmail.alexspush.ui.tests;

import gmail.alexspush.ui.driver.SelenideApplicationDriver;
import gmail.alexspush.ui.page.SelenideMainPage;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Mikhail Makeikin on 3/24/18.
 */
public class TestUI extends SelenideApplicationDriver{

    private SelenideMainPage mainPage = SelenideMainPage.INSTANCE;

    @Test
    public void createUserWithValidValues(){
        mainPage.setUserName("User");
        mainPage.setFullname("Name");
        mainPage.setPassword("1a");
        mainPage.clickSubmit();
        assertTrue("There wrong message after create user!",
                mainPage.getStatus().equals("Status: user ss was created"));
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
        mainPage.setUserName("User");
        mainPage.setFullname("");
        mainPage.clickSubmit();
        assertTrue("There wrong message for empty 'Full name' field!",
                mainPage.getStatus().equals("Status: Full name cannot be empty"));
    }

    @Test
    public void disallowEmptyPassword(){
        mainPage.setUserName("User");
        mainPage.setFullname("Name");
        mainPage.setPassword("");
        mainPage.clickSubmit();
        assertTrue("There wrong message for empty 'Password' field!",
                mainPage.getStatus().equals("Status: Password cannot be empty"));
    }

}
