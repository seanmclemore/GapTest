package pages;

import BaseUtil.BaseUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.Iterator;
import java.util.List;

public class CreateEmployeePage extends BaseUtil {

    public CreateEmployeePage(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(how = How.CSS, using = "#employee_first_name")
    public WebElement employeeFirstName;

    @FindBy(how = How.CSS, using = "#employee_last_name")
    public WebElement employeeLastName;

    @FindBy(how = How.CSS, using = "#employee_email")
    public WebElement employeeEmail;

    @FindBy(how = How.CSS, using = "#employee_identification")
    public WebElement employeeIdentification;

    @FindBy(how = How.CSS, using = "#employee_leader_name")
    public WebElement employeeLeadersName;

    @FindBy(how = How.CSS, using = "#employee_start_working_on_1i")
    public WebElement employeeStartYear;

    @FindBy(how = How.CSS, using = "#employee_start_working_on_2i")
    public WebElement employeeStartMonth;

    @FindBy(how = How.CSS, using = "#employee_start_working_on_3i")
    public WebElement employeeStartDay;

    @FindBy(how = How.NAME, using = "commit")
    public WebElement createBtn;

    @FindBy(how = How.CSS, using = "#notice")
    public WebElement successfullyCreatedTxt;

    @FindBy(how = How.CSS, using = "a[href*='/employees']")
    public WebElement backLink;

    @FindBy(how = How.XPATH, using = "//*[@id=\"content\"]/table/tbody")
    public WebElement tableBody;

    public void createEmployee(String firstName, String lastName, String email, String employeeId, String employeeLeaderName, String startMonth, String startDay, String StartYear) {
        waitForElementToBePresent(createBtn);
        employeeFirstName.sendKeys(firstName);
        employeeLastName.sendKeys(lastName);
        employeeEmail.sendKeys(email);
        employeeIdentification.sendKeys(employeeId);
        employeeLeadersName.sendKeys(employeeLeaderName);
        employeeStartYear.sendKeys(StartYear);
        employeeStartMonth.sendKeys(startMonth);
        employeeStartDay.sendKeys(startDay);
        createBtn.click();
    }

    public void goToEmployeeList() {
        waitForElementToBePresent(successfullyCreatedTxt);
        if (successfullyCreatedTxt.isDisplayed() && backLink.isDisplayed()) {
            backLink.click();
        }
        HomePage homePage = new HomePage();
        waitForElementToBePresent(homePage.EmployeeLogo);
    }

    public void removeEmployeeInfoFromTable(String firstName, String lastName, String startMonth, String startDay, String StartYear) {
        int rowCount = driver.findElements(By.xpath("//*[@id='content']/table")).size();
        String formattedDate = startMonth + "/" + startDay + "/" + StartYear;
        for (int i = 1; i <= rowCount; i++) {
            String rowData = driver.findElement(By.xpath("//*[@id='content']/table/tbody/tr[2]")).getText();
            System.out.println(rowData);
            if (rowData.equalsIgnoreCase(firstName)) {

                for (int j = 1; i <= rowCount; i++) {
                    String sCellValue2 = driver.findElement(By.xpath("//*[@id='content']/table/tbody/tr")).getText();
                    if (sCellValue2.equalsIgnoreCase(lastName)) {

                        for (int k = 1; i <= rowCount; i++) {
                            String sCellValue3 = driver.findElement(By.xpath("//*[@id='content']/table/tbody/tr")).getText();
                            if (sCellValue3.equalsIgnoreCase(formattedDate)) {
                                driver.findElement(By.xpath("//*[@id='content']/table/tbody/tr[" + i + "]/td[8]")).click();
                            }
                        }
                    }
                }
            }
        }
    }
}
