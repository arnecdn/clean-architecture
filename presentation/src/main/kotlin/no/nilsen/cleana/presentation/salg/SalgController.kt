package no.nilsen.cleana.presentation.ansatt

import no.nilsen.cleana.application.ansatt.command.OpprettSalgView
import no.nilsen.cleana.application.ansatt.query.HentSalgView
import no.nilsen.cleana.application.salg.command.OpprettSalgDto
import no.nilsen.cleana.application.salg.command.OpprettSalgImpl
import no.nilsen.cleana.application.salg.command.SalgCommandRepository
import no.nilsen.cleana.application.salg.query.HentSalgImpl
import no.nilsen.cleana.application.salg.query.SalgQueryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
open class SalgController {

    @Autowired
    lateinit var salgQueryReporitory: SalgQueryRepository

    @Autowired
    lateinit var salgCommandRepository: SalgCommandRepository

    @GetMapping("/salg/{id}", produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun hentSalg(@PathVariable id: Int): HentSalgView {
        val hentSalg = HentSalgImpl(salgQueryReporitory)
        val a = hentSalg.hent(id)
        return HentSalgView(a.id, a.antall, a.totalPris, a.selger, a.kunde, a.produkt)
    }

    @GetMapping("/salg", produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun hentAlleSalger(): List<HentSalgView> {
        return HentSalgImpl(salgQueryReporitory).hentAlle().map { a -> HentSalgView(a.id, a.antall, a.totalPris, a.selger, a.kunde, a.produkt) }
    }


    @PostMapping("/salg/", consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun opprett(@RequestBody opprettSalgView: OpprettSalgView) {
        OpprettSalgImpl(salgCommandRepository).opprett(OpprettSalgDto(antall = opprettSalgView.antall, ansattId = opprettSalgView.selger.id, kundeId = opprettSalgView.kunde.id, produktId = opprettSalgView.produkt.id))
    }
}