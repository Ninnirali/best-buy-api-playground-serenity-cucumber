package ui.swagger.info;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import ui.swagger.constants.EndPoints;
import ui.swagger.models.ServicesPojo;

import java.util.HashMap;

public class ServicesSteps {

    @Step("Creating Service with name : {0}")
    public ValidatableResponse createService(String name) {
        ServicesPojo servicesPojo = new ServicesPojo();
        servicesPojo.setName(name);
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(servicesPojo)
                .when()
                .post(EndPoints.CREATE_SERVICES)
                .then().log().ifValidationFails();
    }

    @Step("Getting the Services information with ID: {0}")
    public HashMap<String, ?> getServiceInfoByName(int serviceID) {
        HashMap<String, ?> serviceMap = SerenityRest.given().log().all()
                .when()
                .pathParam("serviceID", serviceID)
                .get(EndPoints.GET_SINGLE_SERVICES_BY_ID)
                .then()
                .statusCode(200)
                .extract().path("");
        return serviceMap;
    }

    @Step("Updating Service with name : {0}")
    public ValidatableResponse updateService(int serviceID,
                                             String name) {
        ServicesPojo servicesPojo = new ServicesPojo();
        servicesPojo.setName(name);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .body(servicesPojo)
                .pathParam("serviceID", serviceID)
                .when()
                .patch(EndPoints.UPDATE_SINGLE_SERVICES_BY_ID)
                .then().log().ifValidationFails();
    }

    @Step("Deleting Services with ID {0}")
    public ValidatableResponse deleteService(int serviceID) {
        return SerenityRest.given().log().all()
                .pathParam("serviceID", serviceID)
                .when()
                .delete(EndPoints.DELETE_SINGLE_SERVICES_BY_ID)
                .then().log().ifValidationFails();
    }

    @Step("Getting Services with ID {0}")
    public ValidatableResponse getServiceByID(int serviceID) {
        return SerenityRest.given().log().all()
                .pathParam("serviceID", serviceID)
                .when()
                .get(EndPoints.GET_SINGLE_SERVICES_BY_ID)
                .then().log().ifValidationFails();
    }

    @Step("Getting All Services ")
    public ValidatableResponse getAllService(){
        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_ALL_SERVICES)
                .then().log().ifValidationFails();
    }
}
