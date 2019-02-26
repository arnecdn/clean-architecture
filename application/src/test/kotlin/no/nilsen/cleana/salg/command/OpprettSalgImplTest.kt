package no.nilsen.cleana.salg.command

import no.nilsen.cleana.repository.SalgRepository
import no.nilsen.cleana.salg.Salg
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test
import java.util.Arrays.asList

internal class OpprettSalgImplTest {

    @Test
    fun skalOppretteEnkeltSalg() {

        val repo = object : SalgRepository {
            lateinit var salg : Salg

            override fun hentAlleSalg(): List<Salg> {
                return asList(salg)
            }

            override fun lagre(nyttSalg: Salg) {
                salg = nyttSalg
            }

        }
        val opprettSalg = OpprettSalgImpl(repo)
        val salg = OpprettSalgDto(1, 1, 1, 1)
        opprettSalg.opprett(salg)
        val alleSalg = repo.hentAlleSalg()
        assertTrue(alleSalg.isNotEmpty())
    }
}