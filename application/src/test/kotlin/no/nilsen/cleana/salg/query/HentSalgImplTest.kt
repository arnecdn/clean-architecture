package no.nilsen.cleana.salg.query

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.hasSize
import no.nilsen.cleana.ansatt.Ansatt
import no.nilsen.cleana.kunde.Kunde
import no.nilsen.cleana.produkt.Produkt
import no.nilsen.cleana.salg.Salg
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.math.BigDecimal
import kotlin.test.assertTrue

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HentSalgImplTest {
    val repo = SalgQueryRepositoryMap()
    val henSalg = HentSalgImpl(repo)

    @BeforeAll
    fun før() {
        val laserPistol = Produkt(id = 1, beskrivelse = "Laser pistol", pris = BigDecimal.TEN)

        val willWheaton = Kunde(id = 1, navn = "Will Wheaton")
        val lukeSkywalker = Kunde(id = 2, navn = "Luke Skywalker")
        val hanSolo = Ansatt(id = 1, navn = "Han Solo")
        val chewebacca = Ansatt(id = 2, navn = "Chewebacca")

        repo.opprett(Salg(antall = 1, selger = hanSolo, kunde = willWheaton, produkt = laserPistol))
        repo.opprett(Salg(antall = 2, selger = hanSolo, kunde = lukeSkywalker, produkt = laserPistol))
        repo.opprett(Salg(antall = 5, selger = chewebacca, kunde = lukeSkywalker, produkt = laserPistol))
        repo.opprett(Salg(antall = 10, selger = chewebacca, kunde = willWheaton, produkt = laserPistol))
    }


    @Test
    fun skalHenteEnkeltSalg() {
        val salgDto = henSalg.hent(1)
        assertTrue(salgDto!!.id.equals(1))
    }

    @Test
    fun skalHenteEnkeltSalgForKunde() {
        val kundeSalg = henSalg.hentSalgPerKunde(1)
        assertThat(kundeSalg, hasSize(equalTo(2)))
    }

    @Test
    fun skalHenteEnkeltSalgForAnsatt() {
        val ansattSalg = henSalg.hentSalgPerAnsatt(1)
        assertThat(ansattSalg, hasSize(equalTo(2)))
    }

    @Test
    fun skalHenteAlleSalg() {
        val alle = henSalg.hentAlle()
        assertThat(alle, hasSize(equalTo(4)))
    }


    class SalgQueryRepositoryMap : SalgQueryRepository {
        override fun hentSalgPerKunde(kundeId: Int): List<Salg> {
            return salgMap.filter { s -> s.value.kunde.id.equals(kundeId) }.map { k -> k.value }
        }

        override fun hentSalgPerAnsatt(ansattId: Int): List<Salg> {
            return salgMap.filter { s -> s.value.selger.id.equals(ansattId) }.map { k -> k.value }
        }

        override fun hentAlleSalg(): List<Salg> {
            return salgMap.map { k -> k.value }
        }

        val salgMap: MutableMap<Int, Salg> = hashMapOf()

        fun opprett(nyttSalg: Salg) {
            val s = Salg(salgMap.size + 1, nyttSalg.antall, nyttSalg.selger, nyttSalg.kunde, nyttSalg.produkt)
            salgMap.put(s.id, s)
        }

        override fun hent(id: Int): Salg? {
            return salgMap.get(id)
        }
    }
}