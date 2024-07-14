package com.example.interceptor;

import com.example.service.EnhancedLogService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class RequestInterceptor implements HandlerInterceptor {
    private final EnhancedLogService logger;
    private StopWatch stopWatch;

    public RequestInterceptor(EnhancedLogService logger) {
        this.logger = logger.setNameLogger(this.getClass());
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        this.stopWatch = new StopWatch();
        this.stopWatch.start(request.getRequestURI());
        logger.log("Заголовки запроса: " + this.getRequestHeaders(request));
        logger.log("Метод запроса: " + request.getMethod());
        logger.log("URL: " + request.getRequestURI());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.log("Заголовки ответа: " + this.getResponseHeaders(response));
        logger.log("Код ответа: " + response.getStatus());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        this.stopWatch.stop();
        logger.log("Время обработки запроса: " + this.stopWatch.prettyPrint());
    }

    private Map<String, String> getRequestHeaders(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }

    private Map<String, String> getResponseHeaders(HttpServletResponse response) {
        Collection<String> headerNames = response.getHeaderNames();
        return headerNames.stream()
                .collect(Collectors.toMap(response::getHeader, Function.identity()));
    }
}
