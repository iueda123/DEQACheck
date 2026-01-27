package iu.SpringBoot.Vaadin.security;

import com.vaadin.flow.spring.security.VaadinWebSecurity;
import iu.SpringBoot.Vaadin.security.DebugAutoLoginFilter;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends VaadinWebSecurity {

    // Admin user credentials
    @Value("${app.security.admin.username:admin}")
    private String adminUsername;

    @Value("${app.security.admin.password:password}")
    private String adminPassword;

    @Value("${app.security.skip-login:false}")
    private boolean skipLogin;

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

    // Takamatsu user credentials
    @Value("${app.security.takamatsu.username:takamatsu}")
    private String takamatsuUsername;

    @Value("${app.security.takamatsu.password:takamatsu}")
    private String takamatsuPassword;

    // Saito user credentials
    @Value("${app.security.saito.username:saito}")
    private String saitoUsername;

    @Value("${app.security.saito.password:saito}")
    private String saitoPassword;

    // Takahashi user credentials
    @Value("${app.security.takahashi.username:takahashi}")
    private String takahashiUsername;

    @Value("${app.security.takahashi.password:takahashi}")
    private String takahashiPassword;

    // Shibukawa user credentials
    @Value("${app.security.shibukawa.username:shibukawa}")
    private String shibukwaUsername;

    @Value("${app.security.shibukawa.password:shibukawa}")
    private String shibukwaPassword;

    // Tamura user credentials
    @Value("${app.security.tamura.username:tamura}")
    private String tamuraUsername;

    @Value("${app.security.tamura.password:tamura}")
    private String tamuraPassword;

    // Etani user credentials
    @Value("${app.security.etani.username:etani}")
    private String etaniUsername;

    @Value("${app.security.etani.password:etani}")
    private String etaniPassword;

    // Local user credentials (for local server access)
    @Value("${app.security.local.username:local}")
    private String localUsername;

    @Value("${app.security.local.password:local}")
    private String localPassword;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Allow access to static resources
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/images/**", "/icons/**", "/styles/**").permitAll()
        );

        // Debug mode: auto-authenticate as admin when enabled
        http.addFilterBefore(debugAutoLoginFilter(userDetailsService()), UsernamePasswordAuthenticationFilter.class);

        super.configure(http);

        // Set login view
        setLoginView(http, LoginView.class);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // Admin user - full access (ADMIN, USER, GUEST roles)
        UserDetails adminUser = User.builder()
                .username(adminUsername)
                .password(passwordEncoder().encode(adminPassword))
                .roles("ADMIN", "USER", "GUEST")
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

        // Takamatsu user - same access as guest (GUEST role only)
        UserDetails takamatsuUser = User.builder()
                .username(takamatsuUsername)
                .password(passwordEncoder().encode(takamatsuPassword))
                .roles("GUEST")
                .build();

        // Saito user - same access as guest (GUEST role only)
        UserDetails saitoUser = User.builder()
                .username(saitoUsername)
                .password(passwordEncoder().encode(saitoPassword))
                .roles("GUEST")
                .build();

        // Takahashi user - same access as guest (GUEST role only)
        UserDetails takahashiUser = User.builder()
                .username(takahashiUsername)
                .password(passwordEncoder().encode(takahashiPassword))
                .roles("GUEST")
                .build();

        // Shibukawa user - same access as guest (GUEST role only)
        UserDetails shibukwaUser = User.builder()
                .username(shibukwaUsername)
                .password(passwordEncoder().encode(shibukwaPassword))
                .roles("GUEST")
                .build();

        // Tamura user - same access as guest (GUEST role only)
        UserDetails tamuraUser = User.builder()
                .username(tamuraUsername)
                .password(passwordEncoder().encode(tamuraPassword))
                .roles("GUEST")
                .build();

        // Etani user - same access as guest (GUEST role only)
        UserDetails etaniUser = User.builder()
                .username(etaniUsername)
                .password(passwordEncoder().encode(etaniPassword))
                .roles("GUEST")
                .build();

        // Local user - admin access for local server (ADMIN, USER, GUEST roles)
        UserDetails localUser = User.builder()
                .username(localUsername)
                .password(passwordEncoder().encode(localPassword))
                .roles("ADMIN", "USER", "GUEST")
                .build();

        return new InMemoryUserDetailsManager(adminUser, guestUser, uedaUser, takamatsuUser, saitoUser, takahashiUser, shibukwaUser, tamuraUser, etaniUser, localUser);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DebugAutoLoginFilter debugAutoLoginFilter(UserDetailsService userDetailsService) {
        return new DebugAutoLoginFilter(skipLogin, userDetailsService, adminUsername);
    }
}
