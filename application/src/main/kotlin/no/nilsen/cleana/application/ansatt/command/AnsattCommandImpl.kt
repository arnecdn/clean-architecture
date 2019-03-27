package no.nilsen.cleana.application.ansatt.command

import no.nilsen.cleana.domain.ansatt.Ansatt

class AnsattCommandImpl(val repo: AnsattCommandRepository) : AnsattCommand {
    override fun endre(a: EndreAnsattDto) {
        repo.endre(ansatt=Ansatt(id=a.id, navn=a.navn))
    }

    override fun opprett(a: OpprettAnsattDto) {
        repo.opprett(ansatt = Ansatt(navn = a.navn))
    }

}