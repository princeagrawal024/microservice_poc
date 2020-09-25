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
 
@FeignClient
Name itself is feign"Client"
This is a client (similar to RestCLient that we used in earlier project.
It is used to get response for other end points. 

@RibbonClient: user for load balancing
  
@RibbonClient("COUPON-SERVICE") 
"COUPON-SERVICE" this is to tell ribbon where the client is running. We will just provide service name
Ribbon will automatically figure out where the service is running by connecting to Eureka and
then it will distribute the load across the servers */


/*
 FeignClient("zuul-api-gateway")
 
   wrt Zuul proxy we have added three changes.
   
   1. @FeignClient("COUPON-SERVICE")  --changed to -> @FeignClient("zuul-api-gateway")  
   This is because now the request will first go to ZuulProxy server and then it will redirect to the actual server.
   
   2.value = "/couponapi/coupons/{code}" --changed to-> value = "/COUPON-SERVICE/couponapi/coupons/{code}"
   This is because since we removed the mapping in step1, Zuul should know in which server to
   hit the request. So, we add as prefix, the spring.application.name of that server in url
   
   3. We removed client side Load Balancing i.e @RibbonClient("COUPON-SERVICE")
   This is because Zuul will do the server side Load balancing automatically
   So we don't need client side load balancing
   
   Internally Zuul also users ribbon-eureka to do server side load balancing
   
 
*/