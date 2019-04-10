package no.nilsen.cleana.application.salg.command

import no.nilsen.cleana.domain.salg.Salg
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class OpprettSalgImplTest {
    val repo = SalgCommandRepositoryMap()
    val opprettSalg = OpprettSalgImpl(repo)


    @Test
    fun skalOppretteEnkeltSalg() {
        val salg = OpprettSalgDto(1, 1, 1, 1)
        opprettSalg.opprett(salg)

        val opprettetSalg = repo.hent(1)
        assertTrue(opprettetSalg!!.id.equals(1))
    }

    class SalgCommandRepositoryMap : SalgCommandRepository {
        override fun endre(salg: Salg) {
            salgMap.put(salg.id, salg)
        }

        override fun slett(salg: Salg) {
            salgMap.remove(salg.id)
        }

        val salgMap: MutableMap<Int, Salg> = hashMapOf()

        override fun opprett(nyttSalg: Salg) {
            val s = Salg(salgMap.size + 1, nyttSalg.antall, nyttSalg.selger, nyttSalg.kunde, nyttSalg.produkt)
            salgMap.put(s.id, s)
        }

        fun hent(id: Int): Salg? {
            return salgMap.get(id)
        }
    }
}