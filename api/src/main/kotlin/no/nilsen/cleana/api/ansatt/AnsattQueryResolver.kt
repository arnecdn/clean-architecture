package no.nilsen.cleana.api.ansatt

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import no.nilsen.cleana.api.ansatt.HentAnsattView
import no.nilsen.cleana.api.kunde.HentKundeView
import no.nilsen.cleana.api.produkt.HentProduktView
import no.nilsen.cleana.application.ansatt.query.AnsattDto
import no.nilsen.cleana.application.ansatt.query.AnsattQueryReporitory
import no.nilsen.cleana.application.ansatt.query.HentAlleAnsatteImpl
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
open class AnsattQueryResolver() : GraphQLQueryResolver {
    @Autowired
    lateinit var ansattQueryRepo: AnsattQueryReporitory

    @Autowired
    lateinit var produktQueryRepo: ProduktQueryReporitory

    fun AnsattDto.toHentAnsattView(): HentAnsattView = HentAnsattView(this.id, this.navn)

    open fun getSelger(id: Int): HentAnsattView {
        return HentAnsattImpl(ansattQueryRepo).hent(id).toHentAnsattView()
    }

    open fun getSelgere(): List<HentAnsattView> {
        return HentAlleAnsatteImpl(ansattQueryRepo).hentAlle().map {a -> a.toHentAnsattView()}
    }
}