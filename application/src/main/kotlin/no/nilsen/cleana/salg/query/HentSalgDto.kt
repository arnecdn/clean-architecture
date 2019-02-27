package no.nilsen.cleana.salg.query

import no.nilsen.cleana.ansatt.Ansatt
import no.nilsen.cleana.kunde.Kunde
import no.nilsen.cleana.produkt.Produkt

class HentSalgDto(val id: Int = 0, val antall: Long = 0, val totalPris: Int = 0, val selger: Ansatt, val kunde: Kunde, val produkt: Produkt) {

}
