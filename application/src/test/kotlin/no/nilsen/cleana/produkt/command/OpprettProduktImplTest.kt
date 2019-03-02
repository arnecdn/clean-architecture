package no.nilsen.cleana.produkt.command

import no.nilsen.cleana.ansatt.command.OpprettProduktDto
import no.nilsen.cleana.ansatt.command.OpprettProduktImpl
import no.nilsen.cleana.ansatt.command.ProduktCommandRepository
import no.nilsen.cleana.produkt.Produkt
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class OpprettProduktImplTest {
    val repo = ProduktCommandRepositoryMap()

    val opprettProdukt = OpprettProduktImpl(repo)

    @Test
    fun skalOppretteEnkeltKunde() {

        opprettProdukt.opprett(OpprettProduktDto(beskrivelse = "Produkt 1", pris = BigDecimal.TEN))
        val hentetKunde = repo.hent(1)
        kotlin.test.assertTrue(hentetKunde!!.id.equals(1))
    }

    class ProduktCommandRepositoryMap : ProduktCommandRepository {

        var produkter: MutableMap<Int, Produkt> = hashMapOf()

        override fun opprett(k: Produkt) {
            val nytt = Produkt(produkter.size + 1, k.beskrivelse, k.pris)
            produkter.put(nytt.id, nytt)
        }

        fun hent(id: Int): Produkt? {
            return produkter.get(id)
        }

    }

}