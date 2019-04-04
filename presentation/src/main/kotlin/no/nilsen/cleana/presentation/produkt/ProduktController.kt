package no.nilsen.cleana.presentation.ansatt

import no.nilsen.cleana.ansatt.command.*
import no.nilsen.cleana.ansatt.query.HentProduktImpl
import no.nilsen.cleana.ansatt.query.ProduktQueryReporitory
import no.nilsen.cleana.application.ansatt.command.OpprettProduktView
import no.nilsen.cleana.application.ansatt.query.HentProduktView
import no.nilsen.cleana.presentation.BaseController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
open class ProduktController : BaseController() {

    @Autowired
    lateinit var produktQueryReporitory: ProduktQueryReporitory

    @Autowired
    lateinit var produktCommandRepository: ProduktCommandRepository

    @GetMapping("/produkt/{id}", produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun hentProdukt(@PathVariable id: Int): HentProduktView {
        val hentProdukt = HentProduktImpl(produktQueryReporitory)
        val a = hentProdukt.hent(id)
        return HentProduktView(a.id, a.beskrivelse, a.pris)
    }

    @GetMapping("/produkt", produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun hentAlleProdukter(): List<HentProduktView> {
        return HentProduktImpl(produktQueryReporitory).hentAlle().map { a -> HentProduktView(a.id, a.beskrivelse, a.pris) }
    }


    @PutMapping("/produkt", consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun opprett(@RequestBody opprettProduktView: OpprettProduktView) {
        OpprettProduktImpl(produktCommandRepository).opprett(OpprettProduktDto(beskrivelse = opprettProduktView.beskrivelse, pris = opprettProduktView.pris))
    }

    @PutMapping("/produkt/{id}", consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun oppdater(@PathVariable id: Int, @RequestBody opprettProduktView: OpprettProduktView) {
        EndreProduktImpl(produktCommandRepository).endre(EndreProduktDto(id = id, beskrivelse = opprettProduktView.beskrivelse, pris = opprettProduktView.pris))
    }


    @DeleteMapping("/produkt/{id}")
    fun slett(@PathVariable id: Int) {
        SlettProduktImpl(produktCommandRepository).slett(SlettProduktDto(id = id))
    }

}