package no.nilsen.cleana.application.produkt.query

import no.nilsen.cleana.domain.produkt.Produkt

interface ProduktQueryReporitory {
    fun hentAlle(): List<Produkt>
    fun hent(id: Int): Produkt
}