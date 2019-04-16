package no.nilsen.cleana.application.kunde.query

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import no.nilsen.cleana.domain.kunde.Kunde
import org.junit.jupiter.api.Test
import java.util.*

class HentKundeImplTest {

    val repo = KundeQueryRepositoryMap()

    val hentKunde = HentKundeImpl(repo)

    @Test
    fun skalHenteKunde() {
        repo.opprett(Kunde(navn = "Tom Tom"))
        repo.opprett(Kunde(navn = "Ben Ben"))

        val kundeDto = hentKunde.hent(1)
        assertThat(kundeDto.navn, equalTo("Tom Tom"))
    }


    class KundeQueryRepositoryMap : KundeQueryReporitory {

        val kunder = HashMap<String, Kunde>()

        fun opprett(kunde: Kunde) {
            val k = Kunde(kunder.size + 1, kunde.navn)

            kunder.put(k.id.toString(), k)
        }

        override fun hentAlle(): List<Kunde> {
            return kunder.map { k -> k.value }
        }

        override fun hent(id: Int): Kunde {
            return kunder.getOrDefault(id.toString(), Kunde(navn = ""))
        }

    }

}