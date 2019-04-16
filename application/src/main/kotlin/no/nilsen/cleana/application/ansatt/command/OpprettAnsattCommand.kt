package no.nilsen.cleana.application.ansatt.command

interface OpprettAnsattCommand {
    fun opprett(opprettAnsattDto: OpprettAnsattDto)
}