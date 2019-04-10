package no.nilsen.cleana.application.salg.command

import no.nilsen.cleana.domain.salg.Salg

interface SalgCommandRepository {
    fun opprett(salg: Salg)
    fun endre(salg: Salg)
    fun slett(salg: Salg)
}