package magic_api.magic.security;

import magic_api.magic.services.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthenticationFilter extends BasicAuthenticationFilter implements JWTAuthenticationFilterInterface {

    private final JWTUtil jwtUtil;
    private final UserService userService;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, UserService userService) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, javax.servlet.ServletException {

        String token = request.getHeader("Authorization");

        if (token != null && jwtUtil.validateToken(token)) {
            String username = jwtUtil.getUsernameFromToken(token);
            var userDetails = userService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        chain.doFilter(request, response);
    }
}
