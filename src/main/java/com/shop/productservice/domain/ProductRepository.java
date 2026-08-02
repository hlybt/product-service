package com.shop.productservice.domain;

import java.util.Optional;

public interface ProductRepository {
	Iterable<Product> findAll();
	Optional<Product> findById(String id);
	boolean existsById(String id);
	Product save(Product product);
	void deleteById(String id);
	void deleteAll();
}
