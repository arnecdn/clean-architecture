package no.nilsen.cleana.persistence.ansatt

import no.nilsen.cleana.domain.ansatt.Ansatt
import no.nilsen.cleana.application.ansatt.command.AnsattCommandRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service


@Service
open class AnsattCommandRepositoryH2Impl : AnsattCommandRepository {

    @Autowired
    lateinit var ansattCrudRepo : AnsattCommandRepositoryH2

    override fun opprett(ansatt: Ansatt) {
        ansattCrudRepo.save(AnsattEntitet(navn = ansatt.navn))
    }
}