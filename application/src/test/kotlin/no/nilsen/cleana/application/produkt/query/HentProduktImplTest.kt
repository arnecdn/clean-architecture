package no.nilsen.cleana.application.produkt.query

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.hasSize
import com.natpryce.hamkrest.startsWith
import no.nilsen.cleana.ansatt.query.HentProduktImpl
import no.nilsen.cleana.ansatt.query.ProduktQueryReporitory
import no.nilsen.cleana.domain.produkt.Produkt
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.math.BigDecimal

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HentProduktImplTest {
    val repo = ProduktQueryRepositoryMap()
    val hentProdukte = HentProduktImpl(repo)

    val PRODUKT_1 = "Produkt 1"
    val PRODUKT_2 = "Produkt 2"


    @BeforeAll
    fun før() {
        repo.opprett(Produkt(beskrivelse = PRODUKT_1, pris = BigDecimal.TEN))
        repo.opprett(Produkt(beskrivelse = PRODUKT_2, pris = BigDecimal.ONE))
    }

    @Test
    fun skalHenteEnkeltProdukt() {

        val hent = hentProdukte.hent(1)
        assertThat(hent.beskrivelse, startsWith(PRODUKT_1))
    }

    @Test
    fun skalHenteAlle() {
        val hentAlle = hentProdukte.hentAlle()
        assertThat(hentAlle, hasSize(equalTo(2)))
    }


    class ProduktQueryRepositoryMap : ProduktQueryReporitory {
        val produkter: MutableMap<Int, Produkt> = hashMapOf()

        fun opprett(produkt: Produkt) {
            val ny = Produkt(produkter.size + 1, produkt.beskrivelse, produkt.pris)
            produkter.put(ny.id, ny)
        }


        override fun hent(id: Int): Produkt {
            return produkter.get(id)!!
        }

        override fun hentAlle(): List<Produkt> {
            return produkter.map { k -> k.value }
        }

    }
}