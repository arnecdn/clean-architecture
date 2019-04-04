package no.nilsen.cleana.ansatt.command

import no.nilsen.cleana.produkt.Produkt

class EndreProduktImpl(val repo: ProduktCommandRepository) : EndreProdukt {
    override fun endre(produkt: EndreProduktDto) {
        repo.endre(Produkt(id = produkt.id, beskrivelse = produkt.beskrivelse, pris = produkt.pris))
    }
}