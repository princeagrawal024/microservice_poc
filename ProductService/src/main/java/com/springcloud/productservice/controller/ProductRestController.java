package com.springcloud.productservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
	 @HystrixCommand(fallbackMethod = "sendErrorResponse")
	 public Product create(@RequestBody Product product) {
		 System.out.println("product - create called");
		 
		 Coupon coupon = couponClient.getCoupon(product.getCouponCode());
		 product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
		 return repo.save(product);
	 }
	 
	 //NOTE: the method signature of this method must be exact same as create.
	 public Product sendErrorResponse(@RequestBody Product product) {
		 System.out.println("Error Occured.");
		 product.setDescription("Error occured"); //Adding error to the description just for testing purpose
		 return product; //returning same object which user sent.
	 }
	 
	
}
