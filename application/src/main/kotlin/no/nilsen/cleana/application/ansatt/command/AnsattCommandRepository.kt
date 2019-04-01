package no.nilsen.cleana.application.ansatt.command

import no.nilsen.cleana.domain.ansatt.Ansatt

open interface AnsattCommandRepository {
    fun opprett(ansatt: Ansatt)
    fun endre(ansatt: Ansatt)
    fun slett(ansatt: Ansatt)
}