package no.nilsen.cleana.application.selger.command

import no.nilsen.cleana.domain.selger.Selger

class SlettSelgerCommandImpl(val repo: SelgerCommandRepository) : SlettSelgerCommand {
    override fun slett(slettSelgerDto: SlettSelgerDto) {
        repo.slett(selgerTilSletting = Selger(id = slettSelgerDto.id))
    }

}