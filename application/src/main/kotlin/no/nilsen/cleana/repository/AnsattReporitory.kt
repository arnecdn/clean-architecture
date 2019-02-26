package no.nilsen.cleana.repository

import no.nilsen.cleana.ansatt.Ansatt
import no.nilsen.cleana.ansatt.query.HentAnsattDto

interface AnsattReporitory{
    fun hentAlle(): List<Ansatt>
    fun hent(id: Int) : Ansatt
}