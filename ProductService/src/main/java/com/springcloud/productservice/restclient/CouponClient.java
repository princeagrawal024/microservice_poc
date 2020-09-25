package com.springcloud.productservice.restclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springcloud.productservice.model.Coupon;


@FeignClient("zuul-api-gateway")  
public interface CouponClient {
    @RequestMapping(value = "/coupon-service/couponapi/coupons/{code}", method = RequestMethod.GET)
	public Coupon getCoupon(@PathVariable("code") String code);//-->Imp2 Rest things exact same except body
	
}



/*
@RibbonClient("COUPON-SERVICE") 
"COUPON-SERVICE" this is to tell ribbon where the client is running. We will just provide service name
Ribbon will automatically figure out where the service is running by connecting to Eureka and
then it will distribute the load across the servers */


/*
 FeignClient("zuul-api-gateway")
 
   wrt Zuul proxy we have added three changes.
   
   1. @FeignClient("COUPON-SERVICE")  --changed to -> @FeignClient("zuul-api-gateway")  
   This is because now the request will first go to ZullProxy server and then it will redired to the actual server.
   
   2.value = "/couponapi/coupons/{code}" --changed to-> value = "/COUPON-SERVICE/couponapi/coupons/{code}"
   This is because sicne we remove the maping in step1, Zull should know in which server to
   hit the request. So, we add as prefix, the spring.application.name of that server in url
   
   3. We removed client side Load Balancing i.e @RibbonClient("COUPON-SERVICE")
   This is because Zull will do the server side Load balancing automatically
   So we dont need client side load balancing
   
   Internally Zuul also users ribbon-eureka to do server side load balancing
   
 
*/