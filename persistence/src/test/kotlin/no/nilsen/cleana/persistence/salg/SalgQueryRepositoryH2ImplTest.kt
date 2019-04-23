package no.nilsen.cleana.persistence.salg

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.lessThanOrEqualTo
import no.nilsen.cleana.persistence.PersistenceConfig
import no.nilsen.cleana.persistence.selger.SelgerEntitet
import no.nilsen.cleana.persistence.kunde.KundeEntitet
import no.nilsen.cleana.persistence.produkt.ProduktEntitet
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.math.BigDecimal
import javax.transaction.Transactional


@ExtendWith(SpringExtension::class)
@DataJpaTest
@Transactional
@ContextConfiguration(classes = arrayOf(PersistenceConfig::class, SalgQueryRepositoryH2Impl::class))
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
open class SalgQueryRepositoryH2ImplTest {

    @Autowired
    lateinit var salgRepo: SalgQueryRepositoryH2Impl


    @BeforeEach
    fun førTest() {
        salgRepo.kundeRepo.save(KundeEntitet(navn = "Kunde Kundesen"))
        salgRepo.selgerRepo.save(SelgerEntitet(navn = "Selger Salgsen"))
        salgRepo.produktRepo.save(ProduktEntitet(beskrivelse = "Salgsprodukt", pris = BigDecimal.TEN))
        salgRepo.salgRepo.save(SalgEntitet(antall = 2, totalPris = BigDecimal.TEN, selger = 1, kunde = 1, produkt = 1))
    }

    @Test
    fun skalHenteEtSalg() {
        val salg = salgRepo.hent(1)
        assertThat(salg!!.id, equalTo(1))
    }

    @Test
    fun skalHenteSalgPrKunde() {
        val salg = salgRepo.hentSalgPerKunde(1).get(0)
        assertThat(salg!!.kunde.navn, equalTo("Kunde Kundesen"))
    }

    @Test
    fun skalHenteSalgPrAnsatt() {
        val salg = salgRepo.hentSalgPerAnsatt(1).get(0)
        assertThat(salg!!.selger.navn, equalTo("Selger Salgsen"))
    }

    @Test
    fun skalHenteAlleSalg() {
        val salg = salgRepo.hentAlleSalg()
        assertThat(salg!!.size, lessThanOrEqualTo(1))
    }
}