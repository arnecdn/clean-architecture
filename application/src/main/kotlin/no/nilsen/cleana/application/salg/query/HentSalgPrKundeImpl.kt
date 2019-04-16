package no.nilsen.cleana.application.salg.query

import no.nilsen.cleana.application.ansatt.query.AnsattDto
import no.nilsen.cleana.application.kunde.query.KundeDto
import no.nilsen.cleana.application.produkt.query.ProduktDto
import no.nilsen.cleana.domain.salg.Salg

class HentSalgPrKundeImpl(val repo: SalgQueryRepository) : HentSalgPrKunde {

    fun Salg.toHentSalgDto(): SalgDto = SalgDto(this.id, this.antall, this.totalPris(), AnsattDto(this.selger), KundeDto(this.kunde), ProduktDto(this.produkt))

    override fun hentSalgPerKunde(kundeId: Int): List<SalgDto> {
        return repo.hentSalgPerKunde(kundeId).map { s -> s.toHentSalgDto() }
    }
}
