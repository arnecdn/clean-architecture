package no.nilsen.cleana

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class CleanaApplication

fun main(args: Array<String>) {
    SpringApplication.run(CleanaApplication::class.java, *args)
}

