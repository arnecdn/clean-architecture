package no.nilsen.cleana.kunde.command

import no.nilsen.cleana.ansatt.command.KundeCommandRepository
import no.nilsen.cleana.ansatt.command.OpprettKunde
import no.nilsen.cleana.ansatt.command.OpprettKundeDto
import no.nilsen.cleana.ansatt.command.OpprettKundeImpl
import no.nilsen.cleana.kunde.Kunde
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

internal class OpprettKundeImplTest {
    val repo =TestKundeCommandRepo()

    class TestKundeCommandRepo : KundeCommandRepository {
        public lateinit var kunde: Kunde

        override fun opprett(k: Kunde) {
            kunde = k
        }

        fun hent(): Kunde{
            return kunde
        }

    }
    val opprettKunde: OpprettKunde = OpprettKundeImpl(repo)


    @Test
    fun skalOppretteEnkeltKunde() {

        val kundeNavn = "Brei Flabb"

        opprettKunde.opprett(OpprettKundeDto(kundeNavn))
        val hentetKunde = repo.hent()


        assertTrue(hentetKunde.navn.contentEquals(kundeNavn))
    }
}