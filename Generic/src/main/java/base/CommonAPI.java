package base;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import reporting.ExtentReporting;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class CommonAPI {

    public static ExtentReports report;
    public static ExtentTest test;
    public static WebDriver driver = null;
    private String saucelabs_username = "";
    private String saucelabs_accesskey = "";
    private String browserstack_username = "";
    private String browserstack_accesskey = "";

    @Parameters({"useCloudEnv", "cloudEnvName", "os", "os_version", "browser_name", "browser_version", "url", "pathForReports"})
    @BeforeMethod
    public void setUp(boolean useCloudEnv, String cloudEnvName, String os, String os_version,
                      String browser_name, String browser_version, String url, String pathForReports) throws IOException {
        if (useCloudEnv == true) {
            if (cloudEnvName.equalsIgnoreCase("browserstack")) {
                getCloudDriver(cloudEnvName, browserstack_username, browserstack_accesskey, os,
                        os_version, browser_name, browser_version, pathForReports);
            } else if (cloudEnvName.equalsIgnoreCase("saucelabs")) {
                getCloudDriver(cloudEnvName, saucelabs_username, saucelabs_accesskey, os,
                        os_version, browser_name, browser_version, pathForReports);
            }
        } else {
            getLocalDriver(os, browser_name, pathForReports);
        }
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(25, TimeUnit.SECONDS);
        driver.navigate().to(url);
        driver.manage().window().maximize();
    }
    public WebDriver getLocalDriver(String os, String browser_name, String pathForReports) {
        report = ExtentReporting.getInstance(pathForReports);
        test= report.startTest("tetsLetsCode");
        os = os.toLowerCase();

        if (browser_name.equalsIgnoreCase("chrome")) {
            if (os.contains("win")) {
                System.setProperty("webdriver.chrome.driver", "../Generic/driver/chromedriver.exe");
                driver = new ChromeDriver();
                test.log(LogStatus.INFO, "excuted chrom driver for windoow");
            } else if (os.contains("mac") || os.contains("os x")) {
                System.setProperty("webdriver.chrome.driver", "../Generic/driver/chromedriver");
                driver = new ChromeDriver();
            }
        } else if (browser_name.equalsIgnoreCase("firefox")) {
            if (os.contains("win")) {
                System.setProperty("webdriver.gecko.driver", "../Generic/driver/geckodriver.exe");
                driver = new FirefoxDriver();
            } else if (os.contains("mac") || os.contains("os x")) {
                System.setProperty("webdriver.gecko.driver", "../Generic/driver/geckodriver");
                driver = new FirefoxDriver();
            }
        }
        return driver;
    }
    public WebDriver getCloudDriver(String envName, String envUsername, String envAccessKey, String os,
                                    String os_version, String browser_name, String browser_version, String pathForReports)
            throws IOException {

        report = ExtentReporting.getInstance(pathForReports);
        test= report.startTest("tetsLetsCode");

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("browser", browser_name);
        cap.setCapability("browser_version", browser_version);
        cap.setCapability("os", os);
        cap.setCapability("os_version", os_version);
        if (envName.equalsIgnoreCase("Saucelabs")) {
            driver = new RemoteWebDriver(new URL("http://" + envUsername + ":" + envAccessKey +
                    "@ondemand.saucelabs.com:80/wd/hub"), cap);
        } else if (envName.equalsIgnoreCase("Browserstack")) {
            cap.setCapability("resolution", "1024x768");
            driver = new RemoteWebDriver(new URL("http://" + envUsername + ":" + envAccessKey +
                    "@hub-cloud.browserstack.com/wd/hub"), cap);
        }
        return driver;
    }
    @Parameters("path")
    @AfterMethod
    public void tearDown(ITestResult testResult, @Optional String Path) throws IOException {
        String path = null;
        String imagePath = null;

        driver.quit();
        report.endTest(test);
        report.flush();
    }

    public String takeScrinshoot(String fileName, String path) throws IOException {
        fileName = fileName + ".png";
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(sourceFile, new File(path + fileName));
        String destination = path + fileName;
        return destination;

    }

}

