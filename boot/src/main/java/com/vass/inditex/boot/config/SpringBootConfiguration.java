package com.vass.inditex.boot.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("com.vass.inditex")
@EntityScan("com.vass.inditex")
@EnableJpaAuditing
@EnableJpaRepositories("com.vass.inditex.infrastructure.adapter")
public class SpringBootConfiguration {}
