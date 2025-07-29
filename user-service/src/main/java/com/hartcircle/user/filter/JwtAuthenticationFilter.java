package com.hartcircle.user.filter;


import com.hartcircle.user.repo.UserRepository;
import com.hartcircle.user.service.JwtClient;
import com.hartcircle.user.entity.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;
import java.util.Collections;

//A custom filter that runs for every HTTP request.Read the JWT from Authorization header and Call JWTClient.Run before any controller



@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter { //Ensures this filter is executed only once per HTTP request.

    @Autowired
    private JwtClient jwtClient;                    //handles token creation, parsing, and validation

    @Autowired
    private UserRepository userRepository;          //fetch user info from the database using NIC

    //This method  every HTTP request to apply JWT validation logic
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");//Extract Authorization Header
        final String token;
        final String nic;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("⛔ No valid Authorization header found!");
            filterChain.doFilter(request, response);
            return;
        }

        token = authHeader.substring(7);//remove Bearer.  extract the raw JWT token.
        nic = jwtClient.extractNic(token);

        if (nic != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            User user = userRepository.findByNic(nic).orElse(null);
//Set Authentication in Security Context
            if (user != null && jwtClient.validateToken(token, nic)) {
                UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                        user.getNic(),
                        user.getPassword(),
                        Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
                );

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}

