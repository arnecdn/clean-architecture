package no.nilsen.cleana.ansatt.command

import no.nilsen.cleana.produkt.Produkt

class SletteProduktImpl(val repo: ProduktCommandRepository) : SletteProdukt {
    override fun slett(produktDto: SletteProduktDto) {
        repo.slett(Produkt(id=produktDto.id))
    }

}