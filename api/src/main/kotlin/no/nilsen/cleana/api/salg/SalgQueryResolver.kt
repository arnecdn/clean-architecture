package no.nilsen.cleana.api.salg

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import no.nilsen.cleana.api.selger.HentSelgerView
import no.nilsen.cleana.api.kunde.HentKundeView
import no.nilsen.cleana.api.produkt.HentProduktView
import no.nilsen.cleana.application.salg.query.HentAlleSalgImpl
import no.nilsen.cleana.application.salg.query.HentSalgImpl
import no.nilsen.cleana.application.salg.query.SalgDto
import no.nilsen.cleana.application.salg.query.SalgQueryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
open class SalgQueryResolver() : GraphQLQueryResolver {
    @Autowired
    lateinit var salgQueryRepo: SalgQueryRepository

    //Bruker extensions for å se hvordan det gjøres og for gøy.
    fun SalgDto.toHentSalgView(): HentSalgView = HentSalgView(this.id, this.antall, this.totalPris, HentSelgerView(this.selger), HentKundeView(this.kunde), HentProduktView(this.produktQuery))

    open fun getSalgsListe(): List<HentSalgView> {
        return HentAlleSalgImpl(salgQueryRepo).hentAlle().map { s -> s.toHentSalgView() }
    }

    open fun getSalg(id: Int) : HentSalgView {
        return HentSalgImpl(salgQueryRepo).hent(id).toHentSalgView()
    }

}