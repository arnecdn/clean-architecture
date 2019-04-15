package no.nilsen.cleana.api.salg

import com.fasterxml.jackson.annotation.JsonCreator
import no.nilsen.cleana.api.ansatt.HentAnsattView
import no.nilsen.cleana.api.kunde.HentKundeView
import no.nilsen.cleana.api.produkt.HentProduktView
import java.math.BigDecimal

data class HentSalgView @JsonCreator constructor(val id: Int = 0, val antall: Long = 0, val totalPris: BigDecimal = BigDecimal.ZERO, val selger: HentAnsattView, val kunde: HentKundeView, val produkt: HentProduktView) {

}
