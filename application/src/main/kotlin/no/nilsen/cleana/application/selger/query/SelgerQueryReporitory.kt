package no.nilsen.cleana.application.selger.query

import no.nilsen.cleana.domain.selger.Selger

interface SelgerQueryReporitory {
    fun hentAlle(): List<Selger>
    fun hent(id: Int): Selger
}