package no.nilsen.cleana.persistence.ansatt

import no.nilsen.cleana.application.ansatt.command.AnsattCommandRepository
import no.nilsen.cleana.domain.ansatt.Ansatt
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository


@Repository
open class AnsattCommandRepositoryH2Impl : AnsattCommandRepository {
    override fun slett(ansattTilSletting: Ansatt) {
        ansattCrudRepo.delete(AnsattEntitet(id = ansattTilSletting.id))
    }

    override fun endre(oppdatertAnsatt: Ansatt) {
        ansattCrudRepo.save(AnsattEntitet(id = oppdatertAnsatt.id, navn = oppdatertAnsatt.navn))
    }

    @Autowired
    lateinit var ansattCrudRepo: AnsattCrudRepositoryH2

    override fun opprett(nyAnsatt: Ansatt) {
        ansattCrudRepo.save(AnsattEntitet(navn = nyAnsatt.navn))
    }
}