package com.shop.productservice.domain;

import org.springframework.stereotype.Service;

@Service
public class ProductService {
	private final ProductRepository productRepository; 
	
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository; 
	}
	
	public Iterable<Product> viewProducts() { 
		return productRepository.findAll();
	}
	
	public Product getProduct(String id) { 
		return productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException(id));
	}
	 
	public Product saveProduct(Product product) { 
		if(productRepository.existsById(product.id())) {
			throw new ProductAlreadyExistsException(product.id());
		} 
		return productRepository.save(product);
	}
	
	public Product updateProduct(String id, Product product) { 
		return productRepository.findById(id)
				.map(existingProduct -> {
					var productToUpdate = new Product(
							existingProduct.id(),
							product.name(), 
							product.price());
					return productRepository.save(productToUpdate);
				})
				.orElseGet(() -> saveProduct(product));
	}
	
	public void deleteProduct(String id) { 
		productRepository.deleteById(id);
	}
}
