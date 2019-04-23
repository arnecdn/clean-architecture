package no.nilsen.cleana.application.selger.command

import no.nilsen.cleana.domain.selger.Selger

interface SelgerCommandRepository {
    fun opprett(nySelger: Selger)
    fun endre(oppdatertSelger: Selger)
    fun slett(selgerTilSletting: Selger)
}