package no.nilsen.cleana.application.ansatt.command

import no.nilsen.cleana.domain.ansatt.Ansatt

interface AnsattCommandRepository {
    fun opprett(ansatt: Ansatt)
}