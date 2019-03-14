package no.nilsen.cleana.domain.salg

import no.nilsen.cleana.application.salg.command.OpprettSalg
import no.nilsen.cleana.application.salg.command.OpprettSalgDto
import no.nilsen.cleana.application.salg.query.HentSalg
import no.nilsen.cleana.application.salg.query.HentSalgsDetaljer

class SalgController(var opprettSalg: OpprettSalg, val hentSalgsDetaljer: HentSalgsDetaljer, val hentSalg: HentSalg, val opprettSalgDto: OpprettSalgDto) {

}
