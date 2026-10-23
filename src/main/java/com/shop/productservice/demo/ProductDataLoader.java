package com.shop.productservice.demo;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.shop.productservice.domain.Product;
import com.shop.productservice.domain.ProductRepository;

@Component
@Profile("testdata")
public class ProductDataLoader {
	private final ProductRepository productRepository;

	public ProductDataLoader(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void loadProductTestData() {
		productRepository.deleteAll();
		var p1 = Product.of("Product name", new BigDecimal("19.99"));
		var p2 = Product.of("Product name", new BigDecimal("19.99"));
		productRepository.saveAll(List.of(p1, p2));
	}
}
