package no.nilsen.cleana.api.salg

import com.fasterxml.jackson.annotation.JsonCreator
import java.math.BigDecimal

data class OpprettSalgView @JsonCreator constructor(val id: Int = 0, val antall: Long = 0, val totalPris: BigDecimal = BigDecimal.ZERO, val selger: Int, val kunde: Int, val produkt: Int) {
}
