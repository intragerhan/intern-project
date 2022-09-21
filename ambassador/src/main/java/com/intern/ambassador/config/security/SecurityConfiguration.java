package com.intern.ambassador.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .httpBasic().disable() // UI를 사용하는 것을 기본값으로 가진 시큐리티 설정을 비활성화 함
                .csrf().disable() // REST API에서는 CSRF 보안이 필요없기 때문에 비활성화
                .sessionManagement() // REST API 기반 애플리케이션의 동작 방식 설정
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션은 사용하지 않기 때문에 STATELESS로 설정
                .and()
                .authorizeRequests() // 애플리케이션에 들어오는 요청에 대한 사용 권한 체크
                .antMatchers("/sign-api/sign-in", "/sign-api/sign-up", "/sign-api/exception").permitAll()
                .antMatchers( "/applications/**").permitAll() // application으로 시작하는 경로의 GET 요청은 모두 허용
                .antMatchers("**exception**").permitAll() // exception 이라는 단어가 들어간 경로는 모두 허용
                .anyRequest().hasRole("ADMIN") // 기타 요청은 인증된 권한을 가진 사용자에게만 허용(ADMIN,USER)
                .and()
                .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler()) // 권한을 확인하는 과정에서 통과하지 못 하는 예외가 발생할 경우 예외 전달.
                .and()
                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint()) // 인증 과정에서 예외가 발생할 경우 예외 전달
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                        UsernamePasswordAuthenticationFilter.class);
    }

    /** 인증과 인가가 모두 적용되기 전에 동작하는 설정
     * 인증과 인가가 적용되지 않는 리소스 접근에 대해서만 사용
     */
    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity.ignoring().antMatchers("/v2/api-docs", "/swagger-resources/**",
                "/swagger-ui.html", "/webjars/**", "/swagger/**", "/sign-api/exception");
    }
}
