package com.baihoo.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * 
 * PRE：这种过滤器在请求被路由之前调用。我们可利用这种过滤器实现身份验证，在集群中选择请求的微服务，记录调试信息等
 * 
 * @author Administrator
 *
 */
public class PreZuulFilter extends ZuulFilter{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PreZuulFilter.class);
	
	/**
	 * 是否使用该过滤器，true使用 false禁用
	 */
	public boolean shouldFilter() {
		
		return true;
	}
	
	/**
	 * 重点层：
	 *   filter 运行的具体逻辑代码编写
	 */
	public Object run() throws ZuulException {
		// 1. zuul的请求上下文获取HttpServletRequest
		HttpServletRequest httpServletRequest= RequestContext.getCurrentContext().getRequest();
		// 2. 获取请求的host地址
		String host = httpServletRequest.getRemoteHost();
		LOGGER.info("请求的host：{}" , host);
		
		
		return null;
	}
	/**
	 * filter 执行的类型
	 */
	public String filterType() {
		return "pre";
	}
	
	/**
	 *  过滤器执行顺序，数字越大越靠后
	 */
	public int filterOrder() {
		return 1;
	}

}
