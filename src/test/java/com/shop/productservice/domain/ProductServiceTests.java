package com.shop.productservice.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTests {
	@Mock
	private ProductRepository productRepository;
	
	@InjectMocks
	private ProductService productService;
	
	@Test
	void viewProducts_returnsAllProducts() {
		var expectedProducts = List.of(
				Product.of("Product name", new BigDecimal("19.99")),
				Product.of("Product name", new BigDecimal("19.99")));
		when(productRepository.findAll()).thenReturn(expectedProducts);
		
		var actualProducts = productService.viewProducts();

		assertThat(actualProducts).containsExactlyInAnyOrderElementsOf(expectedProducts);
	}
	
	@Test
	void whenProductToRetrieveDoesNotExistThenThrows() {
		var id = 1L;
		when(productRepository.findById(id)).thenReturn(Optional.empty());
		assertThatThrownBy(() -> productService.getProduct(id))
			.isInstanceOf(ProductNotFoundException.class)
			.hasMessage("The product with id " + id + " was not found.");
	}	
}
