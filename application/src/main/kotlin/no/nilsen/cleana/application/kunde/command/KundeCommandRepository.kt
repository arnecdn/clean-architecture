package no.nilsen.cleana.application.kunde.command

import no.nilsen.cleana.domain.kunde.Kunde

interface KundeCommandRepository {
    fun endre(kunde: Kunde)
    fun opprett(kunde: Kunde)
    fun slett(kunde: Kunde)
}