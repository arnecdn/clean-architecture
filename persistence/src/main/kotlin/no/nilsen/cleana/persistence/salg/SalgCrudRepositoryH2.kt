package no.nilsen.cleana.persistence.kunde

import no.nilsen.cleana.persistence.salg.SalgEntitet
import org.springframework.data.repository.CrudRepository


interface SalgCrudRepositoryH2 : CrudRepository<SalgEntitet, Int> {

    fun findByKunde(id: Int): List<SalgEntitet>
    fun findBySelger(id: Int): List<SalgEntitet>
}
