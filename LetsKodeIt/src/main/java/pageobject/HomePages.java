package pageobject;

import base.CommonAPI;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePages extends CommonAPI {

    @FindBy(xpath = ".//*[@id='navbar']/div/div/div/ul/li[1]/a")
    WebElement practiceButton;

    public void setPracticeButton() throws InterruptedException {
        practiceButton.click();
        Thread.sleep(1500);
        driver.navigate().back();
    }
}
