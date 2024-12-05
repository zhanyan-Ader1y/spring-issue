package com.ader1y.spring.issue;

import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * <pre>
 * This project reproduces <a href="https://github.com/spring-projects/spring-framework/issues/33970">spring-framework issue: #33970</a> problem.
 * </pre>
 * @see
 */
@EnableWebFlux
@SpringBootApplication
public class SpringIssueApplication {

    private static final Logger LOG = getLogger(SpringIssueApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringIssueApplication.class, args);
    }

}
