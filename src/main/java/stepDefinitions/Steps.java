package stepDefinitions;


import io.cucumber.java.en.*;
import net.bytebuddy.implementation.FieldAccessor;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;


public class Steps extends BaseClass {


    @Given("User Launch Chrome Browser")
    public void user_Launch_Chrome_Browser() throws IOException {

        logger= Logger.getLogger("nopCommerce"); // we added the logger here
        PropertyConfigurator.configure("Log4j.properties");

        //Reading properties:
        configProp= new Properties();
        FileInputStream configPropFile = new FileInputStream("config.properties");
        configProp.load(configPropFile);

        String br = configProp.getProperty("browser"); // this is used to execute our project in a desired browser

        if (br.equals("chrome")){
            System.setProperty("webdriver.chrome.driver",configProp.getProperty("chromePath"));
            driver= new ChromeDriver();
        }

        else if (br.equals("fireFox")){
            System.setProperty("webdriver.gecko.driver",configProp.getProperty("fireFoxPath"));
            driver= new FirefoxDriver();

        }






        logger.info("****************** Lunching Browser ****************************");
        lp=new LoginPage(driver); // this the object of pageObject class , it was created to access all the method created in the pageObject class

    }

    @When("User opens URL {string}")
    public void user_opens_URL(String Url) {
        logger.info("****************** opening Url ****************************");
        driver.get(Url);


    }

    @When("User enters Email as {string} and password as {string}")
    public void user_enters_Email_as_and_password_as(String email, String password) {
        logger.info("****************** Providing login details ****************************");
        lp.setUserName(email);
        lp.setPassword(password);

    }

    @When("Click on Login")
    public void click_on_Login() {
        logger.info("****************** starting login process ****************************");
        lp.clickLogin();

    }

    @Then("Page Title should be {string}")
    public void page_Title_should_be(String title) {


        if(driver.getPageSource().contains("Login was unsuccessful.")){
            driver.close();
            logger.info("****************** Login passed ****************************");
            Assert.assertTrue(false);

        }else {
            logger.info("****************** login failed ****************************");
            Assert.assertEquals(title,driver.getTitle());
        }

    }


    @When("User click on Log out link")
    public void user_click_on_Log_out_link() throws InterruptedException {
        logger.info("****************** click on logOut link ****************************");
        lp.clickLogOut();
        Thread.sleep(3000);

    }


    @And("close browser")
    public void close_browser() {
        logger.info("****************** Closing browser ****************************");
        driver.close();

    }



    //Customer feature step Definitions:

    @Then("User can view Dashboard")
    public void user_can_view_dashboard() {
        addCust = new AddCustomerPage(driver);
        Assert.assertEquals("Dashboard / nopCommerce administration",addCust.getPageTitle());


    }

    @When("User Click on Customers Menu")
    public void user_click_on_customers_menu() throws InterruptedException {
        Thread.sleep(3000);
        addCust.clickOnCustomerMenu();


    }
    @When("Click on customers menu Item")
    public void click_on_cutomers_menu_item() throws InterruptedException {
        Thread.sleep(2000);
        addCust.clickOnCustomerMenuItem();

    }
    @When("Click on Add new button")
    public void click_on_add_new_button() throws InterruptedException {
        addCust.clickOnAddNew();
        Thread.sleep(2000);

    }
    @Then("User can view Add new customer page")
    public void user_can_view_add_new_customer_page() {
        Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());


    }
    @When("User enter customer info")
    public void user_enter_customer_info() throws InterruptedException {
        logger.info("****************** Adding new customer ****************************");
        logger.info("****************** Providing customer details ****************************");

        String email = randomestring() + "@gmail.com";
        addCust.setEmail(email);
        addCust.setPassword("test123");
        // Registered - default
        // The customer cannot be in both 'Guests' and 'Registered' customer roles
        // Add the customer to 'Guests' or 'Registered' customer role
        addCust.setCustomerRoles("Guest");
        Thread.sleep(3000);

        addCust.setManagerOfVendor("Vendor 2");
        addCust.setGender("Male");
        addCust.setName("Billal");
        addCust.setLastName("Yah");
        addCust.setDob("1/1/1900"); // Format: D/MM/YYY
        addCust.setCompanyName("busyQA");
        addCust.adminComment("This is for testing.........");


    }
    @When("Click on Save button")
    public void click_on_save_button() throws InterruptedException {
        logger.info("****************** Saving customer Data ****************************");
        addCust.clickOnSave();
        Thread.sleep(2000);

    }
    @Then("User can view confirmation message\"The new customer has been added successfully.\"")
    public void user_can_view_confirmation_message_the_new_customer_has_been_added_successfully() {

        Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("The new customer has been added successfully"));

    }

    // Steps for searching customer using email ID;

    @When("Enter Customer Email")
    public void enter_customer_email() {
        logger.info("****************** Searching a customer by using email ID ****************************");
       searchCustomer= new SearchCustomerPage(driver);
       searchCustomer.setEmail("victoria_victoria@nopCommerce.com");
    }

    @When("Click on Search button")
    public void click_on_search_button() throws InterruptedException {
       searchCustomer.clickSearch();
       Thread.sleep(3000);
    }

    @Then("User should find email in the Search table")
    public void user_should_find_email_in_the_search_table() {
       boolean status = searchCustomer.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
       Assert.assertEquals(true,status);
    }

    // Steps for searching customer by Using First Name & Last Name

    @When("Enter customer FirstName")
    public void enter_customer_first_name() {
        logger.info("****************** Searching customer by name ****************************");
       searchCustomer= new SearchCustomerPage(driver);
       searchCustomer.setFirstName("Victoria");
    }

    @When("Enter customer LastName")
    public void enter_customer_last_name() {
        searchCustomer.setLastName("Terces");

    }

    @Then("User Should find Name in the Search table")
    public void user_should_find_name_in_the_search_table() {
        boolean status = searchCustomer.searchCustomerByName("Victoria Terces");
        Assert.assertEquals(true,status);
    }









}
