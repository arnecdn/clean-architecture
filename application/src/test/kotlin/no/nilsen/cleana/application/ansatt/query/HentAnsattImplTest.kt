package no.nilsen.cleana.application.ansatt.query

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.hasSize
import com.natpryce.hamkrest.startsWith
import no.nilsen.cleana.domain.ansatt.Ansatt
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HentAnsattImplTest {

    val repo = AnsattQueryRepositoryMap()
    val hentAnsatte = HentAnsattImpl(repo)

    val HE_ISANN = "He Isann"
    val HA_DET = "Ha Det"


    @BeforeAll
    fun før() {
        repo.opprett(Ansatt(navn = HE_ISANN))
        repo.opprett(Ansatt(navn = HA_DET))
    }

    @Test
    fun skalHenteEnkeltAnsatt() {

        val hent = hentAnsatte.hent(1)
        assertThat(hent.navn, startsWith(HE_ISANN))
    }

    @Test
    fun skalHenteAlle() {
        val hentAlle = hentAnsatte.hentAlle()
        assertThat(hentAlle, hasSize(equalTo(2)))
    }


    class AnsattQueryRepositoryMap : AnsattQueryReporitory {
        val ansatte: MutableMap<Int, Ansatt> = hashMapOf()

        fun opprett(ansatt: Ansatt) {
            val ny = Ansatt(ansatte.size + 1, ansatt.navn)
            ansatte.put(ny.id, ny)
        }


        override fun hent(id: Int): Ansatt {
            return ansatte.get(id)!!
        }

        override fun hentAlle(): List<Ansatt> {
            return ansatte.map { k -> k.value }
        }

    }
}