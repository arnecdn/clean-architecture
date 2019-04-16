package no.nilsen.cleana.application.kunde.query

class HentKundeImpl(var repo: KundeQueryReporitory) : HentKunde {

    override fun hent(id: Int): KundeDto {
        val k = repo.hent(id)
        return KundeDto(id = k.id, navn = k.navn)
    }


}