Feature: Customers

  Background: Below are the common steps for each scenario

    Given User Launch Chrome Browser
    When User opens URL "https://admin-demo.nopcommerce.com/login"
    And User enters Email as "admin@yourstore.com" and password as "admin"
    And Click on Login
    Then User can view Dashboard



@sanity
  Scenario: Add a new Customer

    When User Click on Customers Menu
    And Click on customers menu Item
    And Click on Add new button
    Then User can view Add new customer page
    When User enter customer info
    And Click on Save button
    Then User can view confirmation message"The new customer has been added successfully."
    And close browser
@regression
  Scenario: Search Customer by EmailID

    When User Click on Customers Menu
    And Click on customers menu Item
    And Enter Customer Email
    When Click on Search button
    Then User should find email in the Search table
    And close browser

@regression
  Scenario: Search Customer by Name

    When User Click on Customers Menu
    And Click on customers menu Item
    And Enter customer FirstName
    And Enter customer LastName
    When Click on Search button
    Then User Should find Name in the Search table
    And close browser



