package no.nilsen.cleana.salg.query

import no.nilsen.cleana.salg.Salg

interface SalgQueryRepository {
    fun hent(id: Int): Salg?
    fun hentSalgPerKunde(kundeId: Int): List<Salg>
    fun hentSalgPerAnsatt(ansattId: Int): List<Salg>
    fun hentAlleSalg(): List<Salg>
}