package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SigninPage {
    private final WebDriver driver;
    private final By signInText=By.xpath(".//h2[text()='Вход']");
    private final By emailField=By.xpath(".//fieldset[@class='Auth_fieldset__1QzWN mb-6'][1]//input")  ;
    private final By passwordField=By.xpath(".//fieldset[@class='Auth_fieldset__1QzWN mb-6'][2]//input");
    private final By signinButton=By.xpath(".//button[text()='Войти']");
    private final By recoverPasswordLink=By.xpath(".//a[text()='Восстановить пароль']");
    private final By signUpLink =By.xpath(".//a[text()='Зарегистрироваться']");

    public SigninPage(WebDriver driver){
        this.driver=driver;
    }

    public void inputEmail(String email){
        driver.findElement(emailField).sendKeys(email);
    }

    public void inputPassword(String password){
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickSigninButton(){
        driver.findElement(signinButton).click();
    }

    @Step("Fill 'Sign in' form and click 'Sign in' button")
    public void fillSigninForm(String email,String password){
        inputEmail(email);
        inputPassword(password);
        clickSigninButton();
    }

    @Step("Click 'Sign up' link")
    public void clickSignUpLink(){
        driver.findElement(signUpLink).click();
    }

    @Step("Check text 'Вход'")
    public boolean checkSignInText(){
        return driver.findElement(signInText).getText().equalsIgnoreCase("Вход");
    }

    public void waitSignUpLinkIsVisible(){
        new WebDriverWait(driver,5)
                .until(ExpectedConditions.visibilityOfElementLocated(signUpLink));
    }

    @Step("Click 'Recover password' link")
    public void clickRecoverPasswordLink(){
        driver.findElement(recoverPasswordLink).click();
    }

}
