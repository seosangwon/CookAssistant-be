package com.example.cookassistant.security.filter;

import com.example.cookassistant.domain.user.MemberContext;
import com.example.cookassistant.domain.user.User;
import com.example.cookassistant.domain.user.UserService;
import com.example.cookassistant.security.jwt.JwtProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final UserService userService;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String bearerToken = request.getHeader("Authorization");


        if (bearerToken != null) {
            String token = bearerToken.substring("Bearer ".length());

            //1차 체크 (토큰 정보가 변조되지 않았는지 확인)
            if (jwtProvider.verify(token)) {
                Map<String, Object> claims = jwtProvider.getClaims(token);

                User user = userService.getByUserEmail_cached((String) claims.get("email"));
                log.info("cache로부터 유저 찾아오기"+user.getEmail());

                //2차체크 DB에 있는 accessToken이랑 일치하는지 확인 (화이트 리스트 체크 방법)
                if (userService.verifyWithWhiteList(user, token)) {
                    log.info("화이트리스트2차체크 실행 ");
                    forceAuthentication(user);
                }




            }


        }
        filterChain.doFilter(request, response);
    }

    private void forceAuthentication(User member) {
        MemberContext memberContext = new MemberContext(member);

        UsernamePasswordAuthenticationToken authentication =
                UsernamePasswordAuthenticationToken.authenticated(
                        memberContext,
                        null,
                        member.getAuthorities()
                );

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
    }


}
