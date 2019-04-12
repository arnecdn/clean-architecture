package no.nilsen.cleana.application.salg.query

import no.nilsen.cleana.application.ansatt.query.HentAnsattDto
import no.nilsen.cleana.application.kunde.query.HentKundeDto
import no.nilsen.cleana.application.produkt.query.HentProduktDto
import no.nilsen.cleana.domain.salg.Salg

class HentSalgImpl(val repo: SalgQueryRepository) : HentSalg {

    fun Salg.toHentSalgDto(): HentSalgDto = HentSalgDto(this.id, this.antall, this.totalPris(), HentAnsattDto(this.selger), HentKundeDto(this.kunde), HentProduktDto(this.produkt))

    override fun hentAlle(): List<HentSalgDto> {
        return repo.hentAlleSalg().map { s -> s.toHentSalgDto() }
    }

    override fun hent(id: Int): HentSalgDto {
        return repo.hent(id)!!.toHentSalgDto()
    }

    override fun hentSalgPerKunde(kundeId: Int): List<HentSalgDto> {
        return repo.hentSalgPerKunde(kundeId).map { s -> s.toHentSalgDto() }
    }

    override fun hentSalgPerAnsatt(ansattId: Int): List<HentSalgDto> {
        return repo.hentSalgPerAnsatt(ansattId).map { s -> s.toHentSalgDto() }
    }

}
