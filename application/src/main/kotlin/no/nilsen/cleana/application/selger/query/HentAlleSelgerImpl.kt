package no.nilsen.cleana.application.selger.query

class HentAlleSelgerImpl(var repo: SelgerQueryReporitory) : HentAlleSelger{
    override fun hentAlle(): List<SelgerDto> {
        return repo.hentAlle().map { a -> SelgerDto(a.id, a.navn) }
    }


}