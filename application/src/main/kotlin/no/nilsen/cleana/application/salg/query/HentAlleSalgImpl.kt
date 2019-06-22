package no.nilsen.cleana.application.salg.query

import no.nilsen.cleana.domain.salg.Salg

class HentAlleSalgImpl(val repo: SalgQueryRepository) : HentAlleSalg {


    override fun hentAlle(): List<SalgDto> {
        return repo.hentAlleSalg().map(Salg::toHentSalgDto)
    }


}
