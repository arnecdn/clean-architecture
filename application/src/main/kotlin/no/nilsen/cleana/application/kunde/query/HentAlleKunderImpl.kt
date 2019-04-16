package no.nilsen.cleana.application.kunde.query

class HentAlleKunderImpl(var repo: KundeQueryReporitory) : HentAlleKunder {
    override fun hentAlle(): List<KundeDto> {
        return repo.hentAlle().map { k -> KundeDto(id = k.id, navn = k.navn) }
    }
}