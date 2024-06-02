package Pages;

import Utilities.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class TimeMaterialPage {
    WebDriver driver;

    // Page Factory
    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Create New')]")
    WebElement _createButton;

    @FindBy(how = How.XPATH, using = "//*[@id='TimeMaterialEditForm']/div/div[1]/div/span[1]")
    WebElement _typeCodeDropdown;

    @FindBy(how = How.XPATH, using = "//*[@id='TypeCode_listbox']/li[1]")
    WebElement _materialOption;

    @FindBy(how = How.ID, using = "Code")
    WebElement _codeTextBox;

    @FindBy(how = How.ID, using = "Description")
    WebElement _descriptionTextBox;

    @FindBy(how = How.XPATH, using = "//*[@id='TimeMaterialEditForm']/div/div[4]/div/span[1]/span/input[1]")
    WebElement _priceInputTag;

    @FindBy(how = How.XPATH, using = "//*[@id='Price']")
    WebElement _pricePerUnit;

    @FindBy(how = How.ID, using = "SaveButton")
    WebElement _saveButton;

    public TimeMaterialPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void CreateTimeRecord(WebDriver driver, String code, String typeCode, String description, String price) {

        // Click on create new button
        // WebElement createButton = driver.findElement(By.xpath("//*[@id='container']/p/a"));
        // createButton.click();
        _createButton.click();

        WaitUtils.WaitToBeClickable(driver, "xpath", "//*[@id='TimeMaterialEditForm']/div/div[1]/div/span[1]", 2);

        // Select Material from type code dropdown
        // WebElement typeCodeDropdown = driver.findElement(By.xpath("//*[@id='TimeMaterialEditForm']/div/div[1]/div/span[1]"));
        // typeCodeDropdown.click();
        _typeCodeDropdown.click();

        WaitUtils.WaitToBeClickable(driver, "xpath", "//*[@id='TypeCode_listbox']/li[1]", 2);

        //WebElement materialOption = driver.findElement(By.xpath("//*[@id='TypeCode_listbox']/li[1]"));
        //materialOption.click();
        _materialOption.click();

        // Identify code text box and enter a code
        //WebElement codeTextbox = driver.findElement(By.id("Code"));
        //codeTextbox.sendKeys(code);
        _codeTextBox.sendKeys(code);

        // Identify description text box and enter a description
        //WebElement descriptionTextbox = driver.findElement(By.id("Description"));
        //descriptionTextbox.sendKeys(description);
        _descriptionTextBox.sendKeys(description);

        // Identify price per unit text box and enter a code
        //WebElement priceInputTag = driver.findElement(By.xpath("//*[@id='TimeMaterialEditForm']/div/div[4]/div/span[1]/span/input[1]"));
        //priceInputTag.click();
        _priceInputTag.click();

        //WebElement pricePerUnit = driver.findElement(By.xpath("//*[@id='Price']"));
        //pricePerUnit.clear();
        //pricePerUnit.sendKeys(price);
        _pricePerUnit.clear();
        _pricePerUnit.sendKeys(price);

        // Click on save button
        //WebElement saveButton = driver.findElement(By.id("SaveButton"));
        //saveButton.click();
        _saveButton.click();

    }

    public void CreateTMAssertion(WebDriver driver, String code, String typeCode, String description, String price) {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // Wait till the last page button is clickable
        WaitUtils.WaitToBeClickable(driver, "xpath", "//*[@id='tmsGrid']/div[4]/a[4]/span", 20);

        // Click on go to last page button
        WebElement goToLastPageButton = driver.findElement(By.xpath("//*[@id='tmsGrid']/div[4]/a[4]/span"));
        goToLastPageButton.click();

        // Check if material record has been created
        WaitUtils.WaitToBeVisible(driver, "xpath", "//*[@id='tmsGrid']/div[3]/table/tbody/tr[last()]/td[1]", 20);
        WebElement newCode = driver.findElement(By.xpath("//*[@id='tmsGrid']/div[3]/table/tbody/tr[last()]/td[1]"));
        WebElement newTypeCode = driver.findElement(By.xpath("//*[@id='tmsGrid']/div[3]/table/tbody/tr[last()]/td[2]"));
        WebElement newDescription = driver.findElement(By.xpath("//*[@id='tmsGrid']/div[3]/table/tbody/tr[last()]/td[3]"));
        WebElement newPrice = driver.findElement(By.xpath("//*[@id='tmsGrid']/div[3]/table/tbody/tr[last()]/td[4]"));

        Assert.assertEquals(newCode.getText(), code, "Material record hasn't been created");
        Assert.assertEquals(newTypeCode.getText(), typeCode, "Material record hasn't been created");
        Assert.assertEquals(newDescription.getText(), description, "Material record hasn't been created");
        Assert.assertEquals(newPrice.getText(), price, "Material record hasn't been created");
    }

    public void EditTimeRecord(WebDriver driver, String code, String typeCode, String description, String price) {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // Wait till the last page button is clickable
        WaitUtils.WaitToBeClickable(driver, "xpath", "//*[@id='tmsGrid']/div[4]/a[4]", 5);

        // Click on go to last page button
        WebElement goToLastPageButton = driver.findElement(By.xpath("//*[@id='tmsGrid']/div[4]/a[4]/span"));
        goToLastPageButton.click();

        WaitUtils.WaitToBeVisible(driver, "xpath", "//*[@id='tmsGrid']/div[3]/table/tbody/tr/td[1]", 5);

        WebElement findNewRecord = driver.findElement(By.xpath("//*[@id='tmsGrid']/div[3]/table/tbody/tr[last()]/td[1]"));

        if (findNewRecord.getText().contains("Keyboard")) {

            // Check if material record has been updated
            WebElement editButton = driver.findElement(By.xpath("//*[@id='tmsGrid']/div[3]/table/tbody/tr[last()]/td[5]/a[1]"));
            editButton.click();

        } else {
            System.out.println("Record to be edited not found.");
        }

        WaitUtils.WaitToBeClickable(driver, "xpath", "//*[@id='Code']", 5);
        // update code text box value
        WebElement codeTextbox = driver.findElement(By.xpath("//*[@id='Code']"));
        codeTextbox.clear();
        codeTextbox.sendKeys(code);

        // update description text box value
        WebElement descriptionTextbox = driver.findElement(By.id("Description"));
        descriptionTextbox.clear();
        descriptionTextbox.sendKeys(description);

        // update price per unit text box value
        WebElement priceInputTag = driver.findElement(By.xpath("//*[@id='TimeMaterialEditForm']/div/div[4]/div/span[1]/span/input[1]"));
        priceInputTag.click();

        WebElement pricePerUnit = driver.findElement(By.xpath("//*[@id='Price']"));
        pricePerUnit.clear();
        priceInputTag.click();
        pricePerUnit.sendKeys(price);

        // Click on save button
        WebElement saveButton = driver.findElement(By.id("SaveButton"));
        saveButton.click();

    }

    public void EditTMAssertion(WebDriver driver, String code, String typeCode, String description, String price) {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // Wait till the last page button is clickable
        WaitUtils.WaitToBeClickable(driver, "xpath", "//*[@id='tmsGrid']/div[4]/a[4]", 5);

        // Click on go to last page button
        WebElement goToLastPageButton = driver.findElement(By.xpath("//*[@id='tmsGrid']/div[4]/a[4]/span"));
        goToLastPageButton.click();

        // Check if material record has been updated
        WebElement updatedTypeCode = driver.findElement(By.xpath("//*[@id='tmsGrid']/div[3]/table/tbody/tr[last()]/td[1]"));
        WebElement updatedDescription = driver.findElement(By.xpath("//*[@id='tmsGrid']/div[3]/table/tbody/tr[last()]/td[3]"));
        WebElement updatedPrice = driver.findElement(By.xpath("//*[@id='tmsGrid']/div[3]/table/tbody/tr[last()]/td[4]"));

        Assert.assertTrue(updatedTypeCode.getText().contains(typeCode), "Material record hasn't been created");
        Assert.assertTrue(updatedDescription.getText().contains(description), "Material record hasn't been created");
        Assert.assertTrue(updatedPrice.getText().contains(price), "Material record hasn't been created");
    }

    public void DeleteTimeRecord(WebDriver driver) {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // Wait till the last page button is clickable
        WaitUtils.WaitToBeClickable(driver, "xpath", "//*[@id='tmsGrid']/div[4]/a[4]", 5);

        // Click on go to last page button
        WebElement goToLastPageButton = driver.findElement(By.xpath("//*[@id='tmsGrid']/div[4]/a[4]"));
        goToLastPageButton.click();

        // Wait till the delete button is visible
        WaitUtils.WaitToBeVisible(driver, "xpath", "//*[@id='tmsGrid']/div[3]/table/tbody/tr[last()]/td[5]/a[2]", 5);

        // Check if material record can be deleted
        WebElement Delete = driver.findElement(By.xpath("//*[@id='tmsGrid']/div[3]/table/tbody/tr[last()]/td[5]/a[2]"));
        Delete.click();

        driver.switchTo().alert().accept();

    }

    public void DeleteTMAssertion(WebDriver driver, String typeCode, String description, String price) {

        driver.navigate().refresh();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Check if material record has been updated
        WebElement updatedTypeCode = driver.findElement(By.xpath("//*[@id='tmsGrid']/div[3]/table/tbody/tr[last()]/td[1]"));
        WebElement updatedDescription = driver.findElement(By.xpath("//*[@id='tmsGrid']/div[3]/table/tbody/tr[last()]/td[1]"));
        WebElement updatedPrice = driver.findElement(By.xpath("//*[@id='tmsGrid']/div[3]/table/tbody/tr[last()]/td[1]"));

        Assert.assertNotEquals(updatedTypeCode.getText(), typeCode, "Material record hasn't been deleted");
        Assert.assertNotEquals(updatedDescription.getText(), description, "Material record hasn't been deleted");
        Assert.assertNotEquals(updatedPrice.getText(), price, "Material record hasn't been deleted");

    }
}
