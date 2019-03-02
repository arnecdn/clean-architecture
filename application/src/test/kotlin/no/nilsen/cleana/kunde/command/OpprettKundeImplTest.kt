package no.nilsen.cleana.kunde.command

import no.nilsen.cleana.ansatt.command.KundeCommandRepository
import no.nilsen.cleana.ansatt.command.OpprettKundeDto
import no.nilsen.cleana.ansatt.command.OpprettKundeImpl
import no.nilsen.cleana.kunde.Kunde
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