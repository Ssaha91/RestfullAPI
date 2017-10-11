package testPageObjects;

import base.CommonAPI;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import pageobject.HomePages;

public class TestHomePages extends CommonAPI {
    @Test(enabled = true)
    public void testPacticeButton() throws InterruptedException {
        HomePages hp = PageFactory.initElements(driver, HomePages.class);
        hp.setPracticeButton();
        test.log(LogStatus.INFO, "go practicePage");
    }
}
