package no.nilsen.cleana.ansatt.command

import no.nilsen.cleana.domain.kunde.Kunde

interface KundeCommandRepository {
    fun opprett(kunde: Kunde)
}