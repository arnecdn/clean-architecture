package no.nilsen.cleana.application.salg.query

import no.nilsen.cleana.application.selger.query.SelgerDto
import no.nilsen.cleana.application.kunde.query.KundeDto
import no.nilsen.cleana.application.produkt.query.ProduktDto
import no.nilsen.cleana.domain.salg.Salg

class HentAlleSalgImpl(val repo: SalgQueryRepository) : HentAlleSalg {

    fun Salg.toHentSalgDto(): SalgDto = SalgDto(this.id, this.antall, this.totalPris(), SelgerDto(this.selger), KundeDto(this.kunde), ProduktDto(this.produkt))

    override fun hentAlle(): List<SalgDto> {
        return repo.hentAlleSalg().map { s -> s.toHentSalgDto() }
    }


}
