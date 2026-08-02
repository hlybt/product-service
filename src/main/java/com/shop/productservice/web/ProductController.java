package com.shop.productservice.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.shop.productservice.domain.Product;
import com.shop.productservice.domain.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("products")
public class ProductController {
	private final ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping
	public Iterable<Product> get() {
		return productService.viewProducts();
	}
	
	@GetMapping("{id}")
	public Product getById(@PathVariable("id") String id) {
		return productService.getProduct(id);
	}
	 
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Product post(@Valid @RequestBody Product product) {
		return productService.saveProduct(product);
	}
	
	@PutMapping("{id}")
	public Product put(@PathVariable("id") String id, @Valid @RequestBody Product product) {
		return productService.updateProduct(id, product);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		productService.deleteProduct(id);
	}
}
