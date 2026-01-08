package iu.SpringBoot.Vaadin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner logLoginBypassFlag(@Value("${app.security.skip-login:false}") boolean skipLogin) {
        return args -> {
            if (skipLogin) {
                log.warn("app.security.skip-login is ENABLED: login page is skipped and admin is auto-authenticated.");
            } else {
                log.info("app.security.skip-login is disabled (normal login flow).");
            }
        };
    }
}
