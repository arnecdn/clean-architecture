package no.nilsen.cleana.api.produkt

import com.fasterxml.jackson.annotation.JsonCreator
import no.nilsen.cleana.application.produkt.query.ProduktDto
import java.math.BigDecimal

data class HentProduktView @JsonCreator constructor(val id: Int = 0, val beskrivelse: String, val pris: BigDecimal) {
    constructor(h: ProduktDto) : this(h.id, h.beskrivelse, h.pris) {}
}
