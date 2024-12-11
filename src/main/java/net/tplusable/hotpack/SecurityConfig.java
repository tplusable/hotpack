package net.tplusable.hotpack;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// 스프링의 환경설정 파일을 의미
@Configuration
// 모든 요청 URL이 스프링 시큐리티의 제어를 받도록 함
// 모든 요청 URL에 SecurityFilterChain 클래스가 필터로 적용되어 URL로 특별한 설정을 할 수 있음
@EnableWebSecurity
public class SecurityConfig {

    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 인증되지 않은 모든 페이지("/**")의 요청을 허용 -> 로그인하지 않아도 모든 페이지에 접근 가능
        http
                .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                        .requestMatchers(new AntPathRequestMatcher("/**")).permitAll())
                .headers((headers) -> headers
                        // X-Frame-Options 헤더: 클랙재킹 공격을 막기위해 사용
                        // 클랙재킹: 사용자의 의도와 다른 작업이 수행되도록 속이는 보안 공격 기술
                        // SAMEORIGIN: 헤더값으로 SAMEORIGIN을 설정하면 프레임에 포함된 웹 페이지가 동일한 사이트에서 제공할때에만 사용이 허락됨
                        .addHeaderWriter(new XFrameOptionsHeaderWriter(
                                XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
                ;
        return http.build();
    }

    // PasswordEncoder 객체를 빈으로 등록
    // BCryptPasswordEncoder 클래스로 BCrypt 해시함수를 사용해 비밀번호 암호화하여 저장
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
