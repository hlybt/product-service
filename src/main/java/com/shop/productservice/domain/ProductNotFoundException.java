package com.shop.productservice.domain;

public class ProductNotFoundException extends RuntimeException {
	public ProductNotFoundException(Long id) {
		super("The product with id " + id + " was not found.");
	}
}
