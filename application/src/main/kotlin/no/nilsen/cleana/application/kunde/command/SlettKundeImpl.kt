package no.nilsen.cleana.application.kunde.command

import no.nilsen.cleana.domain.kunde.Kunde

class SlettKundeImpl(val repo: KundeCommandRepository) : SlettKunde {

    override fun slett(kunde: SlettKundeDto) {
        repo.slett(Kunde(id = kunde.id))
    }
}