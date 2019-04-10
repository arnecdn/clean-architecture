package no.nilsen.cleana.presentation.salg

import com.fasterxml.jackson.annotation.JsonCreator
import no.nilsen.cleana.presentation.ansatt.HentAnsattView
import no.nilsen.cleana.presentation.kunde.HentKundeView
import no.nilsen.cleana.presentation.produkt.HentProduktView
import java.math.BigDecimal

data class HentSalgView @JsonCreator constructor(val id: Int = 0, val antall: Long = 0, val totalPris: BigDecimal = BigDecimal.ZERO, val selger: HentAnsattView, val kunde: HentKundeView, val produkt: HentProduktView) {

}
