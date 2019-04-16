package no.nilsen.cleana.application.salg.query

import no.nilsen.cleana.application.ansatt.query.AnsattDto
import no.nilsen.cleana.application.kunde.query.KundeDto
import no.nilsen.cleana.application.produkt.query.ProduktDto
import java.math.BigDecimal

class SalgDto(val id: Int = 0, val antall: Long = 0, val totalPris: BigDecimal = BigDecimal.ZERO, val selger: AnsattDto, val kunde: KundeDto, val produktQuery: ProduktDto) {

}
