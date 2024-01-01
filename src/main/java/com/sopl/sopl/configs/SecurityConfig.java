//package com.sopl.sopl.configs;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Slf4j
//@RequiredArgsConstructor
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable();
////        http.headers().frameOptions().sameOrigin();
//
//        //페이지 접근 관련
//        http.authorizeRequests()
//            //인증 요구
//            .antMatchers("/user/**").authenticated()
//            //ADMIN만 접근 가능
//            .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN");
//
//        //접근 허용
//        http.authorizeRequests()
//            .antMatchers("/", "/login/**", "/register/**", "/board/home")
//            .permitAll();
//
//        //로그인
//        http.formLogin()
//                .loginPage("/login-page")
//                .defaultSuccessUrl("/")
//                .loginProcessingUrl("/login")
//                .failureUrl("/login-page")
//                .failureHandler(getFailureHandler())
//                .successHandler(getSuccessHandler())
//                .permitAll();
//
//        //로그아웃
//        http.logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/board/home");
//
//        //접근 거부 시 에러 페이지 설정
//        http.exceptionHandling()
//                .accessDeniedPage("/error/denied");
//
//        return http.build();
//    }
//}
