package no.nilsen.cleana.presentation.produkt

import com.fasterxml.jackson.annotation.JsonCreator
import java.math.BigDecimal

data class OpprettProduktView @JsonCreator constructor(val beskrivelse: String, val pris: BigDecimal) {
}
