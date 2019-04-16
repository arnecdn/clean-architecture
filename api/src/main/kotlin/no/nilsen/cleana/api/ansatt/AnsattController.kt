package no.nilsen.cleana.api.ansatt

import no.nilsen.cleana.api.BaseController
import no.nilsen.cleana.application.ansatt.command.*
import no.nilsen.cleana.application.ansatt.query.AnsattQueryReporitory
import no.nilsen.cleana.application.ansatt.query.HentAlleAnsatteImpl
import no.nilsen.cleana.application.ansatt.query.HentAnsattImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController

open class AnsattController : BaseController() {

    @Autowired
    lateinit var ansattQueryReporitory: AnsattQueryReporitory


    @Autowired
    lateinit var ansattCommandRepository: AnsattCommandRepository


    @GetMapping("ansatt/{id}", produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun hentAnsatt(@PathVariable id: Int): HentAnsattView {
        val a = HentAnsattImpl(ansattQueryReporitory).hent(id)
        return HentAnsattView(a.id, a.navn)
    }

    @GetMapping("ansatt", produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun hentAlleAnsatt(): List<HentAnsattView> {
        return HentAlleAnsatteImpl(ansattQueryReporitory).hentAlle().map { a -> HentAnsattView(a.id, a.navn) }
    }

    @PutMapping("ansatt", consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun opprettAnsatt(@RequestBody lagreAnsattView: LagreAnsattView) {
        OpprettOpprettAnsattCommandImpl(ansattCommandRepository).opprett(OpprettAnsattDto(lagreAnsattView.navn))
    }


    @PutMapping("ansatt/{id}", consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun endreAnsatt(@PathVariable id: Int, @RequestBody ansattView: LagreAnsattView) {
        EndreAnsattCommandImpl(ansattCommandRepository).endre(EndreAnsattDto(id, ansattView.navn))
    }

    @DeleteMapping("ansatt/{id}")
    fun slett(@PathVariable id: Int) {
        SlettAnsattCommandImpl(ansattCommandRepository).slett(SlettAnsattDto(id))
    }

}