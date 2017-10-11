package testPageObjects;

import base.CommonAPI;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import pageobject.HomePages;
import pageobject.PracticePages;

public class TestPracticePages extends CommonAPI{
    @Test
    public void goRadioBtns() throws InterruptedException {
        HomePages hp = PageFactory.initElements(driver, HomePages.class);
        hp.setPracticeButton();
        PracticePages ph = PageFactory.initElements(driver, PracticePages.class);
        ph.clickOnRadioBtns();
    }
}
