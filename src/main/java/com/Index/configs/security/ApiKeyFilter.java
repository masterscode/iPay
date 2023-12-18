package com.Index.configs.security;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        final String authHeader = request.getHeader("Authorization");


        if (StringUtils.isNotBlank(authHeader)) filterChain.doFilter(servletRequest, servletResponse);

        else try (PrintWriter printWriter = response.getWriter()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            servletResponse.setContentType("application/json");
            printWriter.println(
                    gson.toJson(Collections.singletonMap("error", "Unauthorized"))
            );
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }
}
