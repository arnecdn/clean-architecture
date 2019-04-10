package no.nilsen.cleana.application.kunde.command

import no.nilsen.cleana.domain.kunde.Kunde

class EndreKundeImpl(val repo: KundeCommandRepository) : EndreKunde {

    override fun endre(kunde: EndreKundeDto) {
        repo.endre(Kunde(id = kunde.id, navn = kunde.navn))
    }
}