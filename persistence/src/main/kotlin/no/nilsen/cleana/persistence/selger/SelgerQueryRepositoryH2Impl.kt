package no.nilsen.cleana.persistence.selger

import no.nilsen.cleana.application.selger.query.SelgerQueryReporitory
import no.nilsen.cleana.domain.selger.Selger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository


@Repository
open class SelgerQueryRepositoryH2Impl : SelgerQueryReporitory {
    override fun hentAlle(): List<Selger> {
        return selgerCrudRepo.findAll().map { a -> Selger(id = a.id, navn = a.navn) }
    }

    override fun hent(id: Int): Selger {
        val findById = selgerCrudRepo.findById(id)
        return findById.map { a -> Selger(id = a.id, navn = a.navn) }.get()
    }

    @Autowired
    lateinit var selgerCrudRepo: SelgerCrudRepositoryH2
}