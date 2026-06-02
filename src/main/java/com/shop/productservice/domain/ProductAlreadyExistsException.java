package com.shop.productservice.domain;

public class ProductAlreadyExistsException extends RuntimeException { 
	public ProductAlreadyExistsException(String id) {
		super("A product with id " + id + " already exists.");
	}
}
