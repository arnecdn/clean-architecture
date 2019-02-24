package no.nilsen.cleana.salg

import no.nilsen.cleana.salg.command.OpprettSalg
import no.nilsen.cleana.salg.command.OpprettSalgDto
import no.nilsen.cleana.salg.query.HentSalg
import no.nilsen.cleana.salg.query.HentSalgsDetaljer

class SalgController(var opprettSalg: OpprettSalg, val hentSalgsDetaljer: HentSalgsDetaljer, val hentSalg: HentSalg, val opprettSalgDto: OpprettSalgDto) {

}
