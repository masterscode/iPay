package com.Index.configs.security;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;

@Slf4j
@Component
@RequiredArgsConstructor
public class ApiKeyFilter implements Filter {
    private final Gson gson;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        final String authHeader = request.getHeader("Authorization");


        if (StringUtils.isBlank(authHeader)) {

            servletResponse.setContentType("application/json");
            PrintWriter printWriter = servletResponse.getWriter();

            printWriter.println(
                    gson.toJson(Collections.singletonMap("error", "Unauthorized"))
            );

            printWriter.close();
        }
        log.info("convert the object to this URL is ::" + request.getRequestURI());
        log.info("done with request sending response URL is ::" + request.getRequestURI());


        filterChain.doFilter(servletRequest, servletResponse);
    }
}
