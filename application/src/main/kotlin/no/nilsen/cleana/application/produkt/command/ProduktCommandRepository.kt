package no.nilsen.cleana.application.produkt.command

import no.nilsen.cleana.domain.produkt.Produkt

interface ProduktCommandRepository {
    fun endre(produkt: Produkt)
    fun opprett(produkt: Produkt)
    fun slett(produkt: Produkt)
}