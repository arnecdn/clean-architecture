package no.nilsen.cleana.ansatt.query

import no.nilsen.cleana.produkt.Produkt

interface ProduktQueryReporitory {
    fun hentAlle(): List<Produkt>
    fun hent(id: Int): Produkt
}