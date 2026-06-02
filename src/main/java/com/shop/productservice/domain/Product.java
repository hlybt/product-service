package com.shop.productservice.domain;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record Product(
	@NotBlank(message = "The product id must be defined.")
	String id, 
	
	@NotBlank(message = "The product name must be defined.")
	String name, 
	
	@NotBlank(message = "The product price must be defined.")
	@Positive(message = "The product price must be greater than zero.")
	BigDecimal price) {
}
 