package ui.swagger.info;

import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import ui.swagger.testbase.TestBase;
import ui.swagger.utils.TestUtils;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class StoresCRUDTest extends TestBase {

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

    @Steps
    StoreSteps storeSteps;

    @Title("This will create a new store")
    @Test
    public void test001() {
        ValidatableResponse response = storeSteps.createANewStore(name, type, address, address2, city, state, zip, lat, lng, hours, services);
        response.log().all().statusCode(201);
        storeID = response.log().all().extract().path("id");
        System.out.println(storeID);
    }

    @Title("Verify if the Store was added to the application")
    @Test
    public void test002() {
        HashMap<String, ?> storeMap = storeSteps.getStoreInfoByName(storeID);
        Assert.assertThat(storeMap, hasValue(name));
        System.out.println(storeMap);
    }

    @Title("Update the store information")
    @Test
    public void test003() {
        name = name + "_updated";
        storeSteps.updateStoreInfo(storeID, name, type, address);
        HashMap<String, ?> storeList = storeSteps.getStoreInfoByName(storeID);
        Assert.assertThat(storeList, hasValue(name));
        System.out.println(storeList);
    }

    @Title("Delete the product by ID")
    @Test
    public void test004(){
        storeSteps.deleteStore(storeID).statusCode(200);
        storeSteps.getStoreByID(storeID).statusCode(404);

    }
}
