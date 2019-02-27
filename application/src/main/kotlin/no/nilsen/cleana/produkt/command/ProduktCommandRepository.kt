package no.nilsen.cleana.ansatt.command

import no.nilsen.cleana.produkt.Produkt

interface ProduktCommandRepository {
    fun opprett(produkt: Produkt)
}