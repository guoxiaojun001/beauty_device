package com.machine.manager.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class JwtAuthTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    private UserDetailsService userDetailServiceImpl;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //http  请求头中的token
        String token = request.getHeader(jwtTokenUtils.getHeader());
        if (token!=null && token.length()>0) {
            String username = jwtTokenUtils.getUsernameFromToken(token);
            System.out.print("doFilterInternal username:" + username);
            Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
            if (username != null && authentication==null) {
                UserDetails userDetails = userDetailServiceImpl.loadUserByUsername(username);
                if (jwtTokenUtils.validateToken(token, userDetails)) {
                    //给使用该JWT令牌的用户进行授权
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    //设置用户身份授权
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }else {

                System.out.print("doFilterInternal getAuthorities:" + authentication.getAuthorities());
            }
        }
        filterChain.doFilter(request, response);
    }
}
