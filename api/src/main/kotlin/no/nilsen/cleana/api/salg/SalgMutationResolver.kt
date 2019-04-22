package no.nilsen.cleana.api.salg

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import no.nilsen.cleana.application.salg.command.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

@Service
open class SalgMutationResolver() : GraphQLMutationResolver {
    fun OpprettSalgView.toOpprettSalgDto(): OpprettSalgDto = OpprettSalgDto(antall = this.antall, ansattId = this.selger, kundeId = this.kunde, produktId = this.produkt)

    @Autowired
    lateinit var salgCommandRepository: SalgCommandRepository

    fun opprettSalg(input: OpprettSalgView) : String{
        OpprettSalgImpl(salgCommandRepository).opprett(input.toOpprettSalgDto())
        return "ok"
    }

    fun endreSalg(input: OpprettSalgView) : String{
        EndreSalgImpl(salgCommandRepository).endre(EndreSalgDto(id = input.id, antall = input.antall, ansattId = input.selger, kundeId = input.kunde, produktId = input.produkt))
        return "ok"
    }

    fun slettSalg(id: Int) : String{
        SlettSalgImpl(salgCommandRepository).slett(SlettSalgDto(id = id))
        return "ok"
    }


}