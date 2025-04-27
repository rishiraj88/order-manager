package om.product

import io.restassured.RestAssured
import org.hamcrest.Matchers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Import
import org.springframework.http.HttpStatus
import org.testcontainers.containers.MongoDBContainer

@Import(TestcontainersConfiguration::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductApplicationTests() {
    @LocalServerPort
    lateinit var applicationPort: Integer
    lateinit var mongoDBContainer: MongoDBContainer
    init {@ServiceConnection
        mongoDBContainer = MongoDBContainer("mongo:7.0.5")
        mongoDBContainer.start()
    }

    @Test
    fun shouldAddProduct() {
        var requestBody = """
            {
            "name": "product one",
            "desc": "description one",
            "skuCode":"STAT0001",
            "rate":1.23
            }
        """.trimIndent()
        RestAssured.given()
            .contentType("application/json")
            .body(requestBody)
            .`when`()
            .post("/api/v2/products")
            .then()
            .statusCode(HttpStatus.CREATED.value())
        //testing the values of response
            .body("id", Matchers.notNullValue())
            .body("name",Matchers.equalTo("product one"))
            .body("desc",Matchers.equalTo("description one"))
            .body("skuCode",Matchers.equalTo("STAT0001"))


    }

    @BeforeEach
    fun setup() {
        RestAssured.baseURI = "http://localhost"
        RestAssured.port = applicationPort as Int
    }
}
