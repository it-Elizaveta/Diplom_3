import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageObject.HomePage;
import pageObject.SigninPage;

public class Helper {
    public static final String BASE_URI ="https://stellarburgers.nomoreparties.site/";
    public static final String TEST_USER_NAME ="My Test User Name";
    public static final String TEST_USER_EMAIL ="MyTestUserEmail@yandex.ru";
    public static final String TEST_USER_PASSWORD ="MyTestUserPassword";

    public static String getName(){
        return new Faker().name().name();
    }

    public static String getEmail(){
        return new Faker().internet().emailAddress();
    }

    public static String getPassword(){
        return new Faker().internet().password(6,50);
    }

    public static WebDriver connectWebDriver (String browser) throws Exception{
        if (browser.equals("Chrome")) {
            System.setProperty("webdriver.chrome.driver","C:\\myJavaProjects\\Diplom\\Diplom_3\\src\\test\\java\\chromedriver.exe");
            ChromeOptions options=new ChromeOptions();
            options.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
            return new ChromeDriver(options);
        } else if (browser.equals("Yandex")) {
            System.setProperty("webdriver.chrome.driver","C:\\myJavaProjects\\Diplom\\Diplom_3\\src\\test\\java\\yandexdriver.exe");
            ChromeOptions options=new ChromeOptions();
            options.setBinary("C:/Users/User/AppData/Local/Yandex/YandexBrowser/Application/browser.exe");
            return new ChromeDriver(options);
        } else throw new Exception("Необходимо указать \"Chrome\" или \"Yandex\"");
    }

    public static void signInTestUser(WebDriver driver){
        HomePage homePage=new HomePage(driver);
        homePage.clickPersonalAccountBlackButton();
        SigninPage signInPage =new SigninPage(driver);
        signInPage.fillSigninForm(Helper.TEST_USER_EMAIL,Helper.TEST_USER_PASSWORD);
        homePage.waitMakeOrderButtonIsVisible();
    }
}
