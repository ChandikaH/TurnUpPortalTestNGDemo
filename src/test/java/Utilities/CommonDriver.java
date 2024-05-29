package Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

public class CommonDriver {
    public WebDriver webDriver;

    public void SetUpBrowser() {
        // Specify the path to the chromedriver.exe executable
        System.setProperty("webdriver.chrome.driver", "C:\\software\\chromedriver.exe");

        // Use WebDriver Manager to set up ChromeDriver
        //WebDriverManager.chromedriver().setup();

        // Open Chrome Browser
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void CloseTestRun() {
        webDriver.close();
        webDriver.quit();
    }
}
