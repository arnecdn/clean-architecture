package no.nilsen.cleana.ansatt.query


interface HentProdukt {
    fun hentAlle(): List<HentProduktDto>
    fun hent(id: Int): HentProduktDto
}
