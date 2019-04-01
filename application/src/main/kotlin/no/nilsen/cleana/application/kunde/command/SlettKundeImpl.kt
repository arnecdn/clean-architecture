package no.nilsen.cleana.ansatt.command

import no.nilsen.cleana.kunde.Kunde

class SlettKundeImpl(val repo: KundeCommandRepository) : SlettKunde {

    override fun slett(kunde: SlettKundeDto) {
        repo.slett(Kunde(id=kunde.id))
    }
}