package no.nilsen.cleana.application.kunde.command

import no.nilsen.cleana.domain.kunde.Kunde
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

internal class OpprettKundeImplTest {
    val repo = KundeCommandRepositoryMap()

    val opprettKunde = OpprettKundeImpl(repo)

    @Test
    fun skalOppretteEnkeltKunde() {

        val kundeNavn = "Brei Flabb"

        opprettKunde.opprett(OpprettKundeDto(kundeNavn))
        val hentetKunde = repo.hent(1)


        assertTrue(hentetKunde!!.navn.contentEquals(kundeNavn))
    }

    class KundeCommandRepositoryMap : KundeCommandRepository {
        override fun slett(kunde: Kunde) {
            kunder.remove(kunde.id)
        }

        override fun endre(kunde: Kunde) {
            val endretKunde = Kunde(kunde.id, kunde.navn)
            kunder.put(endretKunde.id, endretKunde)

        }

        var kunder: MutableMap<Int, Kunde> = hashMapOf()

        override fun opprett(k: Kunde) {
            val nyKunde = Kunde(kunder.size + 1, k.navn)
            kunder.put(nyKunde.id, nyKunde)
        }

        fun hent(id: Int): Kunde? {
            return kunder.get(id)
        }

    }

}