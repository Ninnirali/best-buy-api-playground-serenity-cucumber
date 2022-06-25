package ui.swagger.cucumber.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;
import ui.swagger.info.ProductSteps;
import ui.swagger.utils.TestUtils;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

public class MyProductsSteps {

    static String name = "ABCD" + TestUtils.getRandomValue();
    static String type = "TYPE" + TestUtils.getRandomValue();
    static double price = 2;
    static Integer shipping = 22;
    static String upc = "01122";
    static String description = "Description";
    static String manufacturer = "Manufacturer";
    static String model = "Model001122";
    static String url = "https//www.url.com";
    static String image = "www.images.jpeg";
    static int productID;
    static ValidatableResponse response;

    @Steps
    ProductSteps productSteps;


    @Given("^User is on homepage of products endpoint$")
    public void userIsOnHomepageOfProductsEndpoint() {

    }

    @When("^User sends a GET request to the products endpoint$")
    public void userSendsAGETRequestToTheProductsEndpoint() {
        response = productSteps.getAllProduct();
        response.statusCode(200);

    }

    @Then("^User must get back a valid status response code (\\d+) from products endpoint$")
    public void userMustGetBackAValidStatusResponseCodeFromProductsEndpoint(int statusCode) {
        response.statusCode(statusCode);
        response.assertThat().statusCode(statusCode);
    }

    @When("^User sends a POST request with a valid payload to the products endpoint$")
    public void userSendsAPOSTRequestWithAValidPayloadToTheProductsEndpoint() {
       response = productSteps.createProduct(name,type,price,shipping,upc,description,manufacturer,model,url,image);
       response.log().all().statusCode(201);
       productID = response.log().all().extract().path("id");
        System.out.println(productID);

    }

    @When("^User send a GET request to newly created product with product ID$")
    public void userSendAGETRequestToNewlyCreatedProductWithProductID() {
        HashMap<String,?> productMap = productSteps.getProductInfoByName(productID);
        Assert.assertThat(productMap,hasValue(name));
        System.out.println(productMap);
    }

    @And("^User should verify if the product is created with correct details$")
    public void userShouldVerifyIfTheProductIsCreatedWithCorrectDetails() {
        HashMap<String,?> productMap = productSteps.getProductInfoByName(productID);
        Assert.assertThat(productMap,hasValue(name));
        Assert.assertThat(productMap,hasValue(type));
        System.out.println(productMap);
    }

    @When("^User send a PUT request to newly created product with product ID$")
    public void userSendAPUTRequestToNewlyCreatedProductWithProductID() {
        name = name + "_update";
        productSteps.updatingProduct(productID,name,type,price,shipping,upc,description,manufacturer,model,url,image);

    }

    @And("^User should verify if the products details are updated with correct details$")
    public void userShouldVerifyIfTheProductsDetailsAreUpdatedWithCorrectDetails() {
        HashMap<String,?>productMap = productSteps.getProductInfoByName(productID);
        Assert.assertThat(productMap,hasValue(name));
        System.out.println(productMap);
    }

    @When("^User send a DELETE request to newly created product with product ID$")
    public void userSendADELETERequestToNewlyCreatedProductWithProductID() {
        productSteps.deleteProduct(productID).statusCode(200);
    }

    @And("^User should verify if the product is deleted$")
    public void userShouldVerifyIfTheProductIsDeleted() {
        productSteps.getProductByID(productID).statusCode(404);
    }
}
