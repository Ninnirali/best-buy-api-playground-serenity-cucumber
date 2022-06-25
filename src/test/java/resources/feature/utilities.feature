@utilities
Feature: Testing different requests on utilities Endpoint of the BestBuy API playground application.

  Background: User is on homepage of utilities endpoint of Best Buy API Playground application
    Given User is on homepage of utilities endpoint


  Scenario: Check the version of utilities endpoint
    When User send a GET request to check version of the utilities endpoint
    Then User must get a valid response code 200 from utilities endpoint

  Scenario: User does the health check of utilities endpoint
    When User send a GET request to check health of the utilities endpoint
    Then User must get a valid response code 200 from utilities endpoint