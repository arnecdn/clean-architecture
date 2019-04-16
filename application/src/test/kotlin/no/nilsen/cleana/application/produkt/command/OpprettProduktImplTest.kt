package no.nilsen.cleana.application.produkt.command

import no.nilsen.cleana.domain.produkt.Produkt
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
        override fun endre(produkt: Produkt) {
            produkter.put(produkt.id, produkt)
        }

        override fun slett(produkt: Produkt) {
            produkter.remove(produkt.id)
        }

        var produkter: MutableMap<Int, Produkt> = hashMapOf()

        override fun opprett(produkt: Produkt) {
            val nytt = Produkt(produkter.size + 1, produkt.beskrivelse, produkt.pris)
            produkter.put(nytt.id, nytt)
        }

        fun hent(id: Int): Produkt? {
            return produkter.get(id)
        }

    }

}