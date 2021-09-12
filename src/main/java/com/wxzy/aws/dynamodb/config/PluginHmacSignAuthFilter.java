package com.wxzy.aws.dynamodb.config;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

import com.wyze.common.request.BodyReaderHttpServletRequestWrapper;
import com.wyze.common.util.EncryptUtil;
import com.wyze.common.util.HMacMD5Util;

public class PluginHmacSignAuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
        throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest)servletRequest;
        final BodyReaderHttpServletRequestWrapper requestWrapper = new BodyReaderHttpServletRequestWrapper(request);
        String accessToken =
            "lvtx.Tl3UIsNWk0v9ULkhw4PDf0bNr7C+yHPVWr95enUEX9IHqP0SJlSbYRKN/v+8/mD4ga/Xd9cOszRG43Dhomskk0IZRAo58jV9qxkiqac5sWz0BhP779mnR1Svab5l6WFCRym+p/NV0O+cyphyeEFiDRdu0cbGiVhmMjA8FIwu8HTvoVsSpSDZahswxeqIRgjAu9KQOA==";
        String appKey = "463GMZRyuOvqEHFg6lCDEM2AXnwvBz6R";
        final String md5 = EncryptUtil.getMD5(accessToken + appKey);
        final String oldSign = HMacMD5Util.HMacMD5Signature(md5, HMacMD5Util.getSignBody(requestWrapper));
        System.out.println(oldSign + "CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC");
    }

    @Override
    public void destroy() {

    }
}
