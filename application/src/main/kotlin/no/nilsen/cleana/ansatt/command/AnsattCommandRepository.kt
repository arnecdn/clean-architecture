package no.nilsen.cleana.ansatt.command

import no.nilsen.cleana.ansatt.Ansatt

interface AnsattCommandRepository {
    fun opprett(ansatt: Ansatt)
}