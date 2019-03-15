package no.nilsen.cleana.persistence.kunde

import no.nilsen.cleana.ansatt.query.KundeQueryReporitory
import no.nilsen.cleana.domain.kunde.Kunde
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository


@Repository
open class KundeQueryRepositoryH2Impl : KundeQueryReporitory {
    override fun hentAlle(): List<Kunde> {
        return crudRepo.findAll().map { a  -> Kunde(id=a.id, navn=a.navn) }
    }

    override fun hent(id: Int): Kunde {
        val findById = crudRepo.findById(id)
        return findById.map { a  -> Kunde(id=a.id, navn=a.navn)}.get()
    }

    @Autowired
    lateinit var crudRepo: KundeCrudRepositoryH2
}