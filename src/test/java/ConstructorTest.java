import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pageObject.HomePage;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class ConstructorTest {
    private WebDriver driver;
    private final String browser;

    public ConstructorTest(String browser){
        this.browser=browser;
    }

    @Parameterized.Parameters
    public static Object[][] chooseBrowser(){
        return new Object[][] {
                {"Chrome"},
                {"Yandex"}
        };
    }

    @Before
    public void init() throws Exception{
        driver = Helper.connectWebDriver(browser);
        driver.get(Helper.BASE_URI);
    }

    @DisplayName("Go to \"Buns\" tab with authorization is successful")
    @Description("The authorized user goes to the \"Buns\" tab and we expect that the transition will be successful")
    @Test
    public void goToBunsTabWithAuthorizationIsSuccessful (){
        Helper.signInTestUser(driver);
        HomePage homePage=new HomePage(driver);
        homePage.clickBunsTab();
        assertTrue(homePage.bunsTextIsDisplayed());
    }

    @DisplayName("Go to \"Sauces\" tab with authorization is successful")
    @Description("The authorized user goes to the \"Sauces\" tab and we expect that the transition will be successful")
    @Test
    public void goToSaucesTabWithAuthorizationIsSuccessful (){
        Helper.signInTestUser(driver);
        HomePage homePage=new HomePage(driver);
        homePage.clickSaucesTab();
        assertTrue(homePage.saucesTextIsDisplayed());
    }

    @DisplayName("Go to \"Fillings\" tab with authorization is successful")
    @Description("The authorized user goes to the \"Fillings\" tab and we expect that the transition will be successful")
    @Test
    public void goToFillingsTabWithAuthorizationIsSuccessful (){
        Helper.signInTestUser(driver);
        HomePage homePage=new HomePage(driver);
        homePage.clickFillingsTab();
        assertTrue(homePage.fillingsTextIsDisplayed());
    }

    @DisplayName("Go to \"Buns\" tab without authorization is successful")
    @Description("An unauthorized user goes to the \"Buns\" tab and we expect that the transition will be successful")
    @Test
    public void goToBunsTabWithoutAuthorizationIsSuccessful (){
        HomePage homePage=new HomePage(driver);
        homePage.clickBunsTab();
        assertTrue(homePage.bunsTextIsDisplayed());
    }

    @DisplayName("Go to \"Sauces\" tab without authorization is successful")
    @Description("An unauthorized user goes to the \"Sauces\" tab and we expect that the transition will be successful")
    @Test
    public void goToSaucesTabWithoutAuthorizationIsSuccessful (){
        HomePage homePage=new HomePage(driver);
        homePage.clickSaucesTab();
        assertTrue(homePage.saucesTextIsDisplayed());
    }

    @DisplayName("Go to \"Fillings\" tab without authorization is successful")
    @Description("An unauthorized user goes to the \"Fillings\" tab and we expect that the transition will be successful")
    @Test
    public void goToFillingsTabWithoutAuthorizationIsSuccessful (){
        HomePage homePage=new HomePage(driver);
        homePage.clickFillingsTab();
        assertTrue(homePage.fillingsTextIsDisplayed());
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
