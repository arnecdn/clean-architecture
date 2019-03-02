package no.nilsen.cleana.ansatt.command

import no.nilsen.cleana.produkt.Produkt

class OpprettProduktImpl(val repo: ProduktCommandRepository) : OpprettProdukt {
    override fun opprett(produkt: OpprettProduktDto) {
        repo.opprett(Produkt(beskrivelse = produkt.beskrivelse, pris = produkt.pris))
    }

}