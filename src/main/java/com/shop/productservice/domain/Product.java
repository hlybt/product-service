package com.shop.productservice.domain;

import java.math.BigDecimal;
import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record Product (
    @Id
    @Column(value = "id") 
    Long id,
    
    @NotBlank(message = "The product name must be defined.")
    String name, 
    
    @NotNull(message = "The product price must be defined.")
    @Positive(message = "The product price must be greater than zero.")
    BigDecimal price,
    
    @CreatedDate
    Instant createdDate,

    @LastModifiedDate
    Instant lastModifiedDate,
    
    @Version
    Long version) { 
    
    public static Product of(String name, BigDecimal price) {
        return new Product(null, name, price, null, null, null);
    }
}