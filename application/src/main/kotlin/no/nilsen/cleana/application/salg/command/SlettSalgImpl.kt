package no.nilsen.cleana.application.salg.command

import no.nilsen.cleana.domain.ansatt.Ansatt
import no.nilsen.cleana.kunde.Kunde
import no.nilsen.cleana.produkt.Produkt
import no.nilsen.cleana.salg.Salg

class SlettSalgImpl(val repo: SalgCommandRepository) : SlettSalg {

    override fun slett(s: SlettSalgDto) {
        repo.slett(Salg(id=s.id))
    }
}