package no.nilsen.cleana.application.produkt.query

class HentAlleProdukterImpl(var repo: ProduktQueryReporitory) : HentAlleProdukter {
    override fun hentAlle(): List<ProduktDto> {
        return repo.hentAlle().map { p -> ProduktDto(p.id, p.beskrivelse, p.pris) }
    }
}