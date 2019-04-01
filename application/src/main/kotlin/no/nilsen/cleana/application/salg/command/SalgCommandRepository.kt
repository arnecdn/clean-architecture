package no.nilsen.cleana.application.salg.command

import no.nilsen.cleana.salg.Salg

interface SalgCommandRepository {
    fun opprett(salg: Salg)
    fun endre(salg: Salg)
    fun slett(salg: Salg)
}