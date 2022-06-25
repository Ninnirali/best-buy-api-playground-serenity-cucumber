@products
Feature: Testing different requests like CREATE, READ, UPDATE and DELETE on Products Endpoint of the BestBuy API playground application.

  Background: User is on homepage of products endpoint of Best Buy API Playground application
    Given User is on homepage of products endpoint


  Scenario: Check if the Products data can be accessed by user
    When User sends a GET request to the products endpoint
    Then User must get back a valid status response code 200 from products endpoint


  Scenario: Check if user can CREATE a new category on products endpoint
    When User sends a POST request with a valid payload to the products endpoint
    Then User must get back a valid status response code 201 from products endpoint


  Scenario: Check if user can READ a newly created category on products endpoint
    When User send a GET request to newly created product with product ID
    Then User must get back a valid status response code 201 from products endpoint
    And User should verify if the product is created with correct details


  Scenario: Check if user can UPDATE a newly created product on products endpoint
    When User send a PUT request to newly created product with product ID
    Then User must get back a valid status response code 201 from products endpoint
    And User should verify if the products details are updated with correct details


  Scenario: Check if user can delete a newly created product on products end point
    When User send a DELETE request to newly created product with product ID
    Then User must get back a valid status response code 201 from products endpoint
    And User should verify if the product is deleted