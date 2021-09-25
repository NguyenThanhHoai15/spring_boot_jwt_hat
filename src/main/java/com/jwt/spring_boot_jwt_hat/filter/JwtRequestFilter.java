package com.jwt.spring_boot_jwt_hat.filter;

import com.jwt.spring_boot_jwt_hat.authen.UserPrincipal;
import com.jwt.spring_boot_jwt_hat.entity.Token;
import com.jwt.spring_boot_jwt_hat.service.TokenService;
import com.jwt.spring_boot_jwt_hat.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
@EnableAutoConfiguration
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService verificationTokenService;

    @Autowired
    private JwtUtil jwtUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        final String authorizationHeader
                = request.getHeader("Authorization");

        UserPrincipal user = null;
        Token token = null;


        if (StringUtils.hasText(authorizationHeader) &&
                authorizationHeader.startsWith("Token ")) {
            String jwt = authorizationHeader.substring(6);

            user = jwtUtil.getUserFromToken(jwt);

            token = verificationTokenService.findTokenById(jwt);
        }

        if (null != user && null != token && token.getTokenExDate().after(new Date())) {

            Set<GrantedAuthority> authorities = new HashSet<>();

            user.getAuthorities().forEach(
                    p -> authorities.add(new SimpleGrantedAuthority((String) p)));


            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(user, null, authorities);


            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

}
