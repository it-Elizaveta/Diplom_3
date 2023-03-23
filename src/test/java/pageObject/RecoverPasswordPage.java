package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RecoverPasswordPage {
    private final WebDriver driver;
    private final By signinLink=By.xpath(".//a[text()='Войти']");

    public RecoverPasswordPage(WebDriver driver){
        this.driver=driver;
    }

    @Step("Click 'Sign in' link")
    public void clickSigninLink(){
        driver.findElement(signinLink).click();
    }
}
