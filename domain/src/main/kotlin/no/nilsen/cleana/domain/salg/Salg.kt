package no.nilsen.cleana.domain.salg

import no.nilsen.cleana.domain.ansatt.Ansatt
import no.nilsen.cleana.domain.kunde.Kunde
import no.nilsen.cleana.domain.produkt.Produkt
import java.math.BigDecimal

class Salg(val id: Int = 0, val antall: Long = 0, val selger: Ansatt, val kunde: Kunde, val produkt: Produkt) {

    open fun totalPris(): BigDecimal {
        return produkt.pris.times(BigDecimal.valueOf(antall))
    }
}