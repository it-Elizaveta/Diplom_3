package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private final WebDriver driver;
    private final By personalAccountBlackButton=By.xpath(".//a[@href='/account']");
    private final By personalAccountBlueButton=By.xpath(".//button[text()='Войти в аккаунт']");
    private final By makeOrderButton=By.xpath(".//button[text()='Оформить заказ']");
    private final By bunsTab =By.xpath(".//section[@Class='BurgerIngredients_ingredients__1N8v2']/div[1]/div[1]");
    private final By saucesTab =By.xpath(".//section[@class='BurgerIngredients_ingredients__1N8v2']/div/div[2]");
    private final By fillingsTab =By.xpath(".//section[@class='BurgerIngredients_ingredients__1N8v2']/div/div[3]");

    public HomePage(WebDriver driver){
        this.driver=driver;
    }

    @Step("Click 'Personal Account' Button (black color)")
    public void clickPersonalAccountBlackButton (){
        driver.findElement(personalAccountBlackButton).click();
    }

    @Step("Click 'Personal Account' Button (blue color)")
    public void clickPersonalAccountBlueButton (){
        driver.findElement(personalAccountBlueButton).click();
    }

    public void chooseAndClickPersonalAccountButton(String buttonType) throws Exception{
        if (buttonType.equals("blue")) {
            clickPersonalAccountBlueButton();
        } else if (buttonType.equals("black")){
            clickPersonalAccountBlackButton();
        } else {
            throw new Exception("Необходимо указать \"black\" или \"blue\"");
        }
    }

    public void waitMakeOrderButtonIsVisible(){
        new WebDriverWait(driver,5)
                .until(ExpectedConditions.visibilityOfElementLocated(makeOrderButton));
    }

    @Step("Check that 'Make order' button is displayed")
    public boolean makeOrderButtonIsDisplayed(){
        return driver.findElement(makeOrderButton).isDisplayed();
    }

    @Step("Click 'Buns' tab")
    public void clickBunsTab(){
        driver.findElement(bunsTab);
    }

    @Step("Click 'Sauces' tab")
    public void clickSaucesTab(){
        driver.findElement(saucesTab).click();
    }

    @Step("Click 'Fillings' tab")
    public void clickFillingsTab(){
        driver.findElement(fillingsTab).click();
    }

    @Step("Check that Buns tab is current tab")
    public boolean checkBunsTabIsCurrent(){
        String extractClass= driver.findElement(bunsTab).getAttribute("class");
        return extractClass.contains("tab_tab_type_current__2BEPc");
    }

    @Step("Check that Sauces tab is current tab")
    public boolean checkSaucesTabIsCurrent(){
        String extractClass= driver.findElement(saucesTab).getAttribute("class");
        return extractClass.contains("tab_tab_type_current__2BEPc");
    }
    @Step("Check that Ingredients tab is current tab")
    public boolean checkFillingsTabIsCurrent(){
        String extractClass= driver.findElement(fillingsTab).getAttribute("class");
        return extractClass.contains("tab_tab_type_current__2BEPc");
    }
}
