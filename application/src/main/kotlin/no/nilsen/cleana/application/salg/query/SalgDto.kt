package no.nilsen.cleana.application.salg.query

import no.nilsen.cleana.application.selger.query.SelgerDto
import no.nilsen.cleana.application.kunde.query.KundeDto
import no.nilsen.cleana.application.produkt.query.ProduktDto
import no.nilsen.cleana.domain.salg.Salg
import java.math.BigDecimal

data class SalgDto(val id: Int = 0, val antall: Long = 0, val totalPris: BigDecimal = BigDecimal.ZERO, val selger: SelgerDto, val kunde: KundeDto, val produktQuery: ProduktDto)

fun Salg.toHentSalgDto(): SalgDto = SalgDto(this.id, this.antall, this.totalPris(), SelgerDto(this.selger), KundeDto(this.kunde), ProduktDto(this.produkt))
