package no.nilsen.cleana.ansatt.command

import no.nilsen.cleana.kunde.Kunde

interface KundeCommandRepository {
    fun opprett(kunde: Kunde)
}