package no.nilsen.cleana.application.ansatt.command

interface OpprettAnsattCommand {
    fun opprett(ansatt: OpprettAnsattDto)
}