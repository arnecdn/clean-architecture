package no.nilsen.cleana.application.salg.query

interface HentAlleSalgsHendelser {
    fun hentAlle(offset: Int, antall: Int): List<SalgsHendelseDto>
}
