package no.nilsen.cleana.application.produkt.command

import no.nilsen.cleana.domain.produkt.Produkt

class SlettProduktImpl(val repo: ProduktCommandRepository) : SlettProdukt {
    override fun slett(produktDto: SlettProduktDto) {
        repo.slett(Produkt(id = produktDto.id))
    }

}