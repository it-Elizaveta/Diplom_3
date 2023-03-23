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
public class SigninNegativeTest {
    private WebDriver driver;
    private final String browser;
    private final String buttonType;
    private final String email;
    private final String password;

    public SigninNegativeTest(String browser, String buttonType,String email,String password){
        this.browser=browser;
        this.buttonType=buttonType;
        this.email=email;
        this.password=password;
    }

    @Parameterized.Parameters
    public static Object[][] getData(){
        return new Object[][] {
                {"Chrome","blue","",Helper.TEST_USER_PASSWORD},
                {"Chrome","blue",Helper.TEST_USER_EMAIL,""},
                {"Chrome","blue",Helper.TEST_USER_EMAIL,Helper.getPassword()},
                {"Chrome","blue",Helper.getEmail(),Helper.TEST_USER_PASSWORD},

                {"Chrome","black","",Helper.TEST_USER_PASSWORD},
                {"Chrome","black",Helper.TEST_USER_EMAIL,""},
                {"Chrome","black",Helper.TEST_USER_EMAIL,Helper.getPassword()},
                {"Chrome","black",Helper.getEmail(),Helper.TEST_USER_PASSWORD},

                {"Yandex","blue","",Helper.TEST_USER_PASSWORD},
                {"Yandex","blue",Helper.TEST_USER_EMAIL,""},
                {"Yandex","blue",Helper.TEST_USER_EMAIL,Helper.getPassword()},
                {"Yandex","blue",Helper.getEmail(),Helper.TEST_USER_PASSWORD},

                {"Yandex","black","",Helper.TEST_USER_PASSWORD},
                {"Yandex","black",Helper.TEST_USER_EMAIL,""},
                {"Yandex","black",Helper.TEST_USER_EMAIL,Helper.getPassword()},
                {"Yandex","black",Helper.getEmail(),Helper.TEST_USER_PASSWORD},
        };
    }

    @Before
    public void init() throws Exception{
        driver = Helper.connectWebDriver(browser);
        driver.get(Helper.BASE_URI);
    }

    @DisplayName("Signin by personal account button on home page with not valid data should fail")
    @Description("We authorize the user using the personal account button on the main page with not valid data and expect that authorization should fail")
    @Test
    public void signinByPersonalAccountButtonOnHomePageWithNotValidDataShouldFail() throws Exception{
        HomePage homePage=new HomePage(driver);
        homePage.chooseAndClickPersonalAccountButton(buttonType);
        SigninPage signInPage =new SigninPage(driver);
        signInPage.fillSigninForm(email,password);
        assertTrue(signInPage.checkSignInText());
    }

    @DisplayName("Signin by personal account button in signin form with not valid data should fail")
    @Description("We authorize the user using the personal account button in signin form with not valid data and expect that authorization should fail")
    @Test
    public void signinByPersonalAccountButtonInSigninFormWithNotValidDataShouldFail() throws Exception{
        HomePage homePage=new HomePage(driver);
        homePage.chooseAndClickPersonalAccountButton(buttonType);
        SigninPage signInPage =new SigninPage(driver);
        signInPage.clickSignUpLink();
        SignupPage signupPage=new SignupPage(driver);
        signupPage.clickSigninLink();
        signInPage.fillSigninForm(email,password);
        assertTrue(signInPage.checkSignInText());
    }

    @DisplayName("Signin by personal account button in recover password form with not valid data should fail")
    @Description("We authorize the user using the personal account button in recover password form with not valid data and expect that authorization should fail")
    @Test
    public void signinByPersonalAccountButtonInRecoverPasswordFormWithNotValidDataShouldFail() throws Exception{
        HomePage homePage=new HomePage(driver);
        homePage.chooseAndClickPersonalAccountButton(buttonType);
        SigninPage signInPage =new SigninPage(driver);
        signInPage.clickRecoverPasswordLink();
        RecoverPasswordPage recoverPasswordPage =new RecoverPasswordPage(driver);
        recoverPasswordPage.clickSigninLink();
        signInPage.fillSigninForm(email,password);
        assertTrue(signInPage.checkSignInText());
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}

