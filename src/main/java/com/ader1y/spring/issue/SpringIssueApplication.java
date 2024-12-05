package com.ader1y.spring.issue;

import org.slf4j.Logger;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.web.reactive.config.EnableWebFlux;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * <pre>
 * This project reproduces <a href="https://github.com/spring-projects/spring-framework/issues/33932">spring-framework issue: #33932</a> problem.
 * </pre>
 */
@EnableWebFlux
@SpringBootApplication
public class SpringIssueApplication {

    private static final Logger LOG = getLogger(SpringIssueApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringIssueApplication.class, args);
    }

}
