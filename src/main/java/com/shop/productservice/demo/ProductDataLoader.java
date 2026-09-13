package com.shop.productservice.demo;

import java.math.BigDecimal;

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
		var p1 = new Product("p-1", "Product name", new BigDecimal("19.99"));
		var p2 = new Product("p-2", "Product name", new BigDecimal("19.99"));
		productRepository.save(p1);
		productRepository.save(p2);
	}
}
