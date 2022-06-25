package ui.swagger.cucumber.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;
import ui.swagger.info.StoreSteps;
import ui.swagger.utils.TestUtils;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

public class MyStoresSteps {

    static String name = "Abc" + TestUtils.getRandomValue();
    static String type = "Type" + TestUtils.getRandomValue();
    static String address = "123 Happy lane";
    static String address2 = "Happy Place";
    static String city = "city";
    static String state = "State";
    static String zip = "12345";
    static double lat = 11.11;
    static double lng = 22.22;
    static String hours = "10-5";
    static HashMap<Object, Object> services = new HashMap<>();

    static int storeID;
    static ValidatableResponse response;

    @Steps
    StoreSteps storeSteps;

    @Given("^User is on homepage of stores endpoint$")
    public void userIsOnHomepageOfStoresEndpoint() {
    }

    @When("^User sends a GET request to the stores endpoint$")
    public void userSendsAGETRequestToTheStoresEndpoint() {
        response = storeSteps.getAllStores().statusCode(200);
    }

    @Then("^User must get back a valid status response code (\\d+) from stores endpoint$")
    public void userMustGetBackAValidStatusResponseCodeFromStoresEndpoint(int statusCode) {
        response.statusCode(statusCode);
        response.assertThat().statusCode(statusCode);
    }

    @When("^User sends a POST request with a valid payload to the stores endpoint$")
    public void userSendsAPOSTRequestWithAValidPayloadToTheStoresEndpoint() {
        HashMap<Object,Object> serviceData = new HashMap<>();
        response = storeSteps.createANewStore(name,type,address,address2,city,state,zip,lat,lng,hours,serviceData);
        response.log().all().statusCode(201);
        storeID = response.log().all().extract().path("id");
        System.out.println(storeID);
    }

    @When("^User send a GET request to newly created store with store ID$")
    public void userSendAGETRequestToNewlyCreatedStoreWithStoreID() {
        HashMap<String, ?> storeMap = storeSteps.getStoreInfoByName(storeID);
    }

    @And("^User should verify if the store is created with correct details$")
    public void userShouldVerifyIfTheStoreIsCreatedWithCorrectDetails() {
        HashMap<String, ?> storeMap = storeSteps.getStoreInfoByName(storeID);
        Assert.assertThat(storeMap, hasValue(name));
        Assert.assertThat(storeMap, hasValue(type));
        Assert.assertThat(storeMap, hasValue(city));
    }

    @When("^User send a PUT request to newly created store with store ID$")
    public void userSendAPUTRequestToNewlyCreatedStoreWithStoreID() {
        name = name + "_updated";
        HashMap<Object, Object> servicesData = new HashMap<>();
        storeSteps.updateStoreInfo(storeID,name,type,address);
    }

    @And("^User should verify if the stores details are updated with correct details$")
    public void userShouldVerifyIfTheStoresDetailsAreUpdatedWithCorrectDetails() {
        HashMap<String, ?> productList = storeSteps.getStoreInfoByName(storeID);
        Assert.assertThat(productList, hasValue(name));
    }

    @When("^User send a DELETE request to newly created store with store ID$")
    public void userSendADELETERequestToNewlyCreatedStoreWithStoreID() {
        storeSteps.deleteStore(storeID).statusCode(200);
    }

    @And("^User should verify if the store is deleted$")
    public void userShouldVerifyIfTheStoreIsDeleted() {
        storeSteps.getStoreByID(storeID).statusCode(404);
    }
}
