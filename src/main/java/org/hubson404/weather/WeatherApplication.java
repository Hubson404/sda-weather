package org.hubson404.weather;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hubson404.weather.security.SecurityUser;
import org.hubson404.weather.security.SecurityUserRepository;
import org.hubson404.weather.weather.OpenWeatherConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Slf4j
@EnableSwagger2
@EnableScheduling
@EnableJpaAuditing
@SpringBootApplication
@RequiredArgsConstructor
@EnableConfigurationProperties(OpenWeatherConfig.class)
//@ConfigurationPropertiesScan // przesszukuje cały contekst springa w poszukiwaniu klas oznaczonych @Congiguration
public class WeatherApplication implements CommandLineRunner {

    private final SecurityUserRepository securityUserRepository;
    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(WeatherApplication.class, args);
    }

    @Scheduled(cron = "0 */2 * * * *") //fixedRate = 2000
    public void generateReport(){
        log.info("At this point, we have {} users", securityUserRepository.count());
    }

    @Override
    public void run(String... args) throws Exception {

        if (securityUserRepository.findAll().size() == 0) {

            SecurityUser hubson = new SecurityUser();
            hubson.setUsername("hubson");
            hubson.setPassword(passwordEncoder.encode("hubson1"));
            hubson.setAuthorities(Collections.singletonList(() -> "ROLE_USER"));
            securityUserRepository.save(hubson);

            SecurityUser admin = new SecurityUser();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin1"));
            admin.setAuthorities(Collections.singletonList(() -> "ROLE_ADMIN"));
            securityUserRepository.save(admin);
        }
    }
}
