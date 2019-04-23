package no.nilsen.cleana.application.selger.command

import no.nilsen.cleana.domain.selger.Selger
import org.junit.jupiter.api.Test

class OpprettOpprettSelgerCommandImplTest {
    val repo = SelgerCommandRepositoryMap()
    val opprettAnsatt = OpprettOpprettSelgerCommandImpl(repo)

    @Test
    fun skalOppretteAnsatt() {

        val ansattNavn = "Brei Flabb"

        opprettAnsatt.opprett(OpprettSelgerDto(ansattNavn))
        val hentetKunde = repo.hent(1)


        kotlin.test.assertTrue(hentetKunde.navn.contentEquals(ansattNavn))
    }


    class SelgerCommandRepositoryMap : SelgerCommandRepository {
        override fun slett(selgerTilSletting: Selger) {
            ansatte.remove(selgerTilSletting.id)
        }

        override fun endre(oppdatertSelger: Selger) {
            val ny = Selger(oppdatertSelger.id, oppdatertSelger.navn)
            ansatte.put(ny.id, ny)
        }

        val ansatte: MutableMap<Int, Selger> = hashMapOf()

        override fun opprett(nySelger: Selger) {
            val ny = Selger(ansatte.size + 1, nySelger.navn)
            ansatte.put(ny.id, ny)
        }


        fun hent(id: Int): Selger {
            return ansatte.get(id)!!
        }

    }


}