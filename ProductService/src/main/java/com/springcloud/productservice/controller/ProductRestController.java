package com.springcloud.productservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springcloud.productservice.model.Coupon;
import com.springcloud.productservice.model.Product;
import com.springcloud.productservice.repo.ProductRepo;
import com.springcloud.productservice.restclient.CouponClient;

@RestController
@RequestMapping("/productapi")
public class ProductRestController {

	 @Autowired
	 private ProductRepo repo;
	 
	 @Autowired
	 private CouponClient couponClient;
	
	 //@RequestMapping(value ="/products", method = RequestMethod.POST)
	 
	 @PostMapping("/products")
	 public Product create(@RequestBody Product product) {
		 System.out.println("product - create called");
		 
		 Coupon coupon = couponClient.getCoupon(product.getCouponCode());
		 product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
		 return repo.save(product);
	 }
	 
	
}
