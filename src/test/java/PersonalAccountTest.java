import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pageObject.HomePage;
import pageObject.PersonalAccountPage;
import pageObject.SigninPage;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class PersonalAccountTest {
    private WebDriver driver;
    private final String browser;

    public PersonalAccountTest(String browser){
        this.browser=browser;
    }

    @Parameterized.Parameters
    public static Object[][] chooseBrowser(){
        return new Object[][] {
                {"Chrome"},
                {"Yandex"},
        };
    }

    @Before
    public void init() throws Exception{
        driver = Helper.connectWebDriver(browser);
        driver.get(Helper.BASE_URI);
    }

    @DisplayName("Opening personal account with authorization should be successful")
    @Description("We are opening personal account with authorization and expect to see personal account")
    @Test
    public void openingPersonalAccountWithAuthorizationShouldBeSuccessful(){
        Helper.signInTestUser(driver);
        HomePage homePage=new HomePage(driver);
        homePage.clickPersonalAccountBlackButton();
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        personalAccountPage.waitNameFieldIsVisible();
        assertTrue(personalAccountPage.checkNameAndEmail(Helper.TEST_USER_NAME,Helper.TEST_USER_EMAIL));
    }

    @DisplayName("Opening personal account without authorization should be unsuccessful")
    @Description("We are opening personal account without authorization and expect to see signin form")
    @Test
    public void openingPersonalAccountWithoutAuthorizationShouldBeUnsuccessful(){
        HomePage homePage=new HomePage(driver);
        homePage.clickPersonalAccountBlackButton();
        SigninPage signInPage = new SigninPage(driver);
        signInPage.waitSignUpLinkIsVisible();
        assertTrue(signInPage.checkSignInText());
    }

    @DisplayName("Opening constructor button with authorization should be successful")
    @Description("We are opening constructor button from personal account with authorization and expect to see home page")
    @Test
    public void openingConstructorButtonWithAuthorizationShouldBeSuccessful(){
        Helper.signInTestUser(driver);
        HomePage homePage=new HomePage(driver);
        homePage.clickPersonalAccountBlackButton();
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        personalAccountPage.waitNameFieldIsVisible();
        personalAccountPage.clickConstructorButton();
        homePage.waitMakeOrderButtonIsVisible();
        assertTrue(homePage.makeOrderButtonIsDisplayed());
    }

    @DisplayName("Opening logo with authorization should be successful")
    @Description("We are opening logo from personal account with authorization and expect to see home page")
    @Test
    public void openingLogoWithAuthorizationShouldBeSuccessful(){
        Helper.signInTestUser(driver);
        HomePage homePage=new HomePage(driver);
        homePage.clickPersonalAccountBlackButton();
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        personalAccountPage.waitNameFieldIsVisible();
        personalAccountPage.clickLogo();
        homePage.waitMakeOrderButtonIsVisible();
        assertTrue(homePage.makeOrderButtonIsDisplayed());
    }

    @DisplayName("Signout should be successful")
    @Description("We signout of personal account and expect to see signin page")
    @Test
    public void signoutShouldBeSuccessful(){
        Helper.signInTestUser(driver);
        HomePage homePage=new HomePage(driver);
        homePage.clickPersonalAccountBlackButton();
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        personalAccountPage.waitNameFieldIsVisible();
        personalAccountPage.clickSignoutButton();
        SigninPage signInPage =new SigninPage(driver);
        signInPage.waitSignUpLinkIsVisible();
        assertTrue(signInPage.checkSignInText());
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
