package no.nilsen.cleana.application.produkt.command

import no.nilsen.cleana.domain.produkt.Produkt

class EndreProduktImpl(val repo: ProduktCommandRepository) : EndreProdukt {
    override fun endre(produkt: EndreProduktDto) {
        repo.endre(Produkt(id = produkt.id, beskrivelse = produkt.beskrivelse, pris = produkt.pris))
    }
}