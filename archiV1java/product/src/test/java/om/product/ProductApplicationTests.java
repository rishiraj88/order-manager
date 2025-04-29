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
    void shouldAddProduct() {
        String requestBody = """
                {
                "name":"affordable mobile phone",\s
                "desc":"technical details",\s
                "skuCode":"DIGI1001MPHO",\s
                "pricePerItem":120.80
                }
                """;
        RestAssured.given().contentType("application/json").body(requestBody).when().post("/api/v1/products").then().log().all().statusCode(201).body("id", Matchers.notNullValue()).body("name", Matchers.equalTo("affordable mobile phone")).body("desc", Matchers.equalTo("technical details")).body("pricePerItem", Matchers.is(120.80f));
    }
}
