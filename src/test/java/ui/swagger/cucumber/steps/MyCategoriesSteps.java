package ui.swagger.cucumber.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;
import ui.swagger.info.CategoriesSteps;
import ui.swagger.utils.TestUtils;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

public class MyCategoriesSteps {

    static String name = "Name" + TestUtils.getRandomValue();
    static String id = "abcd001122" + TestUtils.getRandomValue();
    static String categoryID;
    static ValidatableResponse response;
    
    @Steps
    CategoriesSteps categoriesSteps;
    
    @Given("^User is on homepage of categories endpoint$")
    public void userIsOnHomepageOfCategoriesEndpoint() {
    }

    @When("^User sends a GET request to the categories endpoint$")
    public void userSendsAGETRequestToTheCategoriesEndpoint() {
        response = categoriesSteps.getAllCategory().statusCode(200);
    }

    @Then("^User must get back a valid status response code (\\d+) from categories endpoint$")
    public void userMustGetBackAValidStatusResponseCodeFromCategoriesEndpoint(int statusCode) {
        response.assertThat().statusCode(statusCode);
    }

    @When("^User sends a POST request with a valid payload to the categories endpoint$")
    public void userSendsAPOSTRequestWithAValidPayloadToTheCategoriesEndpoint() {
        response = categoriesSteps.createCategory(name,id);
        response.log().all().statusCode(201);
        categoryID = response.log().all().extract().path("id");
        System.out.println(categoryID);
    }


    @When("^User send a GET request to newly created category with product ID$")
    public void userSendAGETRequestToNewlyCreatedCategoryWithProductID() {
        HashMap<String, ?> categoryMap = categoriesSteps.getCategoryInfoByName(categoryID);
        Assert.assertThat(categoryMap,hasValue(name));
    }

    @And("^User should verify if the category is created with correct details$")
    public void userShouldVerifyIfTheCategoryIsCreatedWithCorrectDetails() {
        HashMap<String, ?> categoryMap = categoriesSteps.getCategoryInfoByName(categoryID);
        Assert.assertThat(categoryMap,hasValue(name));
        Assert.assertThat(categoryMap,hasValue(id));
    }

    @When("^User send a PUT request to newly created category with product ID$")
    public void userSendAPUTRequestToNewlyCreatedCategoryWithProductID() {
        name = name +"_updated";
        categoriesSteps.updatingCategory(categoryID,name,id);
    }

    @And("^User should verify if the category details are updated with correct details$")
    public void userShouldVerifyIfTheCategoryDetailsAreUpdatedWithCorrectDetails() {
        HashMap<String,?>categoriesList = categoriesSteps.getCategoryInfoByName(categoryID);
        Assert.assertThat(categoriesList,hasValue(name));
    }

    @When("^User send a DELETE request to newly created category with product ID$")
    public void userSendADELETERequestToNewlyCreatedCategoryWithProductID() {
        categoriesSteps.deleteCategory(categoryID).statusCode(200);
    }

    @And("^User should verify if the category is deleted$")
    public void userShouldVerifyIfTheCategoryIsDeleted() {
        categoriesSteps.getCategoryByID(categoryID).statusCode(404);
    }
}
