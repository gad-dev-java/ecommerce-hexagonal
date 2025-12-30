package com.gad.ecommerce.common.logging;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

@Slf4j
public class FeignClientInterceptor implements RequestInterceptor {
    private static final String TRACE_ID_HEADER = "X-Trace-Id";
    private static final String USER_ID_HEADER = "X-User-Id";
    private static final String TRACE_ID_MDC_KEY = "traceId";
    private static final String USER_ID_MDC_KEY = "userId";

    @Override
    public void apply(RequestTemplate template) {
        String traceId = MDC.get(TRACE_ID_MDC_KEY);
        String userId = MDC.get(USER_ID_MDC_KEY);

        if (traceId != null) {
            template.header(TRACE_ID_HEADER, traceId);
            log.debug("Propagating traceId: {}", traceId);
        }

        if (userId != null) {
            template.header(USER_ID_HEADER, userId);
            log.debug("Propagating userId: {}", userId);
        }
    }
}
