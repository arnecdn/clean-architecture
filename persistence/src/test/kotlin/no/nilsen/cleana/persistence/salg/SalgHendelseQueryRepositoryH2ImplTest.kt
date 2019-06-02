package no.nilsen.cleana.persistence.salg

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import no.nilsen.cleana.domain.kunde.Kunde
import no.nilsen.cleana.domain.produkt.Produkt
import no.nilsen.cleana.domain.salg.Salg
import no.nilsen.cleana.domain.selger.Selger
import no.nilsen.cleana.persistence.PersistenceConfig
import no.nilsen.cleana.persistence.kunde.KundeCommandRepositoryH2Impl
import no.nilsen.cleana.persistence.produkt.ProduktCommandRepositoryH2Impl
import no.nilsen.cleana.persistence.selger.SelgerCommandRepositoryH2Impl
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.math.BigDecimal
import javax.transaction.Transactional

@ExtendWith(SpringExtension::class)
@DataJpaTest
@Transactional
@ContextConfiguration(classes = arrayOf(PersistenceConfig::class, KundeCommandRepositoryH2Impl::class, SelgerCommandRepositoryH2Impl::class, ProduktCommandRepositoryH2Impl::class,
        SalgCommandRepositoryH2Impl::class, SalgHendelseQueryRepositoryH2Impl::class))
open class SalgHendelseQueryRepositoryH2ImplTest {
    @Autowired
    lateinit var produktRepo: ProduktCommandRepositoryH2Impl

    @Autowired
    lateinit var kundeRepo: KundeCommandRepositoryH2Impl

    @Autowired
    lateinit var selgerRepo: SelgerCommandRepositoryH2Impl

    @Autowired
    lateinit var salgRepo: SalgCommandRepositoryH2Impl

    @Autowired
    lateinit var salgHendelseRepo: SalgHendelseQueryRepositoryH2Impl

    @BeforeEach
    fun førTest() {
        val kunde1 = Kunde(navn = "Kunde Kundesen")
        val selger1 = Selger(navn = "Selger Salgsen")
        val produkt1 = Produkt(beskrivelse = "Salgsprodukt 1", pris = BigDecimal.TEN)
        val produkt2 = Produkt(beskrivelse = "Salgsprodukt 2", pris = BigDecimal.TEN)
        val produkt3 = Produkt(beskrivelse = "Salgsprodukt 3", pris = BigDecimal.TEN)

        kundeRepo.opprett(kunde1)
        selgerRepo.opprett(selger1)
        produktRepo.opprett(produkt1)
        produktRepo.opprett(produkt2)
        produktRepo.opprett(produkt3)

        //Nye
        salgRepo.opprett(Salg(antall = 2, selger = selger1, kunde = kunde1, produkt = produkt1))
        salgRepo.opprett(Salg(antall = 2, selger = selger1, kunde = kunde1, produkt = produkt2))
        salgRepo.opprett(Salg(antall = 2, selger = selger1, kunde = kunde1, produkt = produkt3))
        salgRepo.opprett(Salg(antall = 2, selger = selger1, kunde = kunde1, produkt = produkt1))
        salgRepo.opprett(Salg(antall = 2, selger = selger1, kunde = kunde1, produkt = produkt2))
        salgRepo.opprett(Salg(antall = 2, selger = selger1, kunde = kunde1, produkt = produkt3))


        //Endringer
        salgRepo.endre(Salg(id = 1, antall = 1, selger = selger1, kunde = kunde1, produkt = produkt1))
        salgRepo.endre(Salg(id = 2, antall = 1, selger = selger1, kunde = kunde1, produkt = produkt2))
        salgRepo.endre(Salg(id = 3, antall = 1, selger = selger1, kunde = kunde1, produkt = produkt3))
        salgRepo.endre(Salg(id = 4, antall = 1, selger = selger1, kunde = kunde1, produkt = produkt1))
        salgRepo.endre(Salg(id = 5, antall = 1, selger = selger1, kunde = kunde1, produkt = produkt2))
        salgRepo.endre(Salg(id = 6, antall = 1, selger = selger1, kunde = kunde1, produkt = produkt3))


        //Slette
        salgRepo.slett(Salg(id = 1))
    }


    @Test
    fun skalHenteAlleSalgsHendelser() {
        val salgHendelser = salgHendelseRepo.hent(0, 20)
        assertThat(salgHendelser.size, equalTo(13))
    }

    @Test
    fun skal5og5SalgsHendelser() {
        val foerste5 = salgHendelseRepo.hent(antall = 5)
        val andre5 = salgHendelseRepo.hent(foerste5.get(foerste5.size - 1).id, 5)
        val tredje5 = salgHendelseRepo.hent(andre5.get(andre5.size - 1).id, 5)

        assertThat(foerste5.size, equalTo(5))
        assertThat(foerste5.size, equalTo(5))
        assertThat(tredje5.size, equalTo(3))
    }
}