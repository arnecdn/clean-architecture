package no.nilsen.cleana.application.ansatt.command

interface OpprettAnsattCommand {
    fun opprett(ansattDto: OpprettAnsattDto)
}