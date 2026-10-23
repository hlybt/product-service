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

	public Product getProduct(Long id) {
		return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
	}

	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	public Product updateProduct(Long id, Product product) {
		return productRepository.findById(id).map(existingProduct -> {
			var productToUpdate = new Product(existingProduct.id(), product.name(), product.price(),
					existingProduct.createdDate(), existingProduct.lastModifiedDate(), existingProduct.version());
			return productRepository.save(productToUpdate);
		}).orElseGet(() -> saveProduct(product));
	}

	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
}
