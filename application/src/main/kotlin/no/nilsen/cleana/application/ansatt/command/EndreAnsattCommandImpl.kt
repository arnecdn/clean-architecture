package no.nilsen.cleana.application.ansatt.command

import no.nilsen.cleana.domain.ansatt.Ansatt

class EndreAnsattCommandImpl(val repo: AnsattCommandRepository) : EndreAnsattCommand {
    override fun endre(endreAnsattDto: EndreAnsattDto) {
        repo.endre(oppdatertAnsatt = Ansatt(id = endreAnsattDto.id, navn = endreAnsattDto.navn))
    }

}