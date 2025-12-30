package com.gad.ecommerce.common.logging;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.io.IOException;

@Slf4j
public class MdcFilter implements Filter {
    private static final String TRACE_ID_HEADER = "X-Trace-Id";
    private static final String USER_ID_HEADER = "X-User-Id";
    private static final String TRACE_ID_MDC_KEY = "traceId";
    private static final String USER_ID_MDC_KEY = "userId";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        try {
            String traceId = httpRequest.getHeader(TRACE_ID_HEADER);
            String userId = httpRequest.getHeader(USER_ID_HEADER);

            if (traceId != null && !traceId.isEmpty()) {
                MDC.put(TRACE_ID_MDC_KEY, traceId);
            }

            if (userId != null && !userId.isEmpty()) {
                MDC.put(USER_ID_MDC_KEY, userId);
            }

            log.debug("MDC populated - traceId: {}, userId: {}", traceId, userId);

            chain.doFilter(request, response);

        } finally {
            MDC.clear();
        }
    }
}
