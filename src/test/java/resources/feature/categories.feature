@categories
Feature: Testing different requests like CREATE, READ, UPDATE and DELETE on Categories Endpoint of the BestBuy API playground application.

  Background: User is on homepage of categories endpoint of Best Buy API Playground application
    Given User is on homepage of categories endpoint


  Scenario: Check if the Categories data can be accessed by user
    When User sends a GET request to the categories endpoint
    Then User must get back a valid status response code 200 from categories endpoint


  Scenario: Check if user can CREATE a new category on categories endpoint
    When User sends a POST request with a valid payload to the categories endpoint
    Then User must get back a valid status response code 201 from categories endpoint


  Scenario: Check if user can READ a newly created category on categories endpoint
    When User send a GET request to newly created category with product ID
    Then User must get back a valid status response code 201 from categories endpoint
    And User should verify if the category is created with correct details


  Scenario: Check if user can UPDATE a newly created category on categories endpoint
    When User send a PUT request to newly created category with product ID
    Then User must get back a valid status response code 201 from categories endpoint
    And User should verify if the category details are updated with correct details


  Scenario: Check if user can delete a newly created category on categories end point
    When User send a DELETE request to newly created category with product ID
    Then User must get back a valid status response code 201 from categories endpoint
    And User should verify if the category is deleted