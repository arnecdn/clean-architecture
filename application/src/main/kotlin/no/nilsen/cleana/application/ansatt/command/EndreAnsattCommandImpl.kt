package no.nilsen.cleana.application.ansatt.command

import no.nilsen.cleana.domain.ansatt.Ansatt

class EndreAnsattCommandImpl(val repo: AnsattCommandRepository) : EndreAnsattCommand{
    override fun endre(a: EndreAnsattDto) {
        repo.endre(ansatt=Ansatt(id=a.id, navn=a.navn))
    }

}