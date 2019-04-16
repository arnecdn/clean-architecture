package no.nilsen.cleana.application.salg.command

import no.nilsen.cleana.domain.ansatt.Ansatt
import no.nilsen.cleana.domain.kunde.Kunde
import no.nilsen.cleana.domain.produkt.Produkt
import no.nilsen.cleana.domain.salg.Salg

class OpprettSalgImpl(val repo: SalgCommandRepository) : OpprettSalg {

    override fun opprett(opprettSalgDto: OpprettSalgDto) {

        val nySalg = Salg(antall = opprettSalgDto.antall, selger = Ansatt(id = opprettSalgDto.ansattId), kunde = Kunde(id = opprettSalgDto.kundeId), produkt = Produkt(id = opprettSalgDto.produktId))
        if (nySalg.erGyldigNyttSalg()) {
            repo.opprett(nySalg)
        }
    }
}
