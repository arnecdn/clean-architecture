package no.nilsen.cleana.application.salg.command

import no.nilsen.cleana.domain.kunde.Kunde
import no.nilsen.cleana.domain.produkt.Produkt
import no.nilsen.cleana.domain.salg.Salg
import no.nilsen.cleana.domain.selger.Selger

data class OpprettSalgDto(val produktId: Int, val ansattId: Int, val antall: Long, val kundeId: Int)

fun OpprettSalgDto.toSalg() : Salg =  Salg( antall = this.antall, selger = Selger(id = this.ansattId), kunde = Kunde(id = this.kundeId), produkt = Produkt(id = this.produktId))
