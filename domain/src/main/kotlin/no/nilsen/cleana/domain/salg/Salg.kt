package no.nilsen.cleana.domain.salg

import no.nilsen.cleana.domain.kunde.Kunde
import no.nilsen.cleana.domain.produkt.Produkt
import no.nilsen.cleana.domain.selger.Selger
import java.math.BigDecimal

class Salg(val id: Int = 0, val antall: Long = 0, val selger: Selger = Selger(), val kunde: Kunde = Kunde(), val produkt: Produkt = Produkt()) {

    val erGyldigNyttSalg: Boolean get() = (antall > 0) && (selger.id > 0) && (kunde.id > 0) && (produkt.id > 0)

    val erGyldigOppdatering: Boolean get() = (id > 0) && (antall > 0) && (selger.id > 0) && (kunde.id > 0) && (produkt.id > 0)


    open fun totalPris(): BigDecimal {
        return produkt.pris ganger antall
    }

    infix fun BigDecimal.ganger (antall : Long) : BigDecimal {
        return produkt.pris.times(BigDecimal.valueOf(antall))
    }
}