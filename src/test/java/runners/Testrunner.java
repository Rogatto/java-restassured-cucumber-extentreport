package runners;


import com.cucumber.listener.Reporter;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

import java.io.File;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = { "com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"},
        features = {"src/test/java/features"},
        glue = {"steps"}
)

public class Testrunner {

    @AfterClass
    public static void writeExtentReport() {
        Reporter.loadXMLConfig(new File("config/report.xml"));
    }
}
