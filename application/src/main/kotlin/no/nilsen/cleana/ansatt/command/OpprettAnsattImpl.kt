package no.nilsen.cleana.ansatt.command

import no.nilsen.cleana.ansatt.Ansatt

class OpprettAnsattImpl(val repo: AnsattCommandRepository) : OpprettAnsatt {
    override fun opprett(a: OpprettAnsattDto) {
        repo.opprett(ansatt = Ansatt(navn = a.navn))
    }

}