package no.nilsen.cleana.application.selger.command

import no.nilsen.cleana.domain.selger.Selger

class EndreSelgerCommandImpl(val repo: SelgerCommandRepository) : EndreSelgerCommand {
    override fun endre(endreSelgerDto: EndreSelgerDto) {
        repo.endre(oppdatertSelger = Selger(id = endreSelgerDto.id, navn = endreSelgerDto.navn))
    }

}