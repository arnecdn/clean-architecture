package no.nilsen.cleana.api.salg

import no.nilsen.cleana.api.BaseController
import no.nilsen.cleana.api.ansatt.HentAnsattView
import no.nilsen.cleana.api.kunde.HentKundeView
import no.nilsen.cleana.api.produkt.HentProduktView
import no.nilsen.cleana.application.salg.command.*
import no.nilsen.cleana.application.salg.query.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
open class SalgController : BaseController() {
    fun SalgDto.toHentSalgView(): HentSalgView = HentSalgView(this.id, this.antall, this.totalPris, HentAnsattView(this.selger), HentKundeView(this.kunde), HentProduktView(this.produktQuery))
    fun OpprettSalgView.toOpprettSalgDto(): OpprettSalgDto = OpprettSalgDto(antall = this.antall, ansattId = this.selger, kundeId = this.kunde, produktId = this.produkt)

    @Autowired
    lateinit var salgQueryReporitory: SalgQueryRepository

    @Autowired
    lateinit var salgCommandRepository: SalgCommandRepository

    @GetMapping("salg", produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun hentAlleSalger(): List<HentSalgView> {
        return HentAlleSalgImpl(salgQueryReporitory).hentAlle().map { a -> a.toHentSalgView() }
    }

    @GetMapping("salg/{id}", produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun hentSalg(@PathVariable id: Int): HentSalgView {
        return HentSalgImpl(salgQueryReporitory).hent(id).toHentSalgView()
    }

    @GetMapping("salg/kunde/{id}", produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun hentAlleSalgPrKunde(@PathVariable id: Int): List<HentSalgView> {
        return HentSalgPrKundeImpl(salgQueryReporitory).hentSalgPerKunde(id).map { a -> a.toHentSalgView() }
    }

    @GetMapping("salg/selger/{id}", produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun hentAlleSalgPrSelger(@PathVariable id: Int): List<HentSalgView> {
        return HentSalgPrAnsattImpl(salgQueryReporitory).hentSalgPerAnsatt(id).map { a -> a.toHentSalgView() }
    }

    @PutMapping("salg", consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun opprett(@RequestBody opprettSalgView: OpprettSalgView) {
        OpprettSalgImpl(salgCommandRepository).opprett(opprettSalgView.toOpprettSalgDto())
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