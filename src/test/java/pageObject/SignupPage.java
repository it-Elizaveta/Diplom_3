package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignupPage {
    private final WebDriver driver;
    private final By nameField=By.xpath(".//fieldset[1]//input");
    private final By emailField=By.xpath(".//fieldset[2]//input");
    private final By passwordField=By.xpath(".//input[@type='password']");
    private final By signUpButton=By.xpath(".//button[text()='Зарегистрироваться']");
    private final By passwordInputErrorMessage=By.cssSelector("p.input__error.text_type_main-default");
    private final By signinLink=By.xpath(".//a[text()='Войти']");

    public SignupPage(WebDriver driver){
        this.driver=driver;
    }

    public void inputName(String name){
        driver.findElement(nameField).sendKeys(name);
    }

    public void inputEmail(String email){
        driver.findElement(emailField).sendKeys(email);
    }

    public void inputPassword(String password){
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickSignUpButton(){
        driver.findElement(signUpButton).click();
    }

    @Step("Fill 'Sign up' form and click 'Sign up' button")
    public void fillSignUpForm(String name, String email, String password){
        inputName(name);
        inputEmail(email);
        inputPassword(password);
        clickSignUpButton();
    }

    @Step("Check password error message")
    public boolean checkPasswordErrorMessage(){
        return driver.findElement(passwordInputErrorMessage).getText().equalsIgnoreCase("Некорректный пароль");
    }

    @Step("Click 'Sign in' link")
    public void clickSigninLink(){
        driver.findElement(signinLink).click();
    }

    @Step("Check that 'Sign up' button is displayed")
    public boolean signUpButtonIsDisplayed(){
        return driver.findElement(signUpButton).isDisplayed();
    }
}
