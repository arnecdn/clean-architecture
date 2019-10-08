package no.nilsen.cleana.api.salg

import no.nilsen.cleana.persistence.PersistenceConfig
import no.nilsen.cleana.persistence.salg.SalgCommandRepositoryH2Impl
import no.nilsen.cleana.persistence.salg.SalgQueryRepositoryH2Impl
import org.amshove.kluent.`should be`
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
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

    inner class `skal` {
        @Test
        fun `opprette 2 salg`() {
            salgMutationResolver.opprettSalg(OpprettSalgView(antall = 1, totalPris = BigDecimal(20), kunde = 1, selger = 1, produkt = 1))
            salgMutationResolver.opprettSalg(OpprettSalgView(antall = 1, totalPris = BigDecimal(20), kunde = 1, selger = 1, produkt = 1))


            salgQueryResolver.getSalgsListe().size `should be` 2
        }
    }
}