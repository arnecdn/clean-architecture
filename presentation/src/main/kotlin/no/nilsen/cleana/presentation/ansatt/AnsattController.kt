package no.nilsen.cleana.presentation.ansatt

import no.nilsen.cleana.application.ansatt.command.*
import no.nilsen.cleana.application.ansatt.query.AnsattQueryReporitory
import no.nilsen.cleana.application.ansatt.query.HentAnsattImpl
import no.nilsen.cleana.application.ansatt.query.HentAnsattView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
open class AnsattController {

    @Autowired
    lateinit var ansattQueryReporitory: AnsattQueryReporitory


    @Autowired
    lateinit var ansattCommandRepository: AnsattCommandRepository


    @GetMapping("/ansatt/{id}", produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun hentAnsatt(@PathVariable id: Int): HentAnsattView {
        val hentAnsatt = HentAnsattImpl(ansattQueryReporitory)
        val a = hentAnsatt.hent(id)
        return HentAnsattView(a.id, a.navn)
    }

    @GetMapping("/ansatt", produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun hentAlleAnsatt(): List<HentAnsattView> {
        val hentAnsatt = HentAnsattImpl(ansattQueryReporitory)
        return hentAnsatt.hentAlle().map { a -> HentAnsattView(a.id, a.navn) }
    }


    @PutMapping("/ansatt", consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun opprettAnsatt(@RequestBody opprettAnsattView: OpprettAnsattView) {
        OpprettOpprettAnsattCommandImpl(ansattCommandRepository).opprett(OpprettAnsattDto(opprettAnsattView.navn))
    }


    @PutMapping("/ansatt/{id}", consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun endreAnsatt(@PathVariable id: Int,@RequestBody endreAnsattView: EndreAnsattView) {
        EndreAnsattCommandImpl(ansattCommandRepository).endre(EndreAnsattDto(id, endreAnsattView.navn))
    }

}