package no.nilsen.cleana.application.salg.query

import no.nilsen.cleana.application.selger.query.SelgerDto
import no.nilsen.cleana.application.kunde.query.KundeDto
import no.nilsen.cleana.application.produkt.query.ProduktDto
import no.nilsen.cleana.domain.salg.Salg

class HentSalgPrKundeImpl(val repo: SalgQueryRepository) : HentSalgPrKunde {

    override fun hentSalgPerKunde(kundeId: Int): List<SalgDto> {
        return repo.hentSalgPerKunde(kundeId).map(Salg::toHentSalgDto)
    }
}
