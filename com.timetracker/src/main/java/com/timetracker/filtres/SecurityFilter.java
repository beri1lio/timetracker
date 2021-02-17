package com.timetracker.filtres;

import com.timetracker.db.entity.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class SecurityFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException { }

    @Override
    public void destroy() { }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Object roleParam = request.getSession().getAttribute("userRole");

        if (roleParam == null && request.getAttribute("isRedirected") == null &&
                request.getHeader("Accept").contains("text/html")
        ) {
            response.sendRedirect("/authorization-user");
            request.setAttribute("isRedirected", true);
        } else {
            Role userRole = (Role) roleParam;
            String requestUrl = request.getRequestURI();
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
