package no.nilsen.cleana.ansatt.command

import no.nilsen.cleana.ansatt.Ansatt
import org.junit.jupiter.api.Test

class OpprettAnsattImplTest {
    val repo = AnsattCommandRepositoryMap()
    val opprettAnsatt = OpprettAnsattImpl(repo)

    @Test
    fun skalOppretteAnsatt() {

        val ansattNavn = "Brei Flabb"

        opprettAnsatt.opprett(OpprettAnsattDto(ansattNavn))
        val hentetKunde = repo.hent(1)


        kotlin.test.assertTrue(hentetKunde.navn.contentEquals(ansattNavn))
    }


    class AnsattCommandRepositoryMap : AnsattCommandRepository {
        val ansatte: MutableMap<Int, Ansatt> = hashMapOf()

        override fun opprett(ansatt: Ansatt) {
            val ny = Ansatt(ansatte.size + 1, ansatt.navn)
            ansatte.put(ny.id, ny)
        }


        fun hent(id: Int): Ansatt {
            return ansatte.get(id)!!
        }

    }


}