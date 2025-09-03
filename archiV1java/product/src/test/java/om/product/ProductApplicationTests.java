package om.product;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

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
                "pricePerItemUnit":120.80
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
                .body("id", notNullValue())
                .body("name", equalTo("affordable mobile phone"))
                .body("desc", equalTo("technical details"))
                .body("pricePerItemUnit", is(120.80f));
    }


    @Test
    void shouldGetAllProducts() { // GET collection
         var requestSpecification = RestAssured
                .given();
        var response = requestSpecification
                .get(endpoint);
List<Object> list = response.jsonPath().getList("id");
for(Object product: list) {
    System.out.println("product: "+ product);
}
    }

    @Test
    void shouldUpdateSpecificProduct() { // PUT
        String requestBody = """
                 {
                "name":"affordable mobile phone",\s
                "desc":"technical details",\s
                "skuCode":"DIGI1001MPHO",\s
                "pricePerItemUnit":120.80
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
                .body("id", notNullValue())
                .body("name", equalTo("affordable mobile phone"))
                .body("desc", equalTo("technical details"))
                .body("skuCode", equalTo("DIGI1001MPHO"))
                .body("pricePerItemUnit", is(120.80f));
    }

    @Test
    void shouldGetSpecificProduct() { // GET one
        String skucode ="DIGI1001MPHO";
        var response = RestAssured
                .given()
                .queryParam("name","affordable mobile phone")
                .when()
                .get(endpoint+"/"+skucode)
                .then();

        response.log().all()
                .statusCode(200)
                .body("id", notNullValue())
                .body("name", equalTo("affordable mobile phone"))
                .body("desc", equalTo("technical details"))
                .body("skuCode", equalTo("DIGI1001MPHO"))
                .body("pricePerItemUnit", is(120.80f));
    }
}
