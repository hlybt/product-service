package com.shop.productservice.domain;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

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
        Product product = Product.of("Product name", new BigDecimal("19.99"));

        Set<ConstraintViolation<Product>> violations = validator.validate(product);

        assertTrue(violations.isEmpty(), "Expected no constraint violations for a valid product");
    }

    @Test
    void whenNameBlank_thenViolationOnName() {
        Product product = Product.of("", new BigDecimal("1.00"));

        Set<ConstraintViolation<Product>> violations = validator.validate(product);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> "name".equals(v.getPropertyPath().toString())),
                "Expected a constraint violation for property 'name'");
    }

    @Test
    void whenPriceNull_thenViolationOnPrice() {
        Product product = Product.of("Name", null);

        Set<ConstraintViolation<Product>> violations = validator.validate(product);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> "price".equals(v.getPropertyPath().toString())),
                "Expected a constraint violation for property 'price' when null");
    }

    @Test
    void whenPriceNotPositive_thenViolationOnPrice() {
        Product productZero = Product.of("Name", new BigDecimal("0"));
        Product productNegative = Product.of("Name", new BigDecimal("-5"));

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
