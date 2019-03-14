package no.nilsen.cleana.application.ansatt.query

class HentAnsattImpl(var repo: AnsattQueryReporitory) : HentAnsatt {
    override fun hentAlle(): List<HentAnsattDto> {
        return repo.hentAlle().map { a -> HentAnsattDto(a.id, a.navn) }
    }

    override fun hent(id: Int): HentAnsattDto {
        val hent = repo.hent(id)
        return HentAnsattDto(hent.id, hent.navn)
    }


}