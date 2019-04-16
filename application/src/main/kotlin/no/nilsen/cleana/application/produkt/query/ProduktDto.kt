package no.nilsen.cleana.application.produkt.query

import no.nilsen.cleana.domain.produkt.Produkt
import java.math.BigDecimal

class ProduktDto(val id: Int, val beskrivelse: String, val pris: BigDecimal = BigDecimal.ZERO) {
    constructor(p: Produkt) : this(p.id, p.beskrivelse, p.pris) {}
}
