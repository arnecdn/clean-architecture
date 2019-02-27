package no.nilsen.cleana.salg.command

import no.nilsen.cleana.salg.Salg
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test
import java.util.Arrays.asList

internal class OpprettSalgImplTest {

    @Test
    fun skalOppretteEnkeltSalg() {

        val repo = object : SalgCommandRepository {
            lateinit var salg : Salg

            override fun opprett(nyttSalg: Salg) {
                salg = nyttSalg
            }

            fun hent(): Salg{
                return salg
            }

        }
        val opprettSalg = OpprettSalgImpl(repo)
        val salg = OpprettSalgDto(1, 1, 1, 1)
        opprettSalg.opprett(salg)
        val opprettetSalg = repo.hent()
        assertTrue(opprettetSalg.antall.equals(1))
    }
}