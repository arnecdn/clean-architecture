package no.nilsen.cleana.application.ansatt.query

import com.fasterxml.jackson.annotation.JsonCreator
import java.math.BigDecimal

data class HentProduktView @JsonCreator constructor(val id: Int = 0, val beskrivelse: String, val pris: BigDecimal) {
}
