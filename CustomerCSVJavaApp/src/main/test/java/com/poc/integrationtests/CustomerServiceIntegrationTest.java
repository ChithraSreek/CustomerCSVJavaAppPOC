package com.poc.integrationtests;

import com.jayway.restassured.http.ContentType;
import com.poc.Application;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;

import java.io.FileNotFoundException;
import java.io.FileReader;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CustomerServiceIntegrationTest {

    private static final String URL = "/v1/customers/addCustomer";
    private static final String GET_URL = "/v1/customers/getAllCustomers";
    private static final String TEST_DATA_FILE_PATH = "/Users/chithra/IdeaProjects/CustomerCSVJavaAppPOC/CustomerCSVJavaApp/src/main/test/resources/payload/";
    private static final String REQUEST_FILE = "Customer.json";

    @LocalServerPort
    private int port;

    @Test
    void shouldPostNewCustomer() throws FileNotFoundException {
        String response = given().port(port)
                .request()
                .contentType(ContentType.JSON)
                .body(new FileReader(TEST_DATA_FILE_PATH + REQUEST_FILE))
                .when().post(URL)
                .then()
                .assertThat().statusCode(202).extract().body().asString();

        assertEquals("", response);
    }

    @Test
    void getAllCustomers() {
        final String response = given().port(port)
                .request()
                .when()
                .get(GET_URL)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .body()
                .asString();
        assertEquals("[]", response);
    }
}
