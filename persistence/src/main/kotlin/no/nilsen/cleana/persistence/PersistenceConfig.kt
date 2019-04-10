package no.nilsen.cleana.persistence

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories


@Configuration
@EnableJpaRepositories(basePackages = arrayOf("no.nilsen.cleana.persistence"))
@EntityScan
open class PersistenceConfig {

}