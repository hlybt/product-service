package com.shop.productservice.persistence;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.shop.productservice.domain.Product;
import com.shop.productservice.domain.ProductRepository;

@Repository
public class InMemoryProductRepository implements ProductRepository {
	private static final Map<String, Product> products = 
			new ConcurrentHashMap<>();

	@Override 
	public Iterable<Product> findAll() {
		return products.values();
	}

	@Override
	public Optional<Product> findById(String id) {
		return existsById(id) ? Optional.of(products.get(id)) :
			Optional.empty();
	}

	@Override
	public boolean existsById(String id) {
		return products.get(id) != null;
	}

	@Override
	public Product save(Product product) {
		products.put(product.id(), product);
		return product;
	}

	@Override
	public void deleteById(String id) {
		products.remove(id);
	}
	
}
