package no.nilsen.cleana.presentation.salg

import no.nilsen.cleana.application.salg.command.*
import no.nilsen.cleana.application.salg.query.HentSalgImpl
import no.nilsen.cleana.application.salg.query.SalgQueryRepository
import no.nilsen.cleana.presentation.BaseController
import no.nilsen.cleana.presentation.ansatt.HentAnsattView
import no.nilsen.cleana.presentation.kunde.HentKundeView
import no.nilsen.cleana.presentation.produkt.HentProduktView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
open class SalgController : BaseController() {

    @Autowired
    lateinit var salgQueryReporitory: SalgQueryRepository

    @Autowired
    lateinit var salgCommandRepository: SalgCommandRepository

    @GetMapping("salg", produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun hentAlleSalger(): List<HentSalgView> {
        return HentSalgImpl(salgQueryReporitory).hentAlle().map { a -> HentSalgView(a.id, a.antall, a.totalPris, HentAnsattView(a.selger), HentKundeView(a.kunde), HentProduktView(a.produkt)) }
    }

    @GetMapping("salg/{id}", produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun hentSalg(@PathVariable id: Int): HentSalgView {
        val salg = HentSalgImpl(salgQueryReporitory).hent(id)
        return HentSalgView(salg.id, salg.antall, salg.totalPris, HentAnsattView(salg.selger), HentKundeView(salg.kunde), HentProduktView(salg.produkt))
    }

    @GetMapping("salg/kunde/{id}", produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun hentAlleSalgPrKunde(@PathVariable id: Int): List<HentSalgView> {
        return HentSalgImpl(salgQueryReporitory).hentSalgPerKunde(id).map { a -> HentSalgView(a.id, a.antall, a.totalPris, HentAnsattView(a.selger), HentKundeView(a.kunde), HentProduktView(a.produkt)) }
    }

    @GetMapping("salg/selger/{id}", produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun hentAlleSalgPrSelger(@PathVariable id: Int): List<HentSalgView> {
        return HentSalgImpl(salgQueryReporitory).hentSalgPerAnsatt(id).map { a -> HentSalgView(a.id, a.antall, a.totalPris, HentAnsattView(a.selger), HentKundeView(a.kunde), HentProduktView(a.produkt)) }
    }

    @PutMapping("salg", consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun opprett(@RequestBody opprettSalgView: OpprettSalgView) {
        OpprettSalgImpl(salgCommandRepository).opprett(OpprettSalgDto(antall = opprettSalgView.antall, ansattId = opprettSalgView.selger, kundeId = opprettSalgView.kunde, produktId = opprettSalgView.produkt))
    }

    @PutMapping("salg/{id}", consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun endre(@PathVariable id: Int, @RequestBody opprettSalgView: OpprettSalgView) {
        EndreSalgImpl(salgCommandRepository).endre(EndreSalgDto(id = id, antall = opprettSalgView.antall, ansattId = opprettSalgView.selger, kundeId = opprettSalgView.kunde, produktId = opprettSalgView.produkt))
    }


    @DeleteMapping("salg/{id}")
    fun slett(@PathVariable id: Int) {
        SlettSalgImpl(salgCommandRepository).slett(SlettSalgDto(id = id))
    }

}