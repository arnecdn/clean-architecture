package no.nilsen.cleana.application.ansatt.query

import java.math.BigDecimal

data class HentProduktView(val id: Int = 0, val beskrivelse: String, val pris: BigDecimal) {
}
