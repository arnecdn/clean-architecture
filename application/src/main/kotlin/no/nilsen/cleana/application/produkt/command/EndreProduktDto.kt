package no.nilsen.cleana.application.produkt.command

import java.math.BigDecimal

data class EndreProduktDto(val id: Int, val beskrivelse: String, val pris: BigDecimal) {
}
