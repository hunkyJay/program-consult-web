package com.programLearning.ssm.service.interceptor;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;
        HttpSession session = req.getSession();


        if(session.getAttribute("User_name")==null && session.getAttribute("mentor_username")==null && session.getAttribute("student_username")==null
                && !req.getRequestURI().equals("")
                && !req.getRequestURI().equals("/")
                && req.getRequestURI().indexOf("/mentor/register") == -1
                && req.getRequestURI().indexOf("/student/register") == -1
                && req.getRequestURI().indexOf("/mentor/login") == -1
                && req.getRequestURI().indexOf("/mentor/doLogin") == -1
                && req.getRequestURI().indexOf("/student/login") == -1
                && req.getRequestURI().indexOf("/student/doLogin") == -1
                && req.getRequestURI().indexOf("/admin/doLogin") == -1
                && req.getRequestURI().indexOf("/admin/login") == -1
                && req.getRequestURI().indexOf("/admin/verify") == -1// -1表示不存在该url
        ){
            // 没有登录
            if(req.getRequestURI().contains("/admin/")){
                res.sendRedirect(req.getContextPath()+"/admin/login");
            }else if(req.getRequestURI().contains("/student/")){
                res.sendRedirect(req.getContextPath()+"/student/login");
            }else if(req.getRequestURI().contains("/mentor/")){
                res.sendRedirect(req.getContextPath()+"/mentor/login");
            }
            chain.doFilter(request, response);
        }else{
            // 已经登录，继续请求下一级资源（继续访问）
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
