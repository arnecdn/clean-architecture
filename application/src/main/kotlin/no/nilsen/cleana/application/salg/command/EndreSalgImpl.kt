package no.nilsen.cleana.application.salg.command

import no.nilsen.cleana.domain.kunde.Kunde
import no.nilsen.cleana.domain.produkt.Produkt
import no.nilsen.cleana.domain.salg.Salg
import no.nilsen.cleana.domain.selger.Selger

class EndreSalgImpl(val repo: SalgCommandRepository) : EndreSalg {

    override fun endre(endreSalgDto: EndreSalgDto) {

        endreSalgDto.let { Salg(id = endreSalgDto.id, antall = endreSalgDto.antall, selger = Selger(id = endreSalgDto.ansattId), kunde = Kunde(id = endreSalgDto.kundeId), produkt = Produkt(id = endreSalgDto.produktId)) }
                .takeIf { it.erGyldigOppdatering }
                ?.apply { SalgCommandRepository::endre}
    }
}