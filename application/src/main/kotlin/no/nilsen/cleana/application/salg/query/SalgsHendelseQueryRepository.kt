package no.nilsen.cleana.application.salg.query

import no.nilsen.cleana.domain.salg.SalgsHendelse

interface SalgsHendelseQueryRepository {
    fun hent(offset: Int=0, antall: Int): List<SalgsHendelse>
}