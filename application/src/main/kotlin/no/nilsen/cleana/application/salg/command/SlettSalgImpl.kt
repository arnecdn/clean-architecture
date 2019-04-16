package no.nilsen.cleana.application.salg.command

import no.nilsen.cleana.domain.salg.Salg

class SlettSalgImpl(val repo: SalgCommandRepository) : SlettSalg {

    override fun slett(slettSalgDto: SlettSalgDto) {
        repo.slett(Salg(id = slettSalgDto.id))
    }
}