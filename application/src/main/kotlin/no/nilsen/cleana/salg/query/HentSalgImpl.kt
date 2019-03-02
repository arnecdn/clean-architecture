package no.nilsen.cleana.salg.query

import no.nilsen.cleana.salg.Salg

class HentSalgImpl(val repo: SalgQueryRepository) : HentSalg {
    val salgMapper: (Salg) -> HentSalgDto = { salg -> HentSalgDto(salg.id, salg.antall, salg.totalPris(), salg.selger, salg.kunde, salg.produkt) }

    override fun hentAlle(): List<HentSalgDto> {
        return repo.hentAlleSalg().map { s -> salgMapper(s) }
    }

    override fun hent(id: Int): HentSalgDto {
        val salg = repo.hent(id)
        return HentSalgDto(salg!!.id, salg.antall, salg.totalPris(), salg.selger, salg.kunde, salg.produkt)
    }

    override fun hentSalgPerKunde(kundeId: Int): List<HentSalgDto> {
        return repo.hentSalgPerKunde(kundeId).map { s -> salgMapper(s) }
    }

    override fun hentSalgPerAnsatt(ansattId: Int): List<HentSalgDto> {
        return repo.hentSalgPerAnsatt(ansattId).map { s -> salgMapper(s) }
    }

}
