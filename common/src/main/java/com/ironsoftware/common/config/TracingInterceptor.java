package com.ironsoftware.common.config;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.cloud.sleuth.Span;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
public class TracingInterceptor implements HandlerInterceptor {

    private final Tracer tracer;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Span span = tracer.currentSpan();
        if (span != null) {
            span.tag("http.url", request.getRequestURL().toString());
            span.tag("http.method", request.getMethod());
        }
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        Span span = tracer.currentSpan();
        if (span != null) {
            span.tag("http.status", String.valueOf(response.getStatus()));
        }
    }
}