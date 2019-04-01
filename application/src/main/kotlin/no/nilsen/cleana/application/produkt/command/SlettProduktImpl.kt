package no.nilsen.cleana.ansatt.command

import no.nilsen.cleana.produkt.Produkt

class SlettProduktImpl(val repo: ProduktCommandRepository) : SlettProdukt {
    override fun slett(produktDto: SlettProduktDto) {
        repo.slett(Produkt(id=produktDto.id))
    }

}