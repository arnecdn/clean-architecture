package no.nilsen.cleana.api.produkt

import com.fasterxml.jackson.annotation.JsonCreator
import java.math.BigDecimal

data class OpprettProduktView @JsonCreator constructor(val beskrivelse: String, val pris: BigDecimal) {
}
