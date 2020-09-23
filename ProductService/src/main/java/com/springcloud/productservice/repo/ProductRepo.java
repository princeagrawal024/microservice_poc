package com.springcloud.productservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springcloud.productservice.model.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {

}
