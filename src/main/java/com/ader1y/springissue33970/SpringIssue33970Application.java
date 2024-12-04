package com.ader1y.springissue33970;

import org.slf4j.Logger;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurationSupport;
import org.springframework.web.reactive.socket.server.support.HandshakeWebSocketService;
import org.springframework.web.reactive.socket.server.upgrade.StandardWebSocketUpgradeStrategy;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * <pre>
 * This project reproduces <a href="https://github.com/spring-projects/spring-framework/issues/33970">spring-framework issue: #33970</a> problem.
 * I have rewrite {@link org.springframework.web.reactive.socket.server.support.HandshakeWebSocketService} on project, it comments some codes.
 * </pre>
 * @see org.springframework.web.reactive.socket.server.support.HandshakeWebSocketService#initUpgradeStrategy
 */
@EnableWebFlux
@SpringBootApplication
public class SpringIssue33970Application {

    private static final Logger LOG = getLogger(SpringIssue33970Application.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringIssue33970Application.class, args);
    }

}
