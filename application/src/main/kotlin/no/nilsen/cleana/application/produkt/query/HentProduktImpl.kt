package no.nilsen.cleana.application.produkt.query

class HentProduktImpl(var repo: ProduktQueryReporitory) : HentProdukt {
    override fun hentAlle(): List<HentProduktDto> {
        return repo.hentAlle().map { p -> HentProduktDto(p.id, p.beskrivelse, p.pris) }
    }

    override fun hent(id: Int): HentProduktDto {
        val hent = repo.hent(id)
        return HentProduktDto(hent.id, hent.beskrivelse, hent.pris)
    }
}