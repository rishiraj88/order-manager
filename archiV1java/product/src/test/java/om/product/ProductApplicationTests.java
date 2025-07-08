package om.product;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ProductApplicationTests {
    @ServiceConnection
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5");
    static String endpoint = "/api/v1/products";

    static {
        mongoDBContainer.start();
    }

    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    void shouldAddProduct() { // POST
        String requestBody = """
                {
                "name":"affordable mobile phone",\s
                "desc":"technical details",\s
                "skuCode":"DIGI1001MPHO",\s
                "pricePerItem":120.80
                }
                """;
        var response = RestAssured
                .given()
                .contentType("application/json").body(requestBody)
                .when()
                .post(endpoint)
                .then();

        response.log().all()
                .statusCode(201)
                .body("id", Matchers.notNullValue())
                .body("name", Matchers.equalTo("affordable mobile phone"))
                .body("desc", Matchers.equalTo("technical details"))
                .body("pricePerItem", Matchers.is(120.80f));
    }


    @Test
    void shouldGetAllProducts() { // GET collection
        var response = RestAssured
                .given()
                .when()
                .get(endpoint)
                .then();

        response.log().all()
                .statusCode(200)
                .body("id", Matchers.notNullValue())
                .body("name", Matchers.equalTo("affordable mobile phone"))
                .body("desc", Matchers.equalTo("technical details"))
                .body("skuCode", Matchers.equalTo("DIGI1001MPHO"))
                .body("pricePerItem", Matchers.is(120.80f));
    }

    @Test
    void shouldUpdateSpecificProduct() { // PUT
        String requestBody = """
                 {
                "name":"affordable mobile phone",\s
                "desc":"technical details",\s
                "skuCode":"DIGI1001MPHO",\s
                "pricePerItem":120.80
                }
                """;
        var response = RestAssured
                .given()
                .body(requestBody)
                .when()
                .put(endpoint)
                .then();

        response.log().all()
                .statusCode(200)
                .body("id", Matchers.notNullValue())
                .body("name", Matchers.equalTo("affordable mobile phone"))
                .body("desc", Matchers.equalTo("technical details"))
                .body("skuCode", Matchers.equalTo("DIGI1001MPHO"))
                .body("pricePerItem", Matchers.is(120.80f));
    }

    @Test
    void shouldGetSpecificProduct() { // GET one
        var response = RestAssured
                .given()
                .queryParam("name","affordable mobile phone")
                .when()
                .get(endpoint)
                .then();

        response.log().all()
                .statusCode(200)
                .body("id", Matchers.notNullValue())
                .body("name", Matchers.equalTo("affordable mobile phone"))
                .body("desc", Matchers.equalTo("technical details"))
                .body("skuCode", Matchers.equalTo("DIGI1001MPHO"))
                .body("pricePerItem", Matchers.is(120.80f));
    }
}
