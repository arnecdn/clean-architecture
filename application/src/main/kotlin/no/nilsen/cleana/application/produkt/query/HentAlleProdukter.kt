package no.nilsen.cleana.application.produkt.query


interface HentAlleProdukter {
    fun hentAlle(): List<ProduktDto>
}
