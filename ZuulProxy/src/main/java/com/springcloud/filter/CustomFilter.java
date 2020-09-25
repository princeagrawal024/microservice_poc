package com.springcloud.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class CustomFilter extends ZuulFilter {
	
	
	private static Logger logger = LoggerFactory.getLogger(CustomFilter.class); //frm sl4j
	

	@Override
	/*
	 This should always return true if we want the filter to be happening. 
	 Within this method we can have conditional logic.
	 So we can configure this filter to execute only based on certain conditional condition.  
	 */
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	
	
	/*
	 IMPORTANT 
	 This is where the logic should be implemeted.
	 Inside this we can have access to the entire Zuul Context.
	 */
	@Override
	public Object run() throws ZuulException {
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest request = context.getRequest();
		logger.info(String.format("%s request to %s", request.getMethod(),request.getRequestURL()));
		return null;
	}

	
	
	
	
	/*
	 pre post route error 
	 */
	@Override
	public String filterType() {
		return "pre";
	}

	
	/*
	 This determines the ordering in which the filters are executed starting from.
	 Here we specified 1, so this is the first filter.
	 It is having value 1, so this filter will be executed first,
	 we can create some other filter, and return 2 from it, then that 
	 will execute after this "1"
	 */
	@Override
	public int filterOrder() {
		return 1;
	}

}
