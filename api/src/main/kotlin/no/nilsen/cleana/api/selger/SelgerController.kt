package no.nilsen.cleana.api.selger

import no.nilsen.cleana.api.BaseController
import no.nilsen.cleana.application.selger.command.*
import no.nilsen.cleana.application.selger.query.SelgerQueryReporitory
import no.nilsen.cleana.application.selger.query.HentAlleSelgerImpl
import no.nilsen.cleana.application.selger.query.HentSelgerImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController

open class SelgerController : BaseController() {

    @Autowired
    lateinit var selgerQueryReporitory: SelgerQueryReporitory


    @Autowired
    lateinit var selgerCommandRepository: SelgerCommandRepository


    @GetMapping("selger/{id}", produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun hentSelger(@PathVariable id: Int): HentSelgerView {
        val a = HentSelgerImpl(selgerQueryReporitory).hent(id)
        return HentSelgerView(a.id, a.navn)
    }

    @GetMapping("selger", produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun hentAlleSelgere(): List<HentSelgerView> {
        return HentAlleSelgerImpl(selgerQueryReporitory).hentAlle().map { a -> HentSelgerView(a.id, a.navn) }
    }

    @PutMapping("selger", consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun opprettSelger(@RequestBody lagreSelgerView: LagreSelgerView) {
        OpprettOpprettSelgerCommandImpl(selgerCommandRepository).opprett(OpprettSelgerDto(lagreSelgerView.navn))
    }


    @PutMapping("selger/{id}", consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun endreSelger(@PathVariable id: Int, @RequestBody selgerView: LagreSelgerView) {
        EndreSelgerCommandImpl(selgerCommandRepository).endre(EndreSelgerDto(id, selgerView.navn))
    }

    @DeleteMapping("selger/{id}")
    fun slett(@PathVariable id: Int) {
        SlettSelgerCommandImpl(selgerCommandRepository).slett(SlettSelgerDto(id))
    }

}