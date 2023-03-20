import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pageObject.HomePage;
import pageObject.RecoverPasswordPage;
import pageObject.SigninPage;
import pageObject.SignupPage;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class SigninPositiveTest {
    private WebDriver driver;
    private final String browser;
    private final String buttonType;

    public SigninPositiveTest(String browser, String buttonType){
        this.browser=browser;
        this.buttonType=buttonType;
    }

    @Parameterized.Parameters
    public static Object[][] getData(){
        return new Object[][] {
                {"Chrome","blue"},
                {"Chrome","black"},
                {"Yandex","black"},
                {"Yandex","blue"},
        };
    }

    @Before
    public void init() throws Exception{
        driver = Helper.connectWebDriver(browser);
        driver.get(Helper.BASE_URI);
    }

    @DisplayName("Signin by personal account button on home page with valid data should pass")
    @Description("We authorize the user using the personal account button on the main page with valid data and expect that authorization should pass")
    @Test
    public void signinByPersonalAccountButtonOnHomePageWithValidDataShouldPass() throws Exception{
        HomePage homePage=new HomePage(driver);
        homePage.chooseAndClickPersonalAccountButton(buttonType);
        SigninPage signInPage =new SigninPage(driver);
        signInPage.fillSigninForm(Helper.TEST_USER_EMAIL,Helper.TEST_USER_PASSWORD);
        homePage.waitMakeOrderButtonIsVisible();
        assertTrue(homePage.makeOrderButtonIsDisplayed());
    }

    @DisplayName("Signin by personal account button in signin form with valid data should pass")
    @Description("We authorize the user using the personal account button in signin form with valid data and expect that authorization should pass")
    @Test
    public void signinByPersonalAccountButtonInSigninFormWithValidDataShouldPass() throws Exception{
        HomePage homePage=new HomePage(driver);
        homePage.chooseAndClickPersonalAccountButton(buttonType);
        SigninPage signInPage =new SigninPage(driver);
        signInPage.clickSignUpLink();
        SignupPage signupPage=new SignupPage(driver);
        signupPage.clickSigninLink();
        signInPage.fillSigninForm(Helper.TEST_USER_EMAIL,Helper.TEST_USER_PASSWORD);
        homePage.waitMakeOrderButtonIsVisible();
        assertTrue(homePage.makeOrderButtonIsDisplayed());
    }

    @DisplayName("Signin by personal account button in recover password form with valid data should pass")
    @Description("We authorize the user using the personal account button in recover password form with valid data and expect that authorization should pass")
    @Test
    public void signinByPersonalAccountButtonInRecoverPasswordFormWithValidDataShouldPass() throws Exception{
        HomePage homePage=new HomePage(driver);
        homePage.chooseAndClickPersonalAccountButton(buttonType);
        SigninPage signInPage =new SigninPage(driver);
        signInPage.clickRecoverPasswordLink();
        RecoverPasswordPage recoverPasswordPage =new RecoverPasswordPage(driver);
        recoverPasswordPage.clickSigninLink();
        signInPage.fillSigninForm(Helper.TEST_USER_EMAIL,Helper.TEST_USER_PASSWORD);
        homePage.waitMakeOrderButtonIsVisible();
        assertTrue(homePage.makeOrderButtonIsDisplayed());
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
