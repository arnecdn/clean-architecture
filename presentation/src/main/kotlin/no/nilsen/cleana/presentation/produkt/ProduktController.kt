package no.nilsen.cleana.presentation.ansatt

import no.nilsen.cleana.ansatt.command.OpprettProduktDto
import no.nilsen.cleana.ansatt.command.OpprettProduktImpl
import no.nilsen.cleana.ansatt.command.ProduktCommandRepository
import no.nilsen.cleana.ansatt.query.HentProduktImpl
import no.nilsen.cleana.ansatt.query.ProduktQueryReporitory
import no.nilsen.cleana.application.ansatt.command.OpprettProduktView
import no.nilsen.cleana.application.ansatt.query.HentProduktView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal

@RestController
open class ProduktController {

    @Autowired
    lateinit var produktQueryReporitory: ProduktQueryReporitory

    @Autowired
    lateinit var produktCommandRepository: ProduktCommandRepository

    @GetMapping("/produkt/test", produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun hentProduktEksempel(): HentProduktView {
        return HentProduktView(1, "Produkt Produktsen", BigDecimal.TEN)
    }

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


    @PostMapping("/produkt/", consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun opprett(@RequestBody opprettProduktView: OpprettProduktView) {
        val ProduktOppretter = OpprettProduktImpl(produktCommandRepository)
        ProduktOppretter.opprett(OpprettProduktDto(beskrivelse = opprettProduktView.beskrivelse, pris = opprettProduktView.pris))
    }
}