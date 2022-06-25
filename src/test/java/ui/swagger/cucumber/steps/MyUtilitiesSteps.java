package ui.swagger.cucumber.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import ui.swagger.info.UtilitiesSteps;

public class MyUtilitiesSteps {

    static ValidatableResponse response;

    @Steps
    UtilitiesSteps utilitiesSteps;


    @Given("^User is on homepage of utilities endpoint$")
    public void userIsOnHomepageOfUtilitiesEndpoint() {
    }

    @When("^User send a GET request to check version of the utilities endpoint$")
    public void userSendAGETRequestToCheckVersionOfTheUtilitiesEndpoint() {
        response = utilitiesSteps.gettingVersion().statusCode(200);
    }

    @Then("^User must get a valid response code (\\d+) from utilities endpoint$")
    public void userMustGetAValidResponseCodeFromUtilitiesEndpoint(int statusCode) {
        response.assertThat().statusCode(statusCode);
    }

    @When("^User send a GET request to check health of the utilities endpoint$")
    public void userSendAGETRequestToCheckHealthOfTheUtilitiesEndpoint() {
        response = utilitiesSteps.gettingHealthCheck().statusCode(200);
    }
}
