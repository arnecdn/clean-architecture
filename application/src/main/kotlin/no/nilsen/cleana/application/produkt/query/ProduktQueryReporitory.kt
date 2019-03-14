package no.nilsen.cleana.ansatt.query

import no.nilsen.cleana.domain.produkt.Produkt

interface ProduktQueryReporitory {
    fun hentAlle(): List<Produkt>
    fun hent(id: Int): Produkt
}