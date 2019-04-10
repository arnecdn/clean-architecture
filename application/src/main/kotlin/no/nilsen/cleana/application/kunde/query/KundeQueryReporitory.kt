package no.nilsen.cleana.application.kunde.query

import no.nilsen.cleana.domain.kunde.Kunde

interface KundeQueryReporitory {
    fun hentAlle(): List<Kunde>
    fun hent(id: Int): Kunde
}