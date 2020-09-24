package com.springcloud.productservice.restclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springcloud.productservice.model.Coupon;

@FeignClient("COUPON-SERVICE")  //-->Imp1
public interface CouponClient {
    @RequestMapping(value = "/couponapi/coupons/{code}", method = RequestMethod.GET)
	public Coupon getCoupon(@PathVariable("code") String code);//-->Imp2 Rest things exact same except body
	
}