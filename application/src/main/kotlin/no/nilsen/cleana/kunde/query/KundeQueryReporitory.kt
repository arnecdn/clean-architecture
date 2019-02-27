package no.nilsen.cleana.ansatt.query

import no.nilsen.cleana.kunde.Kunde

interface KundeQueryReporitory {
    fun hentAlle(): List<Kunde>
    fun hent(id: Int): Kunde
}