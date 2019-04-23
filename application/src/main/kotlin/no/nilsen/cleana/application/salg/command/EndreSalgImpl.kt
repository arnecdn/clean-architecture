package no.nilsen.cleana.application.salg.command

import no.nilsen.cleana.domain.selger.Selger
import no.nilsen.cleana.domain.kunde.Kunde
import no.nilsen.cleana.domain.produkt.Produkt
import no.nilsen.cleana.domain.salg.Salg

class EndreSalgImpl(val repo: SalgCommandRepository) : EndreSalg {

    override fun endre(endreSalgDto: EndreSalgDto) {
        repo.endre(Salg(id = endreSalgDto.id, antall = endreSalgDto.antall, selger = Selger(id = endreSalgDto.ansattId), kunde = Kunde(id = endreSalgDto.kundeId), produkt = Produkt(id = endreSalgDto.produktId)))
    }
}