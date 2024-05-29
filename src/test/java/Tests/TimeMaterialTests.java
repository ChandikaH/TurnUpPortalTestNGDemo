package Tests;

import Pages.HomePage;
import Pages.LoginPage;
import Pages.TimeMaterialPage;
import Utilities.CommonDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TimeMaterialTests extends CommonDriver {
    //Login page object initialization and definition
    LoginPage loginPageObj = new LoginPage();
    //Home page object initialization and definition
    HomePage homePageObj = new HomePage();
    //TM page object initialization and definition
    TimeMaterialPage tmPageObj = new TimeMaterialPage();

    @BeforeTest
    public void SetUpTimeMaterial() {
        //Open Chrome Browser
        webDriver = new ChromeDriver();
        loginPageObj.LoginActions(webDriver, "hari", "123123");
        homePageObj.VerifyLoggedInUser(webDriver);
        System.out.println("User logged in successfully");
        homePageObj.NavigateToTimeMaterialPage(webDriver);
    }

    /*
     *This test is for testing the Time record creation
     *these are the test data used for the test
     *Type = IC2024March
     */
    @Test(priority = 1, description = "This test create a new Time/Material record with valid details")
    public void TestCreateTimeMaterialRecord() {
        tmPageObj.CreateTimeRecord(webDriver);
    }

    @Test(priority = 2, description = "This test update the Time/Material record with valid details")
    public void TestUpdateTimeMaterialRecord() {
        tmPageObj.EditNewlyCreatedTMRecord(webDriver);
    }

    @Test(priority = 3, description = "This test delete the last Time/Material record")
    public void TestDeleteTimeMaterialRecord() {
        tmPageObj.DeleteTMRecord(webDriver);
    }

    @AfterTest
    public void CloseTestRun() {
        webDriver.quit();
    }
}
