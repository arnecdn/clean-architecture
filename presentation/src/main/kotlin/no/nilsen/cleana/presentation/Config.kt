package no.nilsen.cleana.presentation

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc


@Configuration
@EnableWebMvc
@ComponentScan("no.nilsen.cleana.presentation")
open class Config {
}