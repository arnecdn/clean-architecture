package no.nilsen.cleana.api.produkt

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import no.nilsen.cleana.application.kunde.query.HentAlleKunderImpl
import no.nilsen.cleana.application.kunde.query.HentKundeImpl
import no.nilsen.cleana.application.kunde.query.KundeDto
import no.nilsen.cleana.application.produkt.query.HentAlleProdukterImpl
import no.nilsen.cleana.application.produkt.query.HentProduktImpl
import no.nilsen.cleana.application.produkt.query.ProduktDto
import no.nilsen.cleana.application.produkt.query.ProduktQueryReporitory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
open class ProduktQueryResolver() : GraphQLQueryResolver {
    @Autowired
    lateinit var produktQueryRepo: ProduktQueryReporitory

    fun ProduktDto.toHentProduktView(): HentProduktView = HentProduktView(this.id, this.beskrivelse, this.pris)

    open fun getProdukt(id: Int): HentProduktView {
        return HentProduktImpl(produktQueryRepo).hent(id).toHentProduktView()
    }

    open fun getProdukter(): List<HentProduktView> {
        return HentAlleProdukterImpl(produktQueryRepo).hentAlle().map { a -> a.toHentProduktView() }
    }
}