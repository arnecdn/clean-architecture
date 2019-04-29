package no.nilsen.cleana.api.salg

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import no.nilsen.cleana.application.salg.query.HentAlleSalgsHendelserImpl
import no.nilsen.cleana.application.salg.query.SalgsHendelseQueryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
open class SalgsHendelseQueryResolver() : GraphQLQueryResolver {
    @Autowired
    lateinit var repo: SalgsHendelseQueryRepository

    fun getSalgsHendelser(offset: Int, antall: Int): List<SalgsHendelseView> {
        return HentAlleSalgsHendelserImpl(repo).hentAlle(offset, antall).map { h -> SalgsHendelseView(id = h.id, salgId = h.salgId, hendelse = h.hendelse, opprettet = h.opprettet) }
    }

    fun getSalgsHendelse(id: Int): SalgsHendelseView {
        return SalgsHendelseView(1, 1,"NYTT", LocalDateTime.now() )
    }
}