package iu.SpringBoot.Vaadin.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Debug-only filter that can auto-authenticate the admin user to skip the login form.
 */
public class DebugAutoLoginFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(DebugAutoLoginFilter.class);

    private final boolean skipLogin;
    private final UserDetailsService userDetailsService;
    private final String adminUsername;

    public DebugAutoLoginFilter(boolean skipLogin, UserDetailsService userDetailsService, String adminUsername) {
        this.skipLogin = skipLogin;
        this.userDetailsService = userDetailsService;
        this.adminUsername = adminUsername;

        if (skipLogin) {
            log.warn("Debug auto-login is ENABLED. All requests will be authenticated as admin ({}).", adminUsername);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        // Fast exit when the debug mode is disabled
        return !skipLogin;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails adminUser = userDetailsService.loadUserByUsername(adminUsername);
            UsernamePasswordAuthenticationToken authentication =
                    UsernamePasswordAuthenticationToken.authenticated(
                            adminUser, adminUser.getPassword(), adminUser.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
