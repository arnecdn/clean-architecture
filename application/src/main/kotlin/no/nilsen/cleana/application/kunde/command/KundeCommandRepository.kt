package no.nilsen.cleana.ansatt.command

import no.nilsen.cleana.kunde.Kunde

interface KundeCommandRepository {
    fun endre(kunde: Kunde)
    fun opprett(kunde: Kunde)
}