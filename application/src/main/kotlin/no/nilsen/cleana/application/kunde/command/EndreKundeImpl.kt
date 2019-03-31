package no.nilsen.cleana.ansatt.command

import no.nilsen.cleana.kunde.Kunde

class EndreKundeImpl(val repo: KundeCommandRepository) : EndreKunde {

    override fun endre(kunde: EndreKundeDto) {
        repo.endre(Kunde(id=kunde.id, navn = kunde.navn))
    }
}