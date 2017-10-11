package pageobject;

import base.CommonAPI;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PracticePages extends CommonAPI {
    @FindBy(xpath = ".//div[@id='radio-btn-example']//label//input")
    List<WebElement> radioBtns;

    public void clickOnRadioBtns() throws InterruptedException {
        for (WebElement x: radioBtns) {
            x.click();
            test.log(LogStatus.INFO, "Click in radio Buttons Test 1");
        }
        test.log(LogStatus.INFO, "Click in radio Buttons TEst 2");
    }
}
