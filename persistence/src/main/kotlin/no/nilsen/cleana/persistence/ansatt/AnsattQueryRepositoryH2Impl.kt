package no.nilsen.cleana.persistence.ansatt

import no.nilsen.cleana.application.ansatt.query.AnsattQueryReporitory
import no.nilsen.cleana.domain.ansatt.Ansatt
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository


@Repository
open class AnsattQueryRepositoryH2Impl : AnsattQueryReporitory {
    override fun hentAlle(): List<Ansatt> {
        return ansattCrudRepo.findAll().map { a -> Ansatt(id = a.id, navn = a.navn) }
    }

    override fun hent(id: Int): Ansatt {
        val findById = ansattCrudRepo.findById(id)
        return findById.map { a -> Ansatt(id = a.id, navn = a.navn) }.get()
    }

    @Autowired
    lateinit var ansattCrudRepo: AnsattCrudRepositoryH2
}