package no.nilsen.cleana.api.kunde

import no.nilsen.cleana.api.BaseController
import no.nilsen.cleana.api.kunde.command.LagreKundeView
import no.nilsen.cleana.application.kunde.command.*
import no.nilsen.cleana.application.kunde.query.HentAlleKunderImpl
import no.nilsen.cleana.application.kunde.query.HentKundeImpl
import no.nilsen.cleana.application.kunde.query.KundeQueryReporitory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
open class KundeController : BaseController() {

    @Autowired
    lateinit var kundeQueryReporitory: KundeQueryReporitory

    @Autowired
    lateinit var kundeCommandRepository: KundeCommandRepository


    @GetMapping("kunde/{id}", produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun hentKunde(@PathVariable id: Int): HentKundeView {
        val a = HentKundeImpl(kundeQueryReporitory).hent(id)
        return HentKundeView(a.id, a.navn)
    }

    @GetMapping("kunde", produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun hentAlleKunder(): List<HentKundeView> {
        return HentAlleKunderImpl(kundeQueryReporitory).hentAlle().map { a -> HentKundeView(a.id, a.navn) }
    }

    @PutMapping("kunde/{id}", consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun endre(@PathVariable id: Int, @RequestBody lagreKundeView: LagreKundeView) {
        EndreKundeImpl(kundeCommandRepository).endre(EndreKundeDto(id = id, navn = lagreKundeView.navn))
    }

    @PutMapping("kunde", consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun opprett(@RequestBody lagreKundeView: LagreKundeView) {
        OpprettKundeImpl(kundeCommandRepository).opprett(OpprettKundeDto(lagreKundeView.navn))
    }

    @DeleteMapping("kunde/{id}")
    fun slett(@PathVariable id: Int) {
        SlettKundeImpl(kundeCommandRepository).slett(SlettKundeDto(id))
    }

}