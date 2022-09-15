package com.intern.ambassador.config.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 액세스 권한이 없는 리소스에 접근할 경우 발생하는 예외에 대한 처리
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

    /**
     * @param accessDeniedException 액세스 권한이 없는 리소스에 접근할 경우 발생하는 예외 (클래스의 객체)
     * 이 예외를 처리하기 위해 AccessDeniedHandler 인터페이스가 사용되며,
     * SecurityConfiguration에도 exceptionHandling() 메서드를 통해 추가함.
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        LOGGER.info("[handle] 접근이 막혔을 경우 경로 리다이렉트");
        response.sendRedirect("/sign-api/exception");
    }
}
