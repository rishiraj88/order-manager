package om.product;

import io.restassured.RestAssured;
import om.product.model.dto.ProductReq;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
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
                "name":"middle-ranged mobile phone",\s
                "desc":"simpler details",\s
                "skuCode":"DIGI1001MPH2",\s
                "pricePerItemUnit":280.30
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
                .body("name", equalTo("middle-ranged mobile phone"))
                .body("desc", equalTo("simpler details"))
                .body("pricePerItemUnit", is(280.30f));

    }

    @Test
    void shouldGetAllProducts() { // GET collection
           var requestSpecification = RestAssured
                .given();
        var response = requestSpecification
                .when()
                .get(endpoint)
                .then();

        response.log().body()
                .assertThat().statusCode(200)
                .body("size()",greaterThan(0))
                .body("name",everyItem(notNullValue()))
                .body("desc[0]",equalTo("simpler details"))
        ;

    }

    //@ParameterizedTest(name = "productReq")
    void shouldUpdateSpecificProduct(ProductReq productReq) { // PUT
        //String id,String name, String desc, String skuCode, BigDecimal pricePerItemUnit
        /*String requestBody = """
                {
                "id": "68b8a00057406c46eea73e83",\s
                "name":"middle-ranged mobile phone",\s
                "desc":"simpler details",\s
                "skuCode":"DIGI1001MPH2",\s
                "pricePerItemUnit":310.80\s
                }
                """;*/
        var response = RestAssured
                .given()
                //.contentType("application/json").body(requestBody)
                .contentType("application/json").body(productReq)
                .when()
                .put(endpoint)
                .then();

        response.log().body()
                .assertThat().statusCode(200)
                .body("size()",greaterThan(0))
                .body("skuCode", equalTo("DIGI1001MPH2"))
                .body("pricePerItemUnit", is(310.80f));
    }

    //@Test
    void shouldGetSpecificProduct_success() { // GET one

            String skucode ="DIGI1001MPH2";//DIGI1001MPH2

            var response =RestAssured
                    .given().body(new ProductReq("","","", "DIGI1001MPH2", new BigDecimal("280.30")))
                    //.queryParam("skucode",skucode)
                    .when()
                    //.get(endpoint +"/skucode")
                    .put(endpoint)
                    .then();

           response.log().body()
                   .assertThat().statusCode(200)
                   .body("size()",greaterThan(0))
                   .body("skuCode", equalTo("DIGI1001MPH2"));
                   //.body("pricePerItemUnit", equalTo("280.30"));
    }
    @Test
    void shouldGetSpecificProduct_notFound() { // GET one

        String skucode ="DIGI1001MPH2x";

        var response =RestAssured
                .given()
                .queryParam("skucode",skucode)
                .when()
                .get(endpoint +"/skucode")
                .then();

        response.log().body()
                .assertThat().statusCode(404);
    }

    @Test
    void shouldAddProduct_missingName() { // POST with missing name
        String requestBody = """
                {
                "desc":"simpler details",\s
                "skuCode":"DIGI1001MPH2",\s
                "pricePerItemUnit":280.30
                }
                """;
        var response = RestAssured
                .given()
                .contentType("application/json").body(requestBody)
                .when()
                .post(endpoint)
                .then();

        response.log().all()
                .statusCode(400);
    }

    @Test
    void shouldAddProduct_invalidPrice() { // POST with invalid price
        String requestBody = """
                {
                "name":"mobile phone",\s
                "desc":"simpler details",\s
                "skuCode":"DIGI1001MPH2",\s
                "pricePerItemUnit":-50.00
                }
                """;
        var response = RestAssured
                .given()
                .contentType("application/json").body(requestBody)
                .when()
                .post(endpoint)
                .then();

        response.log().all()
                .statusCode(400);
    }

    @Test
    void shouldAddProduct_emptyRequest() { // POST with empty request
        String requestBody = "{}";
        var response = RestAssured
                .given()
                .contentType("application/json").body(requestBody)
                .when()
                .post(endpoint)
                .then();

        response.log().all()
                .statusCode(400);
    }

    @Test
    void shouldAddProduct_invalidContentType() { // POST with invalid content type
        String requestBody = """
                {
                "name":"mobile phone",\s
                "desc":"simpler details",\s
                "skuCode":"DIGI1001MPH2",\s
                "pricePerItemUnit":280.30
                }
                """;
        var response = RestAssured
                .given()
                .contentType("application/xml").body(requestBody)
                .when()
                .post(endpoint)
                .then();

        response.log().all()
                .statusCode(415);
    }

    @Test
    void shouldUpdateProduct_notFound() { // PUT with non-existent product
        String requestBody = """
                {
                "id":"000000000000000000000000",\s
                "name":"non-existent product",\s
                "desc":"details",\s
                "skuCode":"NONEXISTENT",\s
                "pricePerItemUnit":100.00
                }
                """;
        var response = RestAssured
                .given()
                .contentType("application/json").body(requestBody)
                .when()
                .put(endpoint)
                .then();

        response.log().all()
                .statusCode(404);
    }

    @Test
    void shouldGetProduct_invalidSkucode() { // GET with invalid skucode
        String skucode = "";

        var response = RestAssured
                .given()
                .queryParam("skucode", skucode)
                .when()
                .get(endpoint + "/skucode")
                .then();

        response.log().body()
                .assertThat().statusCode(400);
    }

    @Test
    void shouldAddProduct_nullPrice() { // POST with null price
        String requestBody = """
                {
                "name":"mobile phone",\s
                "desc":"simpler details",\s
                "skuCode":"DIGI1001MPH2",\s
                "pricePerItemUnit":null
                }
                """;
        var response = RestAssured
                .given()
                .contentType("application/json").body(requestBody)
                .when()
                .post(endpoint)
                .then();

        response.log().all()
                .statusCode(400);
    }

    @Test
    void shouldAddProduct_duplicateSkuCode() { // POST with duplicate skuCode
        // First, add a product
        String requestBody = """
                {
                "name":"mobile phone",\s
                "desc":"simpler details",\s
                "skuCode":"DUPLICATE001",\s
                "pricePerItemUnit":280.30
                }
                """;
        RestAssured
                .given()
                .contentType("application/json").body(requestBody)
                .when()
                .post(endpoint)
                .then()
                .statusCode(201);

        // Try to add another product with same skuCode
        var response = RestAssured
                .given()
                .contentType("application/json").body(requestBody)
                .when()
                .post(endpoint)
                .then();

        response.log().all()
                .statusCode(409);
    }


}
