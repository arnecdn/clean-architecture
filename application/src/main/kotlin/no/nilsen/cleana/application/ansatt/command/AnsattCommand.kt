package no.nilsen.cleana.application.ansatt.command

interface AnsattCommand {
    fun opprett(ansatt: OpprettAnsattDto)
    fun endre(ansatt: EndreAnsattDto)
}