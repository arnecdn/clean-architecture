package no.nilsen.cleana.api.salg

import com.natpryce.hamkrest.assertion.assertThat
import no.nilsen.cleana.api.Config
import no.nilsen.cleana.application.kunde.query.KundeQueryReporitory
import no.nilsen.cleana.application.salg.command.SalgCommandRepository
import no.nilsen.cleana.persistence.PersistenceConfig
import no.nilsen.cleana.persistence.salg.SalgCommandRepositoryH2Impl
import no.nilsen.cleana.persistence.salg.SalgCrudRepositoryH2
import no.nilsen.cleana.persistence.salg.SalgQueryRepositoryH2Impl
import no.nilsen.cleana.persistence.salg.SalgsHendelseCrudRepositoryH2
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.context.junit4.SpringRunner
import java.math.BigDecimal

@SpringBootTest(classes = arrayOf(PersistenceConfig::class,
        SalgMutationResolver::class,
        SalgQueryResolver::class,
        SalgCommandRepositoryH2Impl::class,
        SalgQueryRepositoryH2Impl::class))
@ExtendWith(SpringExtension::class)
@AutoConfigureDataJpa
@AutoConfigureTestDatabase
internal class SalgMutationResolverTest {
    @Autowired
    lateinit var salgMutationResolver: SalgMutationResolver

    @Autowired
    lateinit var salgQueryResolver: SalgQueryResolver

    @Test
    fun skalOpprettToSalg() {
        salgMutationResolver.opprettSalg(OpprettSalgView(antall = 1, totalPris = BigDecimal(20), kunde = 1, selger = 1, produkt = 1))
        salgMutationResolver.opprettSalg(OpprettSalgView(antall = 1, totalPris = BigDecimal(20), kunde = 1, selger = 1, produkt = 1))
        val salgsListe = salgQueryResolver.getSalgsListe()

        assertEquals( salgsListe.size, 2)
    }
}