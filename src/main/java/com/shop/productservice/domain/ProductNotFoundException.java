package com.shop.productservice.domain;

public class ProductNotFoundException extends RuntimeException {
	public ProductNotFoundException(String id) {
		super("The product with id " + id + " was not found.");
	}
}
