package no.nilsen.cleana.persistence.selger

import no.nilsen.cleana.application.selger.command.SelgerCommandRepository
import no.nilsen.cleana.domain.selger.Selger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository


@Repository
open class SelgerCommandRepositoryH2Impl : SelgerCommandRepository {
    override fun slett(selgerTilSletting: Selger) {
        selgerCrudRepo.delete(SelgerEntitet(id = selgerTilSletting.id))
    }

    override fun endre(oppdatertSelger: Selger) {
        selgerCrudRepo.save(SelgerEntitet(id = oppdatertSelger.id, navn = oppdatertSelger.navn))
    }

    @Autowired
    lateinit var selgerCrudRepo: SelgerCrudRepositoryH2

    override fun opprett(nySelger: Selger) {
        selgerCrudRepo.save(SelgerEntitet(navn = nySelger.navn))
    }
}