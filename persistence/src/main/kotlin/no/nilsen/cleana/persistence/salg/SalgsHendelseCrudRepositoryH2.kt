package no.nilsen.cleana.persistence.salg

import no.nilsen.cleana.domain.salg.SalgsHendelse
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository


interface SalgsHendelseCrudRepositoryH2 : CrudRepository<SalgHendelseEntitet, Int> {
    @Query(" SELECT * FROM SALG_HENDELSE WHERE id > :offset ORDER BY id ASC LIMIT :antall"
            , nativeQuery = true)
    fun hentSalgsHendelser(offset: Int, antall: Int): List<SalgHendelseEntitet>

    @Query(" SELECT * FROM SALG_HENDELSE ORDER BY id ASC LIMIT :antall ", nativeQuery = true)
    fun hentSalgsHendelser(antall: Int): List<SalgHendelseEntitet>
}
