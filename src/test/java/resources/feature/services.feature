@services
Feature: Testing different requests like CREATE, READ, UPDATE and DELETE on Services Endpoint of the BestBuy API playground application.

  Background: User is on homepage of services endpoint of Best Buy API Playground application
    Given User is on homepage of services endpoint


  Scenario: Check if the Services data can be accessed by user
    When User sends a GET request to the services endpoint
    Then User must get back a valid status response code 200 from services endpoint


  Scenario: Check if user can CREATE a new service on services endpoint
    When User sends a POST request with a valid payload to the services endpoint
    Then User must get back a valid status response code 201 from services endpoint


  Scenario: Check if user can READ a newly created service on services endpoint
    When User send a GET request to newly created service with service ID
    Then User must get back a valid status response code 201 from services endpoint
    And User should verify if the service is created with correct details


  Scenario: Check if user can UPDATE a newly created service on services endpoint
    When User send a PUT request to newly created service with service ID
    Then User must get back a valid status response code 201 from services endpoint
    And User should verify if the services details are updated with correct details


  Scenario: Check if user can delete a newly created service on services end point
    When User send a DELETE request to newly created service with service ID
    Then User must get back a valid status response code 201 from services endpoint
    And User should verify if the service is deleted