package no.nilsen.cleana.api.kunde

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import no.nilsen.cleana.application.kunde.query.HentAlleKunderImpl
import no.nilsen.cleana.application.kunde.query.HentKundeImpl
import no.nilsen.cleana.application.kunde.query.KundeDto
import no.nilsen.cleana.application.kunde.query.KundeQueryReporitory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
open class KundeQueryResolver() : GraphQLQueryResolver {
    @Autowired
    lateinit var kundeQueryRepo: KundeQueryReporitory

    fun KundeDto.toHentKundeView(): HentKundeView = HentKundeView(this.id, this.navn)

    open fun getKunde(id: Int): HentKundeView {
        return HentKundeImpl(kundeQueryRepo).hent(id).toHentKundeView()
    }

    open fun getKunder(): List<HentKundeView> {
        return HentAlleKunderImpl(kundeQueryRepo).hentAlle().map { a -> a.toHentKundeView() }
    }
}