package no.nilsen.cleana.ansatt.query

import no.nilsen.cleana.ansatt.Ansatt

interface AnsattQueryReporitory {
    fun hentAlle(): List<Ansatt>
    fun hent(id: Int): Ansatt
}