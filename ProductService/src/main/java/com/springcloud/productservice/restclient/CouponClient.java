package com.springcloud.productservice.restclient;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springcloud.productservice.model.Coupon;

@FeignClient("COUPON-SERVICE")  //-->Imp1
@RibbonClient("COUPON-SERVICE") 
public interface CouponClient {
    @RequestMapping(value = "/couponapi/coupons/{code}", method = RequestMethod.GET)
	public Coupon getCoupon(@PathVariable("code") String code);//-->Imp2 Rest things exact same except body
	
}

/*
@RibbonClient("COUPON-SERVICE") 
"COUPON-SERVICE" this is to tell ribbon where the client is running. We will just provide service name
Ribbon will automatically figure out where the service is running by connecting to Eureka and
then it will distribute the load across the servers */