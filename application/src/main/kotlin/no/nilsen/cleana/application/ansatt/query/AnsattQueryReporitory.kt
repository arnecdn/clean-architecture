package no.nilsen.cleana.application.ansatt.query

import no.nilsen.cleana.domain.ansatt.Ansatt

interface AnsattQueryReporitory {
    fun hentAlle(): List<Ansatt>
    fun hent(id: Int): Ansatt
}