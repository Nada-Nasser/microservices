package com.learning.commonlib.tracing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class TraceIdFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(TraceIdFilter.class);

    private final Tracer tracer;

    public TraceIdFilter(Tracer tracer) {
        this.tracer = tracer;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (response instanceof HttpServletResponse httpServletResponse &&
                tracer.currentSpan() != null) {

            String traceId = Objects.requireNonNull(tracer.currentSpan()).context().traceId();
            httpServletResponse.addHeader("X-B3-TraceId", traceId);
            logger.info("Added Trace ID to response: {}", traceId);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
