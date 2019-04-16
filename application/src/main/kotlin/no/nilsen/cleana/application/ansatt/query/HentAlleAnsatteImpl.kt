package no.nilsen.cleana.application.ansatt.query

class HentAlleAnsatteImpl(var repo: AnsattQueryReporitory) : HentAlleAnsatte{
    override fun hentAlle(): List<AnsattDto> {
        return repo.hentAlle().map { a -> AnsattDto(a.id, a.navn) }
    }


}