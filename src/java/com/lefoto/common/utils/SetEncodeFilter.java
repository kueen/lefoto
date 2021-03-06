/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.common.utils;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *
 * @author Eric
 */
public class SetEncodeFilter implements Filter {

    protected FilterConfig filterConfig = null;
    protected String defaultEncoding = null;

    /**//* (non-Javadoc) 
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig) 
     */

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub 
        this.filterConfig = arg0;
        this.defaultEncoding = filterConfig.getInitParameter("defaultencoding");
    }

    /**//* (non-Javadoc) 
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain) 
     */

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        // TODO Auto-generated method stub 
        request.setCharacterEncoding(selectEncoding(request));
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

        this.defaultEncoding = null;
        this.filterConfig = null;
    }

    protected String selectEncoding(ServletRequest request) {

        return this.defaultEncoding;
    }
}
