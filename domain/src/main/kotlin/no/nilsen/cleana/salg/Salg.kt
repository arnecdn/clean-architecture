package no.nilsen.cleana.salg

import no.nilsen.cleana.domain.ansatt.Ansatt
import no.nilsen.cleana.kunde.Kunde
import no.nilsen.cleana.produkt.Produkt
import java.math.BigDecimal

class Salg(val id: Int = 0, val antall: Long = 0, val selger: Ansatt=Ansatt(), val kunde: Kunde=Kunde(), val produkt: Produkt=Produkt()) {

    open fun totalPris(): BigDecimal {
        return produkt.pris.times(BigDecimal.valueOf(antall))
    }
}