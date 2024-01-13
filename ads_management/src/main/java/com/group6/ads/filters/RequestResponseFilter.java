package com.group6.ads.filters;

import com.group6.ads.controllers.admin.contracts.ContractController;
import com.group6.ads.controllers.client.contracts.ContractClientController;
import com.group6.ads.services.contracts.ContractServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class RequestResponseFilter extends OncePerRequestFilter {
    private static final Logger logAdmin =  LoggerFactory.getLogger(ContractController.class);
    private static final Logger logClient =  LoggerFactory.getLogger(ContractClientController.class);

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        ContentCachingRequestWrapper cachingRequestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper cachingResponseWrapper = new ContentCachingResponseWrapper(response);

        filterChain.doFilter(cachingRequestWrapper, cachingResponseWrapper);

        String requestBody = getValueAsString(cachingRequestWrapper.getContentAsByteArray(),cachingRequestWrapper.getCharacterEncoding());
        String responseBody = getValueAsString(cachingResponseWrapper.getContentAsByteArray(),cachingResponseWrapper.getCharacterEncoding());

        int statusCode = response.getStatus();

        if(request.getRequestURI().startsWith("/api/")){
            if(request.getRequestURI().contains("client")){
                if(statusCode == 200)
                    logClient.info("METHOD = {}; REQUEST URI = {}; REQUEST BODY = {}; RESPONSE CODE = {}; RESPONSE BODY = {}",
                            request.getMethod(),
                            request.getRequestURI(),
                            requestBody,
                            statusCode,
                            responseBody);
                else
                    logClient.error("METHOD = {}; REQUEST URI = {}; REQUEST BODY = {}; RESPONSE CODE = {}; RESPONSE BODY = {}",
                            request.getMethod(),
                            request.getRequestURI(),
                            requestBody,
                            statusCode,
                            responseBody);
            }
            else{
                if(statusCode == 200)
                    logAdmin.info("METHOD = {}; REQUEST URI = {}; REQUEST BODY = {}; RESPONSE CODE = {}; RESPONSE BODY = {}",
                            request.getMethod(),
                            request.getRequestURI(),
                            requestBody,
                            statusCode,
                            responseBody);
                else
                    logAdmin.error("METHOD = {}; REQUEST URI = {}; REQUEST BODY = {}; RESPONSE CODE = {}; RESPONSE BODY = {}",
                            request.getMethod(),
                            request.getRequestURI(),
                            requestBody,
                            statusCode,
                            responseBody);
            }

        }

        cachingResponseWrapper.copyBodyToResponse();
    }

    private String getValueAsString(byte[] contentAsByteArray, String characterEncoding) {
        String dataAsString = "";
        try {
            dataAsString = new String(contentAsByteArray, characterEncoding).replaceAll("\\s", "");
        }catch (Exception e) {
            e.printStackTrace();
        }
        return dataAsString;
    }
}
