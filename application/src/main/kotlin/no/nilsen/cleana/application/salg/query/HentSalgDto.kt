package no.nilsen.cleana.application.salg.query

import no.nilsen.cleana.application.ansatt.query.HentAnsattDto
import no.nilsen.cleana.application.kunde.query.HentKundeDto
import no.nilsen.cleana.application.produkt.query.HentProduktDto
import no.nilsen.cleana.domain.ansatt.Ansatt
import no.nilsen.cleana.domain.salg.Salg
import java.math.BigDecimal

class HentSalgDto(val id: Int = 0, val antall: Long = 0, val totalPris: BigDecimal = BigDecimal.ZERO, val selger: HentAnsattDto, val kunde: HentKundeDto, val produkt: HentProduktDto) {

}
