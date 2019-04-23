package no.nilsen.cleana.application.selger.query

class HentSelgerImpl(var repo: SelgerQueryReporitory) : HentSelger {
    override fun hent(id: Int): SelgerDto {
        val hent = repo.hent(id)
        return SelgerDto(hent.id, hent.navn)
    }

}