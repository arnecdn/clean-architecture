package no.nilsen.cleana.application.ansatt.command

import no.nilsen.cleana.domain.ansatt.Ansatt

class OpprettOpprettAnsattCommandImpl(val repo: AnsattCommandRepository) : OpprettAnsattCommand {
    override fun opprett(a: OpprettAnsattDto) {
        repo.opprett(ansatt = Ansatt(navn = a.navn))
    }

}