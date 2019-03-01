package no.nilsen.cleana.ansatt.query

import no.nilsen.cleana.kunde.Kunde

class HentKundeImpl(var repo: KundeQueryReporitory) : HentKunde{
    override fun hentAlle(): List<HentKundeDto> {
        return repo.hentAlle().map { k -> HentKundeDto(id=k.id, navn=k.navn) }
    }

    override fun hent(id: Int): HentKundeDto {
        val k = repo.hent(id)
        return HentKundeDto(id=k.id, navn=k.navn)
    }


}