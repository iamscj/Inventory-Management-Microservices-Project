package com.iamscjoshi.microservices.product;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

	@ServiceConnection
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5");

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setUp() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	static {
		mongoDBContainer.start();
	}

	@Test
	void shouldCreateProduct() {
		String requestBody = """
					{
						"name": "iPhone-15",
						"description": "iPhone-15 smartphone",
						"price": 1000
					}
				""";
		RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("api/product")
				.then()
				.statusCode(201)
				.body("id", Matchers.notNullValue())
				.body("name", Matchers.equalTo("iPhone-15"))
				.body("description", Matchers.equalTo("iPhone-15 smartphone"))
				.body("price", Matchers.equalTo(1000.0F));
	}

	@Test
	void shouldReturnListOfProducts() {
		String requestBody = """
					{
						"name": "iPhone-15",
						"description": "iPhone-15 smartphone",
						"price": 1000
					}
				""";

		RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("api/product")
				.then()
				.statusCode(201);

		RestAssured.given()
				.contentType("application/json")
				.when()
				.get("api/product")
				.then()
				.statusCode(200)
				.body("size()", Matchers.equalTo(1))
				.body("[0].id", Matchers.notNullValue())
				.body("[0].name", Matchers.equalTo("iPhone-15"))
				.body("[0].description", Matchers.equalTo("iPhone-15 smartphone"))
				.body("[0].price", Matchers.equalTo(1000.0F));
	}

}
