package org.hubson404.weather;

import lombok.RequiredArgsConstructor;
import org.hubson404.weather.security.SecurityUser;
import org.hubson404.weather.security.SecurityUserRepository;
import org.hubson404.weather.weather.OpenWeatherConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

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
