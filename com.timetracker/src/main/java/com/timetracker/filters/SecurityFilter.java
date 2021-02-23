package com.timetracker.filters;

import com.timetracker.db.entity.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * This class add security filters.
 */
@WebFilter(urlPatterns = "/*")
public class SecurityFilter implements Filter {
    private HttpServletRequest httpRequest;

    private static final String[] loginRequiredURLs = {
            "/profile", "/tasks", "/categories", "/users", "/approve-tasks"
    };

    private static final String[] forbiddenForClient = {
            "/tasks", "/categories", "/users", "/approve-tasks"
    };

    /**
     * Method give permission to certain roles for certain sites.
     * If role is admin -> redirect to /tasks, if role is client to redirect /profile.
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        httpRequest = (HttpServletRequest) request;

        HttpSession session = httpRequest.getSession(false);
        HttpServletResponse httpServletResponse= (HttpServletResponse) response;

        boolean isLoggedIn = (session != null && session.getAttribute("userID") != null);

        boolean isLoginRequest = httpRequest.getRequestURI().contains("authorization-user");
        boolean isLoginPage = httpRequest.getRequestURI().endsWith("authorization");
        boolean isIndexPage = httpRequest.getRequestURI().endsWith("/");

        if (isLoggedIn && isIndexPage) {
            if (isAdmin()) {
                httpServletResponse.sendRedirect("/tasks");
            } else if (isClient()) {
                httpServletResponse.sendRedirect("/profile");
            }
        }

        if (isLoggedIn && (isLoginRequest || isLoginPage)) {
            httpServletResponse.sendRedirect("/");
        } else if (!isLoggedIn && isLoginRequired()) {
            httpServletResponse.sendRedirect("/authorization");
        } else if(isLoggedIn && isClient() && isForbiddenForClient()) {
            httpServletResponse.sendRedirect("/404");
        } else {
            chain.doFilter(request, response);
        }
    }

    /**
     * Admin check.
     */
    private boolean isAdmin() {
        String role = (String) httpRequest.getSession(false).getAttribute("userRole");
        return Role.ADMIN.name().equals(role);
    }

    /**
     * Client check.
     */
    private boolean isClient() {
        String role = (String) httpRequest.getSession(false).getAttribute("userRole");
        return Role.CLIENT.name().equals(role);
    }

    /**
     * Check list forbidden URLs.
     */
    private boolean isForbiddenForClient() {
        return checkPages(forbiddenForClient);
    }

    /**
     * Check all required URLs.
     */
    private boolean isLoginRequired() {
        return checkPages(loginRequiredURLs);
    }

    /**
     * Check URLs.
     */
    private boolean checkPages(String[] pages) {
        String requestURL = httpRequest.getRequestURL().toString();

        for (String loginRequiredURL : pages) {
            if (requestURL.endsWith(loginRequiredURL)) {
                return true;
            }
        }

        return false;
    }

    public void destroy() {
    }

    public void init(FilterConfig fConfig) throws ServletException {
    }
}