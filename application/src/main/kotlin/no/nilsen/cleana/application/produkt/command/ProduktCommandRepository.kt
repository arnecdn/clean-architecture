package no.nilsen.cleana.ansatt.command

import no.nilsen.cleana.domain.produkt.Produkt

interface ProduktCommandRepository {
    fun opprett(produkt: Produkt)
}