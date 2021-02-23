package com.timetracker.filters;

import javax.servlet.*;
import java.io.IOException;

/**
 * This class allows write in data base not only English but also Russian.
 */
public class QueryFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * This method allows to use Character Encoding UTF-8;
     * @param servletRequest - servlet request
     * @param servletResponse - servlet response
     * @param filterChain - filter chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
