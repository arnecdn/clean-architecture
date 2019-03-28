package no.nilsen.cleana.bootstrap

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("no.nilsen.cleana")
open class SpringBootKotlinApplication
fun main(args: Array<String>) {
    runApplication<SpringBootKotlinApplication>(*args)
}
