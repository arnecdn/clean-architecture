package no.nilsen.cleana.application.selger.query

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.startsWith
import no.nilsen.cleana.domain.selger.Selger
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HentSelgerImplTest {

    val repo = SelgerQueryRepositoryMap()
    val hentAnsatte = HentSelgerImpl(repo)

    val HE_ISANN = "He Isann"
    val HA_DET = "Ha Det"


    @BeforeAll
    fun før() {
        repo.opprett(Selger(navn = HE_ISANN))
        repo.opprett(Selger(navn = HA_DET))
    }

    @Test
    fun skalHenteEnkeltAnsatt() {

        val hent = hentAnsatte.hent(1)
        assertThat(hent.navn, startsWith(HE_ISANN))
    }



    class SelgerQueryRepositoryMap : SelgerQueryReporitory {
        val ansatte: MutableMap<Int, Selger> = hashMapOf()

        fun opprett(selger: Selger) {
            val ny = Selger(ansatte.size + 1, selger.navn)
            ansatte.put(ny.id, ny)
        }


        override fun hent(id: Int): Selger {
            return ansatte.get(id)!!
        }

        override fun hentAlle(): List<Selger> {
            return ansatte.map { k -> k.value }
        }

    }
}