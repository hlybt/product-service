package com.shop.productservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.productservice.config.ProductProperties;

@RestController
public class HomeController {
	private final ProductProperties productProperties;

	public HomeController(ProductProperties productProperties) {
		this.productProperties = productProperties;
	}

	@GetMapping("/")
	public String greeting() {
		return productProperties.getGreeting();
	} 
}
