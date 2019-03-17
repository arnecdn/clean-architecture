package no.nilsen.cleana.presentation.ansatt

import no.nilsen.cleana.ansatt.command.KundeCommandRepository
import no.nilsen.cleana.ansatt.command.OpprettKundeDto
import no.nilsen.cleana.ansatt.command.OpprettKundeImpl
import no.nilsen.cleana.ansatt.query.HentKundeImpl
import no.nilsen.cleana.ansatt.query.KundeQueryReporitory
import no.nilsen.cleana.application.ansatt.command.OpprettKundeView
import no.nilsen.cleana.application.ansatt.command.OpprettProduktView
import no.nilsen.cleana.application.ansatt.query.HentKundeView
import no.nilsen.cleana.application.ansatt.query.HentProduktView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
open class KundeController {

    @Autowired
    lateinit var kundeQueryReporitory: KundeQueryReporitory

    @Autowired
    lateinit var kundeCommandRepository: KundeCommandRepository


    @GetMapping("/kunde/{id}", produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun hentKunde(@PathVariable id: Int): HentKundeView {
        val hentKunde = HentKundeImpl(kundeQueryReporitory)
        val a = hentKunde.hent(id)
        return HentKundeView(a.id, a.navn)
    }

    @GetMapping("/kunde", produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun hentAlleKunder (): List<HentKundeView> {
        val kundeHenter = HentKundeImpl(kundeQueryReporitory)
        return kundeHenter .hentAlle().map { a-> HentKundeView(a.id, a.navn) }
    }


    @PostMapping("/kunde/",  consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun opprett(@RequestBody opprettKundeView: OpprettKundeView) {
        val kundeOppretter = OpprettKundeImpl(kundeCommandRepository)
        kundeOppretter.opprett(OpprettKundeDto(opprettKundeView.navn))
    }
}