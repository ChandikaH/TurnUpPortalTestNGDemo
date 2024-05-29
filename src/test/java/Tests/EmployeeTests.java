package Tests;

import Pages.EmployeePage;
import Pages.HomePage;
import Pages.LoginPage;
import Utilities.CommonDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class EmployeeTests extends CommonDriver {
    //Login page object initialization and definition
    LoginPage loginPageObj = new LoginPage();
    //Home page object initialization and definition
    HomePage homePageObj = new HomePage();
    //Employee page object initialization and definition
    EmployeePage employeePageObj = new EmployeePage();

    @BeforeTest
    public void SetUp() {
        //Open Chrome Browser
        webDriver = new ChromeDriver();

        loginPageObj.LoginActions(webDriver, "hari", "123123");
        System.out.println("User logged in successfully - EmployeeTests");
        homePageObj.VerifyLoggedInUser(webDriver);
        System.out.println("User logged in successfully - EmployeeTests");
        homePageObj.NavigateToEmployeePage(webDriver);
    }

    @Test(priority = 1, description = "This test create a Employee record with valid details")
    public void TestCreateEmployeeRecord() {
        employeePageObj.CreateEmployeeRecord(webDriver);
    }

    @Test(priority = 2, description = "This test update the Employee record with valid details")
    public void TestUpdateEmployeeRecord() {
        employeePageObj.EditEmployeeRecord(webDriver);
    }

    @Test(priority = 3, description = "This test delete the last Employee record")
    public void TestDeleteEmployeeRecord() {
        employeePageObj.DeleteEmployeeRecord(webDriver);
    }

    @AfterTest
    public void CloseTestRun() {
        webDriver.quit();
    }
}
