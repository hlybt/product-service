package com.shop.productservice;

import java.math.BigDecimal;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.shop.productservice.domain.Product;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductValidationTests {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeAll
    static void setUpValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterAll
    static void closeValidator() {
        validatorFactory.close();
    }

    @Test
    void whenProductIsValid_thenNoConstraintViolations() {
        Product product = new Product("p-1", "Product name", new BigDecimal("19.99"));

        Set<ConstraintViolation<Product>> violations = validator.validate(product);

        assertTrue(violations.isEmpty(), "Expected no constraint violations for a valid product");
    }

    @Test
    void whenIdBlank_thenViolationOnId() {
        Product product = new Product("", "Name", new BigDecimal("1.00"));

        Set<ConstraintViolation<Product>> violations = validator.validate(product);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> "id".equals(v.getPropertyPath().toString())),
                "Expected a constraint violation for property 'id'");
    }

    @Test
    void whenNameBlank_thenViolationOnName() {
        Product product = new Product("p-2", "", new BigDecimal("1.00"));

        Set<ConstraintViolation<Product>> violations = validator.validate(product);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> "name".equals(v.getPropertyPath().toString())),
                "Expected a constraint violation for property 'name'");
    }

    @Test
    void whenPriceNull_thenViolationOnPrice() {
        Product product = new Product("p-3", "Name", null);

        Set<ConstraintViolation<Product>> violations = validator.validate(product);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> "price".equals(v.getPropertyPath().toString())),
                "Expected a constraint violation for property 'price' when null");
    }

    @Test
    void whenPriceNotPositive_thenViolationOnPrice() {
        Product productZero = new Product("p-4", "Name", new BigDecimal("0"));
        Product productNegative = new Product("p-5", "Name", new BigDecimal("-5"));

        Set<ConstraintViolation<Product>> vZero = validator.validate(productZero);
        Set<ConstraintViolation<Product>> vNeg = validator.validate(productNegative);

        assertFalse(vZero.isEmpty());
        assertTrue(vZero.stream().anyMatch(v -> "price".equals(v.getPropertyPath().toString())),
                "Expected a constraint violation for property 'price' when zero");

        assertFalse(vNeg.isEmpty());
        assertTrue(vNeg.stream().anyMatch(v -> "price".equals(v.getPropertyPath().toString())),
                "Expected a constraint violation for property 'price' when negative");
    }
}
