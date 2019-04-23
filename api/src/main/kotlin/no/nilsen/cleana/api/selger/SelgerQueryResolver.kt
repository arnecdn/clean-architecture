package no.nilsen.cleana.api.selger

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import no.nilsen.cleana.application.selger.query.SelgerDto
import no.nilsen.cleana.application.selger.query.SelgerQueryReporitory
import no.nilsen.cleana.application.selger.query.HentAlleSelgerImpl
import no.nilsen.cleana.application.selger.query.HentSelgerImpl
import no.nilsen.cleana.application.produkt.query.ProduktQueryReporitory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
open class SelgerQueryResolver() : GraphQLQueryResolver {
    @Autowired
    lateinit var selgerQueryRepo: SelgerQueryReporitory

    @Autowired
    lateinit var produktQueryRepo: ProduktQueryReporitory

    fun SelgerDto.toHentSelgerView(): HentSelgerView = HentSelgerView(this.id, this.navn)

    open fun getSelger(id: Int): HentSelgerView {
        return HentSelgerImpl(selgerQueryRepo).hent(id).toHentSelgerView()
    }

    open fun getSelgere(): List<HentSelgerView> {
        return HentAlleSelgerImpl(selgerQueryRepo).hentAlle().map { a -> a.toHentSelgerView()}
    }
}