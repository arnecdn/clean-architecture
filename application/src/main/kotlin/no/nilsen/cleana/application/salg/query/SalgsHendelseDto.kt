package no.nilsen.cleana.application.salg.query

import no.nilsen.cleana.application.selger.query.SelgerDto
import no.nilsen.cleana.application.kunde.query.KundeDto
import no.nilsen.cleana.application.produkt.query.ProduktDto
import java.math.BigDecimal
import java.time.LocalDateTime

class SalgsHendelseDto(val id: Int = 0, val salgId: Int= 0, val hendelse: String, val opprettet: LocalDateTime) {

}
