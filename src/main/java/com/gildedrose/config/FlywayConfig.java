package com.gildedrose.config;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfig {

    @Bean
    public Flyway flyway() {
        Flyway flyway = Flyway.configure()
            .dataSource("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1", "sa", null)
            .load();
        flyway.migrate(); // Ensure migrations are run at startup
        return flyway;
    }
}
