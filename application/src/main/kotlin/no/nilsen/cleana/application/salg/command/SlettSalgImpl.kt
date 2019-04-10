package no.nilsen.cleana.application.salg.command

import no.nilsen.cleana.domain.salg.Salg

class SlettSalgImpl(val repo: SalgCommandRepository) : SlettSalg {

    override fun slett(s: SlettSalgDto) {
        repo.slett(Salg(id = s.id))
    }
}