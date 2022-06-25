package ui.swagger.testbase;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import ui.swagger.constants.Path;
import ui.swagger.utils.PropertyReader;

public class TestBase {

    public static PropertyReader propertyReader;

    @BeforeClass
    public static void inIt(){
        propertyReader = PropertyReader.getInstance();
        RestAssured.baseURI = propertyReader.getProperty("baseUrl");
        RestAssured.port = Integer.parseInt(propertyReader.getProperty("port"));
        //RestAssured.basePath = Path.PRODUCTS;

    }
}
