package no.nilsen.cleana.api.salg

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import no.nilsen.cleana.api.ansatt.HentAnsattView
import no.nilsen.cleana.api.kunde.HentKundeView
import no.nilsen.cleana.api.produkt.HentProduktView
import no.nilsen.cleana.application.ansatt.query.AnsattDto
import no.nilsen.cleana.application.ansatt.query.AnsattQueryReporitory
import no.nilsen.cleana.application.ansatt.query.HentAnsattImpl
import no.nilsen.cleana.application.kunde.query.HentKundeImpl
import no.nilsen.cleana.application.kunde.query.KundeDto
import no.nilsen.cleana.application.kunde.query.KundeQueryReporitory
import no.nilsen.cleana.application.produkt.query.HentProduktImpl
import no.nilsen.cleana.application.produkt.query.ProduktDto
import no.nilsen.cleana.application.produkt.query.ProduktQueryReporitory
import no.nilsen.cleana.application.salg.query.HentAlleSalgImpl
import no.nilsen.cleana.application.salg.query.SalgDto
import no.nilsen.cleana.application.salg.query.SalgQueryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
open class SalgQueryResolver() : GraphQLQueryResolver {
    @Autowired
    lateinit var salgQueryRepo: SalgQueryRepository

    @Autowired
    lateinit var kundeQueryRepo: KundeQueryReporitory

    @Autowired
    lateinit var ansattQueryRepo: AnsattQueryReporitory

    @Autowired
    lateinit var produktQueryRepo: ProduktQueryReporitory

    //Bruker extensions for å se hvordan det gjøres og for gøy.
    fun SalgDto.toHentSalgView(): HentSalgView = HentSalgView(this.id, this.antall, this.totalPris, HentAnsattView(this.selger), HentKundeView(this.kunde), HentProduktView(this.produktQuery))

    fun KundeDto.toHentKundeView(): HentKundeView = HentKundeView(this.id, this.navn)
    fun AnsattDto.toHentAnsattView(): HentAnsattView = HentAnsattView(this.id, this.navn)
    fun ProduktDto.toHentProduktView(): HentProduktView = HentProduktView(this.id, this.beskrivelse, this.pris)

    open fun getSalg(): List<HentSalgView> {
        return HentAlleSalgImpl(salgQueryRepo).hentAlle().map { s -> s.toHentSalgView() }
    }


    open fun getKunde(id: Int): HentKundeView {
        return HentKundeImpl(kundeQueryRepo).hent(id).toHentKundeView()
    }


    open fun getSelger(id: Int): HentAnsattView {
        return HentAnsattImpl(ansattQueryRepo).hent(id).toHentAnsattView()
    }


    open fun getProdukt(id: Int): HentProduktView {
        return HentProduktImpl(produktQueryRepo).hent(id).toHentProduktView()
    }
}