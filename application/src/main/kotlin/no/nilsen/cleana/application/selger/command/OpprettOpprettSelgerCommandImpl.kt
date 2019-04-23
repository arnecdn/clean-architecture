package no.nilsen.cleana.application.selger.command

import no.nilsen.cleana.domain.selger.Selger

class OpprettOpprettSelgerCommandImpl(val repo: SelgerCommandRepository) : OpprettSelgerCommand {
    override fun opprett(a: OpprettSelgerDto) {
        repo.opprett(nySelger = Selger(navn = a.navn))
    }

}