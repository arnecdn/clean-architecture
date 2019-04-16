package no.nilsen.cleana.application.produkt.query


interface HentProdukt {
    fun hent(id: Int): ProduktDto
}
