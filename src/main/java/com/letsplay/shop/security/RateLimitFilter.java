package com.letsplay.shop.security;

import java.io.IOException;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Component;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RateLimitFilter implements Filter {

    private static final int MAX_REQUESTS_PER_ITRVL = 2; // just to test needs to be bigger XD
    private static final long INTERVAL = 60 * 1000;

    private static class RateLimitInfo {
        AtomicInteger counter = new AtomicInteger(0);
        long windowStart = Instant.now().toEpochMilli();
    }

    private final Map<String, RateLimitInfo> ipLimits = new ConcurrentHashMap<>();

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String ip = request.getRemoteAddr();
        RateLimitInfo info = ipLimits.computeIfAbsent(ip, k -> new RateLimitInfo());

        long now = Instant.now().toEpochMilli();

        synchronized (info) {
            if (now - info.windowStart > INTERVAL) {
                info.counter.set(0);
                info.windowStart = now;
            }

            if (info.counter.incrementAndGet() > MAX_REQUESTS_PER_ITRVL) {
                response.setStatus(404); // need to now what is the too many request status code
                response.getWriter().write("Rate limit exeeded for IP: " + ip);
                return;
            }
        }

        chain.doFilter(request, response);
    }

}
