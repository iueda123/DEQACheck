package iu.SpringBoot.Vaadin.security;

import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends VaadinWebSecurity {

    // Admin user credentials
    @Value("${app.security.admin.username:admin}")
    private String adminUsername;

    @Value("${app.security.admin.password:password}")
    private String adminPassword;

    // Guest user credentials
    @Value("${app.security.guest.username:guest}")
    private String guestUsername;

    @Value("${app.security.guest.password:guest}")
    private String guestPassword;

    // Ueda user credentials
    @Value("${app.security.ueda.username:ueda}")
    private String uedaUsername;

    @Value("${app.security.ueda.password:ueda}")
    private String uedaPassword;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Allow access to static resources
        http.authorizeHttpRequests(auth -> auth
            .requestMatchers("/images/**", "/icons/**", "/styles/**").permitAll()
        );

        super.configure(http);

        // Set login view
        setLoginView(http, LoginView.class);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // Admin user - full access (USER role)
        UserDetails adminUser = User.builder()
            .username(adminUsername)
            .password(passwordEncoder().encode(adminPassword))
            .roles("USER", "GUEST")
            .build();

        // Guest user - limited access (GUEST role only)
        UserDetails guestUser = User.builder()
            .username(guestUsername)
            .password(passwordEncoder().encode(guestPassword))
            .roles("GUEST")
            .build();

        // Ueda user - same access as guest (GUEST role only)
        UserDetails uedaUser = User.builder()
            .username(uedaUsername)
            .password(passwordEncoder().encode(uedaPassword))
            .roles("GUEST")
            .build();

        return new InMemoryUserDetailsManager(adminUser, guestUser, uedaUser);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
