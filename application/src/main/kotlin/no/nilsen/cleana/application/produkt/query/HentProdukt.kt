package no.nilsen.cleana.application.produkt.query


interface HentProdukt {
    fun hentAlle(): List<HentProduktDto>
    fun hent(id: Int): HentProduktDto
}
