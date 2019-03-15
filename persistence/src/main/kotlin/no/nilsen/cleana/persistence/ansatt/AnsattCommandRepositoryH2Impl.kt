package no.nilsen.cleana.persistence.ansatt

import no.nilsen.cleana.application.ansatt.command.AnsattCommandRepository
import no.nilsen.cleana.domain.ansatt.Ansatt
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository


@Repository
open class AnsattCommandRepositoryH2Impl : AnsattCommandRepository {

    @Autowired
    lateinit var ansattCrudRepo: AnsattCrudRepositoryH2

    override fun opprett(ansatt: Ansatt) {
        ansattCrudRepo.save(AnsattEntitet(navn = ansatt.navn))
    }
}