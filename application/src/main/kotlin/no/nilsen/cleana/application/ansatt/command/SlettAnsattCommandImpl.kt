package no.nilsen.cleana.application.ansatt.command

import no.nilsen.cleana.domain.ansatt.Ansatt

class SlettAnsattCommandImpl(val repo: AnsattCommandRepository) : SlettAnsattCommand {
    override fun slett(slettAnsattDto: SlettAnsattDto) {
        repo.slett(ansattTilSletting = Ansatt(id = slettAnsattDto.id))
    }

}