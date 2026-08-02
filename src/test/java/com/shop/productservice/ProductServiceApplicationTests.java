package com.shop.productservice;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.shop.productservice.domain.Product;
import com.shop.productservice.domain.ProductRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class ProductServiceApplicationTests {
     
	@Autowired
	private WebTestClient webTestClient;
	
	@Autowired
	ProductRepository productRepository;
	
	@BeforeEach
	void setUp() {
		productRepository.deleteAll();
	}
	
	@Test
	void whenGetWithIdThenProductReturned() {
		var id = "p-1";
		var productToCreate = new Product(id, "Product name", new BigDecimal("19.99"));
		Product expectedProduct = webTestClient.post()
				.uri("/products")
				.bodyValue(productToCreate)
				.exchange()
				.expectStatus().isCreated()
				.expectBody(Product.class).value(product -> assertThat(product).isNotNull())
                .returnResult().getResponseBody();
		
		webTestClient.get()
				.uri("/products/" + id)
				.exchange()
				.expectStatus().is2xxSuccessful()
				.expectBody(Product.class).value(actualProduct -> {
                    assertThat(actualProduct).isNotNull();
                    assertThat(actualProduct.id()).isEqualTo(expectedProduct.id());
                });
	}
	
	@Test
	void whenPostThenProductCreated() {
		var expectedProduct = new Product("p-1", "Product name", new BigDecimal("19.99"));
		
		webTestClient.post()
				.uri("/products")
				.bodyValue(expectedProduct)
				.exchange()
				.expectStatus().isCreated()
				.expectBody(Product.class).value(actualProduct -> {
					assertThat(actualProduct).isNotNull();
					assertThat(actualProduct.id()).isEqualTo(expectedProduct.id());
				});
	}
	
	@Test
	void whenPutWithNewPriceThenProductUpdated() {
		var id = "p-1"; 
		var productToCreate = new Product(id, "Product name", new BigDecimal("19.99"));
		Product createdProduct = webTestClient.post()
				.uri("/products")
				.bodyValue(productToCreate)
				.exchange()
				.expectStatus().isCreated()
				.expectBody(Product.class).value(product -> assertThat(product).isNotNull())
				.returnResult().getResponseBody();
		
		var productWNewPrice = new Product(createdProduct.id(), createdProduct.name(), new BigDecimal("20.00"));
		
		webTestClient.put()
				.uri("/products/" + id)
				.bodyValue(productWNewPrice)
				.exchange()
				.expectStatus().isOk()
				.expectBody(Product.class).value(actualProduct -> {
					assertThat(actualProduct).isNotNull();
					assertThat(actualProduct.price()).isEqualTo(productWNewPrice.price());
				});
	}
	
	@Test
	void whenDeleteThenProductDeleted() {
		var id = "p-1"; 
		var productToCreate = new Product(id, "Product name", new BigDecimal("19.99"));
		webTestClient.post()
				.uri("/products")
				.bodyValue(productToCreate)
				.exchange()
				.expectStatus().isCreated()
				.expectBody(Product.class).value(product -> assertThat(product).isNotNull())
				.returnResult().getResponseBody();
		
		webTestClient.delete()
			.uri("/products/" + id)
			.exchange()
			.expectStatus().isNoContent();
		
		webTestClient.get()
				.uri("/products/" + id) 
				.exchange()
				.expectStatus().isNotFound()
				.expectBody(String.class).value(errorMessage -> 
						assertThat(errorMessage)
							.isEqualTo("The product with id " + id + " was not found."));
	}
}
