package pageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PersonalAccountPage {
    private final WebDriver driver;
    private final By nameField=By.xpath(".//li[@class='Profile_profileListItem__2th0t mb-6'][1]//input");
    private final By loginField=By.xpath(".//li[@class='Profile_profileListItem__2th0t mb-6'][2]//input");
    private final By signoutButton=By.xpath(".//button[text()='Выход']");
    private final By constructorButton=By.xpath(".//header//li[1]//a");
    private final By logo =By.xpath(".//header//div[@class='AppHeader_header__logo__2D0X2']/a");

    public PersonalAccountPage (WebDriver driver){
        this.driver=driver;
    }

    @Step("Click 'Constructor' button")
    public void clickConstructorButton(){
        driver.findElement(constructorButton).click();
    }

    @Step("Click Logo")
    public void clickLogo(){
        driver.findElement(logo).click();
    }

    @Step("Click 'Sign out' button")
    public void clickSignoutButton(){
        driver.findElement(signoutButton).click();
    }

    @Step("Get name from 'Name field'")
    public String getName(){
        return driver.findElement(nameField).getAttribute("value");
    }

    @Step("Get email from 'Email field'")
    public String getEmail(){
        return driver.findElement(loginField).getAttribute("value");
    }

    @Step("Check Name and Email")
    public boolean checkNameAndEmail(String name,String email){
        return getName().equals(name)&&getEmail().equalsIgnoreCase(email);
    }

    public void waitNameFieldIsVisible(){
        new WebDriverWait(driver,5)
                .until(ExpectedConditions.visibilityOfElementLocated(nameField));
    }
}
