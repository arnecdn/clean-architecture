package no.nilsen.cleana.application.ansatt.command

import no.nilsen.cleana.domain.ansatt.Ansatt

class SlettAnsattCommandImpl(val repo: AnsattCommandRepository) : SlettAnsattCommand {
    override fun slett(a: SlettAnsattDto) {
        repo.slett(ansatt = Ansatt(id = a.id))
    }

}