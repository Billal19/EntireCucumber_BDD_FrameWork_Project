package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddCustomerPage {

    public WebDriver ldriver;

    public AddCustomerPage(WebDriver rdriver){
        ldriver=rdriver;
        PageFactory.initElements(ldriver,this);
    }

    // We will first have to define all the locators:

    By lnkCustomers_menu= By.xpath("/html/body/div[3]/div[2]/div/ul/li[4]/a");
    By lnkCustomers_menuItem = By.xpath("//li[@class='treeview menu-open']//li[1]//a[1]");
    By AddNewCustButton = By.xpath("//a[@class='btn bg-blue']");
    By TxtEmail = By.xpath("//input[@id='Email']");
    By TxtPassword = By.xpath("//input[@id='Password']");
    By TxtCustomerName = By.xpath("//input[@id='FirstName']");
    By TxtCustomerLastName =By.xpath("//input[@id='LastName']");
    By rdMaleGender=By.id("Gender_Male");
    By rdFeMaleGender=By.id("Gender_Female");
    By TxtCustomerDOB = By.xpath("//input[@id='DateOfBirth']");
    By TxtCompanyName = By.xpath("//input[@id='Company']");
    By TxtTaxExempt = By.xpath("//input[@id='IsTaxExempt']");
    By TxtNewsLetter = By.xpath(" //div[9]//div[2]//div[1]//div[1]//div[1]");
    By TxtCustomerRole = By.xpath("//div[10]//div[2]//div[1]//div[1]//div[1]");

    By lstitemAdministrators=By.xpath("//li[contains(text(),'Administrators')]");
    By lstitemRegistered=By.xpath("//li[contains(text(),'Registered')]");
    By lstitemGuests=By.xpath("//li[contains(text(),'Guests')]");
    By lstitemVendors=By.xpath("//li[contains(text(),'Vendors')]");

    By TxtManagerOfVendor = By.xpath("//select[@id='VendorId']");
    By TxtAdminContent = By.xpath("//textarea[@id='AdminComment']");
    By TxtActive = By.xpath("//div[12]//div[2]");
    By SaveButton=By.xpath("//button[@name='save']");

    // After capturing all the elements , we will implement all the actions methods :

    public String getPageTitle()
    {
        return ldriver.getTitle();
    }

    public void clickOnCustomerMenu(){
        ldriver.findElement(lnkCustomers_menu).click();
    }

    public void clickOnCustomerMenuItem(){
        ldriver.findElement(lnkCustomers_menuItem).click();
    }

    public void clickOnAddNew(){
        ldriver.findElement(AddNewCustButton).click();
    }

    public void setEmail(String email){
        ldriver.findElement(TxtEmail).sendKeys(email);
    }

    public void setPassword(String password){
        ldriver.findElement(TxtPassword).sendKeys(password);
    }

    public void setName (String name){
        ldriver.findElement(TxtCustomerName).sendKeys(name);
    }

    public void setLastName (String LastName){
        ldriver.findElement(TxtCustomerLastName).sendKeys(LastName);
    }


    public void setGender(String gender)
    {
        if(gender.equals("Male"))
        {
            ldriver.findElement(rdMaleGender).click();
        }
        else if(gender.equals("Female"))
        {
            ldriver.findElement(rdFeMaleGender).click();
        }
        else
        {
            ldriver.findElement(rdMaleGender).click();//Default
        }

    }

    public void setDob(String dob) {
        ldriver.findElement(TxtCustomerDOB).sendKeys(dob);
    }

    public void setCompanyName(String comName)
    {
        ldriver.findElement(TxtCompanyName).sendKeys(comName);
    }


    public void setCustomerRoles(String role) throws InterruptedException {
        if(!role.equals("Vendors")) //If role is vendors should not delete Register as per req.
        {
            ldriver.findElement(By.xpath("//*[@id=\"SelectedCustomerRoleIds_taglist\"]/li/span[2]")).click();
        }

        ldriver.findElement(TxtCustomerRole).click();

        WebElement listitem;

        Thread.sleep(3000);

        if(role.equals("Administrators"))
        {
            listitem=ldriver.findElement(lstitemAdministrators);
        }
        else if(role.equals("Guests"))
        {
            listitem=ldriver.findElement(lstitemGuests);
        }
        else if(role.equals("Registered"))
        {
            listitem=ldriver.findElement(lstitemRegistered);
        }
        else if(role.equals("Vendors"))
        {
            listitem=ldriver.findElement(lstitemVendors);
        }
        else
        {
            listitem=ldriver.findElement(lstitemGuests);
        }

        //listitem.click();
        //Thread.sleep(3000);

        JavascriptExecutor js = (JavascriptExecutor)ldriver;
        js.executeScript("arguments[0].click();", listitem);

    }

    public void setManagerOfVendor(String value)
    {
        Select drp=new Select(ldriver.findElement(TxtManagerOfVendor));
        drp.selectByVisibleText(value);
    }

    public void adminComment(String content){
        ldriver.findElement(TxtAdminContent).sendKeys(content);
    }

    public void clickOnSave()
    {
        ldriver.findElement(SaveButton).click();
    }









}
