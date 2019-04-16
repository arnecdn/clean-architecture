package no.nilsen.cleana.application.ansatt.query

class HentAnsattImpl(var repo: AnsattQueryReporitory) : HentAnsatt {
    override fun hent(id: Int): AnsattDto {
        val hent = repo.hent(id)
        return AnsattDto(hent.id, hent.navn)
    }

}