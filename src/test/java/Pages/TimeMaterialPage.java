package Pages;

import Utilities.WaitUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import static Utilities.WaitUtils.waitFor;

public class TimeMaterialPage {
    public void CreateTimeRecord(WebDriver webDriver) {
        //Create a new Time/Material record

        //click on the Create New Button
        WebElement createNewButton = webDriver.findElement(By.xpath("//*[@id=\"container\"]/p/a"));
        createNewButton.click();

        WaitUtils.WaitToBeVisible(webDriver, "Xpath", "//*[@id=\"TimeMaterialEditForm\"]/div/div[1]/div/span[1]/span/span[1]", 5);

        //Select Time from dropdown
        WebElement typeCodeMainDropdown = webDriver.findElement(By.xpath("//*[@id=\"TimeMaterialEditForm\"]/div/div[1]/div/span[1]/span/span[1]"));
        typeCodeMainDropdown.click();
        WebElement timeTypeCode = webDriver.findElement(By.xpath("//ul[@id='TypeCode_listbox']/li[2]"));
        timeTypeCode.click();

        //Enter Code
        WebElement codeTextbox = webDriver.findElement(By.id("Code"));
        codeTextbox.sendKeys("ICMarch2024");

        //Enter Description
        WebElement descriptionTextbox = webDriver.findElement(By.id("Description"));
        descriptionTextbox.sendKeys("ICMarch2024 Description");

        WaitUtils.WaitToBeVisible(webDriver, "Xpath", "//*[@id=\"TimeMaterialEditForm\"]/div/div[4]/div/span[1]/span/input[1]", 5);

        //Enter Price
        WebElement priceTextbox = webDriver.findElement(By.xpath("//*[@id=\"TimeMaterialEditForm\"]/div/div[4]/div/span[1]/span/input[1]"));
        priceTextbox.sendKeys("250.00");

        //click on Select File button and select a file

        //click on save button
        WaitUtils.WaitToBeVisible(webDriver, "Id", "SaveButton", 5);
        WebElement saveButton = webDriver.findElement(By.id("SaveButton"));
        saveButton.click();

        waitFor(5000);

        //Check if a new Time/Material record has been created successfully
        WebElement goToLastPageButton = webDriver.findElement(By.xpath("//*[@id=\"tmsGrid\"]/div[4]/a[4]/span"));
        goToLastPageButton.click();
        VerifyRecordCreated(webDriver);
    }

    public void VerifyRecordCreated(WebDriver webDriver) {
        WebElement newCode = webDriver.findElement(By.xpath("//*[@id=\"tmsGrid\"]/div[3]/table/tbody/tr[last()]/td[1]"));
            /*if (newCode.Text == "ICMarch")
            {
                Assert.Pass("New Time record has been created successfully");
            }
            else
            {
                Assert.Fail("New Time record has not been created");
            }*/

        Assert.assertEquals(newCode.getText(), "ICMarch2024", "New Time record has not been created");
    }

    public void EditNewlyCreatedTMRecord(WebDriver webDriver) {
        //Code for Edit Time Record
        WebElement goToLastPageButton = webDriver.findElement(By.xpath("//*[@id=\"tmsGrid\"]/div[4]/a[4]/span"));
        goToLastPageButton.click();
        waitFor(3000);

        //click on Edit Button
        WebElement editButton = webDriver.findElement(By.xpath("//tbody/tr[last()]/td[5]/a[1]"));
        editButton.click();
        waitFor(3000);

        //Edit Code in Code Textbox
        WebElement editCodeTextbox = webDriver.findElement(By.id("Code"));
        editCodeTextbox.clear();
        editCodeTextbox.sendKeys("IC2024Edited");

        //Edit Description in Description Textbox
        WebElement editDescriptionTextBox = webDriver.findElement(By.id("Description"));
        editDescriptionTextBox.clear();
        editDescriptionTextBox.sendKeys("IC2024Edited");

        //Edit Price in Price Textbox
        WaitUtils.WaitToBeVisible(webDriver, "XPath", "//*[@id='TimeMaterialEditForm']/div/div[4]/div/span[1]/span/input[1]", 3);
        WebElement editPriceOverlappingTag = webDriver.findElement(By.xpath("//*[@id='TimeMaterialEditForm']/div/div[4]/div/span[1]/span/input[1]"));
        WebElement editPriceTextBox = webDriver.findElement(By.id("Price"));
        editPriceOverlappingTag.click();
        editPriceTextBox.clear();
        editPriceOverlappingTag.click();
        editPriceTextBox.sendKeys("500");

        //click on save button
        WebElement editSaveButton = webDriver.findElement(By.id("SaveButton"));
        editSaveButton.click();
        waitFor(4000);

        // Clock on goToLastPage Button
        WebElement editGoToLastPageButton = webDriver.findElement(By.xpath("//*[@id=\"tmsGrid\"]/div[4]/a[4]/span"));
        editGoToLastPageButton.click();

        WebElement editedCode = webDriver.findElement(By.xpath("//*[@id=\"tmsGrid\"]/div[3]/table/tbody/tr[last()]/td[1]"));
        WebElement EditedDescription = webDriver.findElement(By.xpath("//*[@id=\"tmsGrid\"]/div[3]/table/tbody/tr[last()]/td[3]"));
        Assert.assertEquals(editedCode.getText(), "IC2024Edited", "Time Record has not been updated");
    }

    public void DeleteTMRecord(WebDriver webDriver) {
        //Code for Delete Time Record
        WaitUtils.WaitToBeVisible(webDriver, "Xpath", "//*[@id=\"tmsGrid\"]/div[4]/a[4]/span", 5);
        WebElement goToLastPageButton = webDriver.findElement(By.xpath("//*[@id=\"tmsGrid\"]/div[4]/a[4]/span"));
        goToLastPageButton.click();
        waitFor(3000);

        //click on delete button
        WebElement deleteButton = webDriver.findElement(By.xpath("//*[@id=\"tmsGrid\"]/div[3]/table/tbody/tr[last()]/td[5]/a[2]"));
        deleteButton.click();

        Alert simpleAlert = webDriver.switchTo().alert();
        simpleAlert.accept();

        WebElement lastCodeInTable = webDriver.findElement(By.xpath("//*[@id=\"tmsGrid\"]/div[3]/table/tbody/tr[last()]/td[1]"));
        Assert.assertNotEquals(lastCodeInTable.getText(), "IC2024Edited", "Time Record has not been deleted");
    }
}
