package no.nilsen.cleana.application.salg.query

class HentAlleSalgsHendelserImpl(val repo: SalgsHendelseQueryRepository) : HentAlleSalgsHendelser {

    override fun hentAlle(offset: Int, antall: Int): List<SalgsHendelseDto> {
        return repo.hent(offset, antall).map { h -> SalgsHendelseDto(h.id, h.salgId, h.hendelseType.name, h.opprettet) }
    }
}
