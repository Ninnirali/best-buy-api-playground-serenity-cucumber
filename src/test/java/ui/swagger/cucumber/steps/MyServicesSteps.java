package ui.swagger.cucumber.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;
import ui.swagger.info.ServicesSteps;
import ui.swagger.utils.TestUtils;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

public class MyServicesSteps {

    static String name = "NAME" + TestUtils.getRandomValue();
    static int serviceID;
    static ValidatableResponse response;


    @Steps
    ServicesSteps servicesSteps;

    @Given("^User is on homepage of services endpoint$")
    public void userIsOnHomepageOfServicesEndpoint() {

    }

    @When("^User sends a GET request to the services endpoint$")
    public void userSendsAGETRequestToTheServicesEndpoint() {
        response = servicesSteps.getAllService().statusCode(200);
    }

    @Then("^User must get back a valid status response code (\\d+) from services endpoint$")
    public void userMustGetBackAValidStatusResponseCodeFromServicesEndpoint(int statusCode) {
        response.assertThat().statusCode(statusCode);
    }

    @When("^User sends a POST request with a valid payload to the services endpoint$")
    public void userSendsAPOSTRequestWithAValidPayloadToTheServicesEndpoint() {
        response = servicesSteps.createService(name);
        response.log().all().statusCode(201);
        serviceID = response.log().all().extract().path("id");
        System.out.println(serviceID);
    }

    @When("^User send a GET request to newly created service with service ID$")
    public void userSendAGETRequestToNewlyCreatedServiceWithServiceID() {
        HashMap<String, ?> storeMap = servicesSteps.getServiceInfoByName(serviceID);
        Assert.assertThat(storeMap, hasValue(name));
    }

    @And("^User should verify if the service is created with correct details$")
    public void userShouldVerifyIfTheServiceIsCreatedWithCorrectDetails() {
        HashMap<String, ?> storeMap = servicesSteps.getServiceInfoByName(serviceID);
        Assert.assertThat(storeMap, hasValue(name));
    }

    @When("^User send a PUT request to newly created service with service ID$")
    public void userSendAPUTRequestToNewlyCreatedServiceWithServiceID() {
        name = name + "_updated";
        HashMap<Object, Object> servicesData = new HashMap<>();
        servicesSteps.updateService(serviceID, name);
    }

    @And("^User should verify if the services details are updated with correct details$")
    public void userShouldVerifyIfTheServicesDetailsAreUpdatedWithCorrectDetails() {
        HashMap<String, ?> productList = servicesSteps.getServiceInfoByName(serviceID);
        Assert.assertThat(productList, hasValue(name));
        System.out.println(productList);
    }

    @When("^User send a DELETE request to newly created service with service ID$")
    public void userSendADELETERequestToNewlyCreatedServiceWithServiceID() {
        servicesSteps.deleteService(serviceID).statusCode(200);
    }

    @And("^User should verify if the service is deleted$")
    public void userShouldVerifyIfTheServiceIsDeleted() {
        servicesSteps.getServiceByID(serviceID).statusCode(404);
    }
}
