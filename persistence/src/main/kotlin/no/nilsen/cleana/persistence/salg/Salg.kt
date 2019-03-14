package no.nilsen.cleana.persistence.salg

import no.nilsen.cleana.domain.ansatt.Ansatt
import no.nilsen.cleana.persistence.kunde.Kunde
import no.nilsen.cleana.persistence.produkt.Produkt
import java.math.BigDecimal

class Salg(val id: Int, val antall: Long, val totalPris: Int, val selger: Ansatt, val kunde: Kunde, val produkt: Produkt) {

    open fun totalPris(): BigDecimal {
        return produkt.pris.times(BigDecimal.valueOf(antall))
    }
}