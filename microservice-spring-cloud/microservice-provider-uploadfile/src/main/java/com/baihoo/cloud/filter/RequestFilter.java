package com.baihoo.cloud.filter;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @project: springboot-vue
 * @author: chen.baihoo
 * @date: 2019/3/30 22:28
 * @Description: TODO 解决VUE-resource跨域问题：No 'Access-Control-Allow-Origin' header is present on the requested resource.
 *  错误场景：
 *      前端是vue 工程，写的是绝对 URL 请求后端工程接口，报错如下：
 *          No 'Access-Control-Allow-Origin' header is present on the requested resource
 *
 *      解决方法，后端开放跨域：新增一个过滤器，改写所有请求头信息。
 * version 0.1
 */

@Order(1) // @order代表注解表示执行过滤顺序，值越小，越先执行
@WebFilter(filterName = "requestFilter",urlPatterns = {"/*"})
public class RequestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest)servletRequest;


        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        /*
        origin 參數指定了一個可以存取資源的 URI。瀏覽器必定會執行此檢查。對一個不帶有身分驗證的請求，
        伺服器可以指定一個「*」作為萬用字元（wildcard），從而允許任何來源存取資源
        */
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        // Access-Control-Expose-Headers 標頭表示伺服器允許瀏覽器存取回應標頭的白名單
        response.setHeader("Access-Control-Expose-Headers", "X-My-Custom-Header, X-Another-Custom-Header");
        // Access-Control-Max-Age 標頭表示了預檢請求的結果可以被快取多長的時間
        response.setHeader("Access-Control-Max-Age", "3600");
        /*
        Access-Control-Allow-Credentials 標頭表示了當請求的 credentials 旗標為真時，是否要回應該請求。
        當用在預檢請求的回應中，那就是指示後續的實際請求可否附帶身分驗證。請注意，由於簡單的 GET 請求
        沒有預檢，所以如果一個簡單請求帶有身分驗證，同時假設此標頭沒有與資源一併回傳，則回應會被瀏覽
        器所忽略並且不會回傳予呼叫的網站內容
        */
        response.setHeader("Access-Control-Allow-Credentials", "true");
        // Access-Control-Allow-Methods 標頭表示存取資源所允許的方法，用來回應預檢請求。上面已討論請求之預檢的條件
        response.setHeader("Access-Control-Allow-Methods", "POST , GET , OPTIONS , DELETE");
        // Access-Control-Allow-Headers 標頭用在回傳予預檢請求的回應當中，以指定哪些 HTTP 標頭可以於實際請求中使用
        response.setHeader("Access-Control-Allow-Headers", "Origin , X-Requested-With , Content-Type , Accept , token");

        filterChain.doFilter(servletRequest, servletResponse);
    }
    @Override
    public void destroy() {

    }
}
