package no.nilsen.cleana.persistence.ansatt

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@Configuration

@EnableJpaRepositories(basePackages = arrayOf("no.nilsen.cleana.ansatt"))
@PropertySource("persistence.properties")
@EnableTransactionManagement
open class PersistenceConfig {

    @Autowired
    lateinit var env: Environment;

    @Bean
    open fun dataSource() : DataSource {
        val dataSource = DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"))
        dataSource.setUrl(env.getProperty("jdbc.url"))
        dataSource.setUsername(env.getProperty("jdbc.user"))
        dataSource.setPassword(env.getProperty("jdbc.pass"))

        return dataSource;
    }

    // configure entityManagerFactory

    // configure transactionManager

    // configure additional Hibernate Properties
}