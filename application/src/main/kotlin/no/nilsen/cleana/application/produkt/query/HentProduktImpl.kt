package no.nilsen.cleana.application.produkt.query

class HentProduktImpl(var repo: ProduktQueryReporitory) : HentProdukt {

    override fun hent(id: Int): ProduktDto {
        val hent = repo.hent(id)
        return ProduktDto(hent.id, hent.beskrivelse, hent.pris)
    }
}