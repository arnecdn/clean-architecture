package no.nilsen.cleana.application.salg.command

class OpprettSalgImpl(val repo: SalgCommandRepository) : OpprettSalg {

    override fun opprett(opprettSalgDto: OpprettSalgDto) {

        val nyttSalg = opprettSalgDto.toSalg()

        when (nyttSalg.erGyldigNyttSalg) {
            true -> repo.opprett(nyttSalg)
            false -> throw IllegalArgumentException("$nyttSalg er ikke gyldig")
        }
    }
}
