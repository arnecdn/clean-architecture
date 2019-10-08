package no.nilsen.cleana.persistence.salg

import no.nilsen.cleana.application.salg.query.SalgsHendelseQueryRepository
import no.nilsen.cleana.domain.salg.HendelsesType
import no.nilsen.cleana.domain.salg.SalgsHendelse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository


@Repository
open class SalgHendelseQueryRepositoryH2Impl : SalgsHendelseQueryRepository {
    @Autowired
    lateinit var salgsHendelseCrudRepositoryH2: SalgsHendelseCrudRepositoryH2


    override fun hent(offset: Int, antall: Int): List<SalgsHendelse> {
        if(offset== 0){
            return mapAndSort(salgsHendelseCrudRepositoryH2.hentSalgsHendelser(antall = antall))
        }
        return mapAndSort(salgsHendelseCrudRepositoryH2.hentSalgsHendelser(offset, antall))
    }

    private fun mapAndSort(salgsHendelser: List<SalgHendelseEntitet>): List<SalgsHendelse> {
        return salgsHendelser
                .map { e -> SalgsHendelse(e.id, e.salgId, HendelsesType.valueOf(e.hendelse), e.opprettet) }
                .sortedByDescending { s -> s.id }
    }


}