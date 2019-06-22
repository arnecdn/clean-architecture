package no.nilsen.cleana.application.salg.query

import no.nilsen.cleana.domain.salg.Salg

class HentSalgPrAnsattImpl(val repo: SalgQueryRepository) : HentSalgPrAnsatt {


    override fun hentSalgPerAnsatt(ansattId: Int): List<SalgDto> {
        return repo.hentSalgPerAnsatt(ansattId).map(Salg::toHentSalgDto)
    }
}
