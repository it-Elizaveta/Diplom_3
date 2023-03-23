import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pageObject.HomePage;
import pageObject.SigninPage;
import pageObject.SignupPage;

@RunWith(Parameterized.class)
public class SignupTest {
    private  WebDriver driver;
    private final String browser;
    private final String buttonType;
    private final String correctPassword;
    private final String incorrectPassword;

    public SignupTest(String browser,String buttonType,String correctPassword,String incorrectPassword){
        this.browser=browser;
        this.buttonType=buttonType;
        this.correctPassword=correctPassword;
        this.incorrectPassword=incorrectPassword;
    }

    @Parameterized.Parameters
    public static Object[][] getData(){
        return new Object[][] {
                {"Chrome","black","1q2w3e"," "},
                {"Chrome","black","абвгдеё","r"},
                {"Chrome","black","вкусно и .","б1"},
                {"Chrome","black","Wowwowwowwowwow123","!и g"},
                {"Chrome","black","Pneumonoultramicroscopicsilicovolcanoconiosis","5@?><"},
                {"Chrome","blue","1q2w3e"," "},
                {"Chrome","blue","абвгдеё","r"},
                {"Chrome","blue","вкусно и .","б1"},
                {"Chrome","blue","Wowwowwowwowwow123","!и g"},
                {"Chrome","blue","Pneumonoultramicroscopicsilicovolcanoconiosis","5@?><"},
                {"Yandex","black","1q2w3e"," "},
                {"Yandex","black","абвгдеё","r"},
                {"Yandex","black","вкусно и .","б1"},
                {"Yandex","black","Wowwowwowwowwow123","!и g"},
                {"Yandex","black","Pneumonoultramicroscopicsilicovolcanoconiosis","5@?><"},
                {"Yandex","blue","1q2w3e"," "},
                {"Yandex","blue","абвгдеё","r"},
                {"Yandex","blue","вкусно и .","б1"},
                {"Yandex","blue","Wowwowwowwowwow123","!и g"},
                {"Yandex","blue","Pneumonoultramicroscopicsilicovolcanoconiosis","5@?><"},
        };
    }

    @Before
    public void init() throws Exception{
        driver = Helper.connectWebDriver(browser);
        driver.get(Helper.BASE_URI);
    }

    @DisplayName("Signup with valid data should pass")
    @Description("We signup a new user with valid data and expect successful registration")
    @Test
    public void signupWithValidDataShouldPass() throws Exception{
        HomePage homePage =new HomePage(driver);
        homePage.chooseAndClickPersonalAccountButton(buttonType);
        SigninPage signInPage=new SigninPage(driver);
        signInPage.clickSignUpLink();
        SignupPage signUpPage=new SignupPage(driver);
        signUpPage.fillSignUpForm(Helper.getName(),Helper.getEmail(), correctPassword);
        signInPage.waitSignUpLinkIsVisible();
        Assert.assertTrue(signInPage.checkSignInText());
    }

    @DisplayName("Signup with not valid password should fail")
    @Description("We signup a new user with not valid password and expect that user will not be registered")
    @Test
    public void signupWithNotValidPasswordShouldFail() throws Exception{
        HomePage homePage =new HomePage(driver);
        homePage.chooseAndClickPersonalAccountButton(buttonType);
        SigninPage signInPage=new SigninPage(driver);
        signInPage.clickSignUpLink();
        SignupPage signUpPage=new SignupPage(driver);
        signUpPage.fillSignUpForm(Helper.getName(),Helper.getEmail(),incorrectPassword);
        Assert.assertTrue(signUpPage.checkPasswordErrorMessage());
    }

    @DisplayName("Signup without password should fail")
    @Description("We signup a new user without password and expect that user will not be registered")
    @Test
    public void signupWithoutPasswordShouldFail() throws Exception{
        HomePage homePage =new HomePage(driver);
        homePage.chooseAndClickPersonalAccountButton(buttonType);
        SigninPage signInPage=new SigninPage(driver);
        signInPage.clickSignUpLink();
        SignupPage signUpPage=new SignupPage(driver);
        signUpPage.fillSignUpForm(Helper.getName(),Helper.getEmail(),"");
        Assert.assertTrue(signUpPage.signUpButtonIsDisplayed());
    }

    @DisplayName("Signup without name should fail")
    @Description("We signup a new user without name and expect that user will not be registered")
    @Test
    public void signupWithoutNameShouldFail() throws Exception{
        HomePage homePage =new HomePage(driver);
        homePage.chooseAndClickPersonalAccountButton(buttonType);
        SigninPage signInPage=new SigninPage(driver);
        signInPage.clickSignUpLink();
        SignupPage signUpPage=new SignupPage(driver);
        signUpPage.fillSignUpForm("",Helper.getEmail(),Helper.getPassword());
        Assert.assertTrue(signUpPage.signUpButtonIsDisplayed());
    }

    @DisplayName("Signup without email should fail")
    @Description("We signup a new user without email and expect that user will not be registered")
    @Test
    public void signupWithoutEmailShouldFail() throws Exception{
        HomePage homePage =new HomePage(driver);
        homePage.chooseAndClickPersonalAccountButton(buttonType);
        SigninPage signInPage=new SigninPage(driver);
        signInPage.clickSignUpLink();
        SignupPage signUpPage=new SignupPage(driver);
        signUpPage.fillSignUpForm(Helper.getName(),"",Helper.getPassword());
        Assert.assertTrue(signUpPage.signUpButtonIsDisplayed());
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
