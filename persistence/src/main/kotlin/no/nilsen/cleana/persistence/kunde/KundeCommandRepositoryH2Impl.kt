package no.nilsen.cleana.persistence.kunde

import no.nilsen.cleana.application.kunde.command.KundeCommandRepository
import no.nilsen.cleana.domain.kunde.Kunde
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository


@Repository
open class KundeCommandRepositoryH2Impl : KundeCommandRepository {
    override fun slett(kunde: Kunde) {
        crudRepo.delete(KundeEntitet(id = kunde.id))
    }

    override fun endre(kunde: Kunde) {
        crudRepo.save(KundeEntitet(id = kunde.id, navn = kunde.navn))
    }

    override fun opprett(kunde: Kunde) {
        crudRepo.save(KundeEntitet(navn = kunde.navn))
    }

    @Autowired
    lateinit var crudRepo: KundeCrudRepositoryH2

}