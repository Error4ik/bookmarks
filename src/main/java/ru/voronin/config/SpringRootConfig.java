package ru.voronin.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Spring root config.
 *
 * @author Alexey Voronin.
 * @since 15.04.2018.
 */
@Configuration
@ComponentScan("ru.voronin")
@ImportResource("classpath*:*spring-context.xml")
public class SpringRootConfig {
}
