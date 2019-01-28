package com.tensquare.manager.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;

@Component
public class ManagerFilter extends ZuulFilter {
    /**
     * 在请求前或者后执行
     * pre  post
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 多个过滤器执行顺序
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否启用当前过滤器
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器内执行的操作    return 任何object的值都表示继续执行
     * setsendzuulResponse(false)表示不再执行
     * @return
     * @throws ZuulException
     */

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Object run() throws ZuulException {
        System.out.println("经过了后台过滤器");
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        //放行网关的首次转发请求
        if(request.getMethod().equals("OPTIONS"))
            return null;
        //放行登陆请求
        if(request.getRequestURI().indexOf("login") > 0)
            return null;

        String header = request.getHeader("Authorization");
        if( header != null && !"".equals(header)) {
            if(header.startsWith("Bearer ")) {
                String token = header.substring(7);
                try {
                    Claims claims = jwtUtil.parseJWT(token);
                    String roles = (String) claims.get("roles");
                    if("admin".equals(roles)) {
                        //头信息转发并放行
                        requestContext.addZuulRequestHeader("Authorization", header);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    requestContext.setSendZuulResponse(false);
                }
            }
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(403);
            requestContext.setResponseBody("权限不足");
            requestContext.getResponse().setContentType("text/html;charset=utf-8");
        }
        return null;
    }
}
