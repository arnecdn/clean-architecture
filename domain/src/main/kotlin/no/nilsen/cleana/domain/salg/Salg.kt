package no.nilsen.cleana.domain.salg

import no.nilsen.cleana.domain.ansatt.Ansatt
import no.nilsen.cleana.domain.kunde.Kunde
import no.nilsen.cleana.domain.produkt.Produkt
import java.math.BigDecimal

class Salg(val id: Int = 0, val antall: Long = 0, val selger: Ansatt = Ansatt(), val kunde: Kunde = Kunde(), val produkt: Produkt = Produkt()) {
    fun erGyldigNyttSalg(): Boolean {
        return (antall > 0) && (selger.id > 0) && (kunde.id > 0) && (produkt.id > 0)
    }

    fun erGyldigOppdatering(): Boolean {
        return (id > 0) && (antall > 0) && (selger.id > 0) && (kunde.id > 0) && (produkt.id > 0)
    }

    open fun totalPris(): BigDecimal {
        return produkt.pris.times(BigDecimal.valueOf(antall))
    }
}