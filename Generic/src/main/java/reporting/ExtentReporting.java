package reporting;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentReporting {

    public static ExtentReports getInstance(String pathForReports) {
        ExtentReports extent;
        String Path = pathForReports + ".html";
        extent = new ExtentReports(Path, true);
        extent.addSystemInfo("Host", "Ibrahim Khan").addSystemInfo("Environment", "QA")
                .addSystemInfo("Framework" , "Hybrid");
        return extent;
    }
}

