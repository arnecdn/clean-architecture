package no.nilsen.cleana.application.ansatt.query

import no.nilsen.cleana.domain.ansatt.Ansatt
import no.nilsen.cleana.kunde.Kunde
import no.nilsen.cleana.produkt.Produkt
import java.math.BigDecimal

data class HentSalgView(val id: Int = 0, val antall: Long = 0, val totalPris: BigDecimal = BigDecimal.ZERO, val selger: Ansatt, val kunde: Kunde, val produkt: Produkt) {
}
