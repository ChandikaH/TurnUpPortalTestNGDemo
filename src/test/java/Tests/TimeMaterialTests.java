package Tests;

import Pages.HomePage;
import Pages.LoginPage;
import Pages.TimeMaterialPage;
import Utilities.CommonDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TimeMaterialTests extends CommonDriver {
    WebDriver webDriver = new ChromeDriver();
    //Login page object initialization and definition
    LoginPage loginPageObj = new LoginPage();
    //Home page object initialization and definition
    HomePage homePageObj = new HomePage();
    //TM page object initialization and definition
    TimeMaterialPage tmPageObj = new TimeMaterialPage(webDriver);

    @BeforeTest
    public void SetUpTimeMaterial() {
        //Open Chrome Browser

        loginPageObj.LoginActions(webDriver, "hari", "123123");
        homePageObj.VerifyLoggedInUser(webDriver);
        System.out.println("User logged in successfully");
        homePageObj.NavigateToTimeMaterialPage(webDriver);
    }

    /*
     *This test is for testing the Time record creation
     *these are the test data used for the test
     * "Keyboard", "M","Unknown Material","500"
     */
    @Test(priority = 1, description = "This test create a new Time/Material record with valid details")
    public void TestCreateTimeMaterialRecord() {
        tmPageObj.CreateTimeRecord(webDriver, "Keyboard", "M","Unknown Material","500");
        tmPageObj.CreateTMAssertion(webDriver, "Keyboard", "M","Unknown Material","$500.00");
    }

    @Test(priority = 2, description = "This test update the Time/Material record with valid details")
    public void TestUpdateTimeMaterialRecord() {
        tmPageObj.EditTimeRecord(webDriver, "Mouse", "M","Known Material","100");
        tmPageObj.EditTMAssertion(webDriver,"Mouse", "M","Known Material","100");
    }

    @Test(priority = 3, description = "This test delete the last Time/Material record")
    public void TestDeleteTimeMaterialRecord() {
        tmPageObj.DeleteTimeRecord(webDriver);
        tmPageObj.DeleteTMAssertion(webDriver,"M","Know Material","100");
    }

    @AfterTest
    public void CloseTestRun() {
        webDriver.quit();
    }
}
