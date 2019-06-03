package no.nilsen.cleana.application.salg.query

import no.nilsen.cleana.application.selger.query.SelgerDto
import no.nilsen.cleana.application.kunde.query.KundeDto
import no.nilsen.cleana.application.produkt.query.ProduktDto
import no.nilsen.cleana.domain.salg.Salg

class HentSalgPrAnsattImpl(val repo: SalgQueryRepository) : HentSalgPrAnsatt {

    fun Salg.toHentSalgDto(): SalgDto = SalgDto(this.id, this.antall, this.totalPris(), SelgerDto(this.selger), KundeDto(this.kunde), ProduktDto(this.produkt))

    override fun hentSalgPerAnsatt(ansattId: Int): List<SalgDto> {

        return repo.hentSalgPerAnsatt(ansattId).map { s -> s.toHentSalgDto() }
    }

}
