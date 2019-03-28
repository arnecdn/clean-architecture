package no.nilsen.cleana.application.ansatt.command

import no.nilsen.cleana.domain.ansatt.Ansatt
import org.junit.jupiter.api.Test

class OpprettOpprettAnsattCommandImplTest {
    val repo = AnsattCommandRepositoryMap()
    val opprettAnsatt = OpprettOpprettAnsattCommandImpl(repo)

    @Test
    fun skalOppretteAnsatt() {

        val ansattNavn = "Brei Flabb"

        opprettAnsatt.opprett(OpprettAnsattDto(ansattNavn))
        val hentetKunde = repo.hent(1)


        kotlin.test.assertTrue(hentetKunde.navn.contentEquals(ansattNavn))
    }


    class AnsattCommandRepositoryMap : AnsattCommandRepository {
        override fun endre(ansatt: Ansatt) {
            val ny = Ansatt(ansatt.id, ansatt.navn)
            ansatte.put(ny.id, ny)
        }

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