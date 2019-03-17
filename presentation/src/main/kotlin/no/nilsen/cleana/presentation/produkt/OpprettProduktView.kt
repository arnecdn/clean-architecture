package no.nilsen.cleana.application.ansatt.command

import com.fasterxml.jackson.annotation.JsonCreator
import java.math.BigDecimal

data class OpprettProduktView @JsonCreator constructor(val beskrivelse: String, val pris: BigDecimal) {
}
