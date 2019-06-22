package no.nilsen.cleana.application.salg.command

import no.nilsen.cleana.domain.selger.Selger
import no.nilsen.cleana.domain.kunde.Kunde
import no.nilsen.cleana.domain.produkt.Produkt
import no.nilsen.cleana.domain.salg.Salg

class OpprettSalgImpl(val repo: SalgCommandRepository) : OpprettSalg {

    override fun opprett(opprettSalgDto: OpprettSalgDto) {

        opprettSalgDto.let { Salg( antall = opprettSalgDto.antall, selger = Selger(id = opprettSalgDto.ansattId), kunde = Kunde(id = opprettSalgDto.kundeId), produkt = Produkt(id = opprettSalgDto.produktId))}
                .takeIf { it.erGyldigNyttSalg}
                ?.apply {  repo.opprett(this)}

    }
}
