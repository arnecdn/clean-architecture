package no.nilsen.cleana.persistence.ansatt

import no.nilsen.cleana.application.ansatt.command.AnsattCommandRepository
import no.nilsen.cleana.domain.ansatt.Ansatt
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository


@Repository
open class AnsattCommandRepositoryH2Impl : AnsattCommandRepository {
    override fun slett(ansatt: Ansatt) {
        ansattCrudRepo.delete(AnsattEntitet(id = ansatt.id))
    }

    override fun endre(ansatt: Ansatt) {
        ansattCrudRepo.save(AnsattEntitet(id = ansatt.id, navn = ansatt.navn))
    }

    @Autowired
    lateinit var ansattCrudRepo: AnsattCrudRepositoryH2

    override fun opprett(ansatt: Ansatt) {
        ansattCrudRepo.save(AnsattEntitet(navn = ansatt.navn))
    }
}