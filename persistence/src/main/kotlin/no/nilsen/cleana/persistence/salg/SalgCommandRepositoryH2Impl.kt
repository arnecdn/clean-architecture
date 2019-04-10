package no.nilsen.cleana.persistence.salg

import no.nilsen.cleana.application.salg.command.SalgCommandRepository
import no.nilsen.cleana.domain.salg.Salg
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository


@Repository
open class SalgCommandRepositoryH2Impl : SalgCommandRepository {
    override fun endre(salg: Salg) {
        crudRepo.save(SalgEntitet(id = salg.id, antall = salg.antall, totalPris = salg.totalPris(), selger = salg.selger.id, kunde = salg.kunde.id, produkt = salg.produkt.id))
    }

    override fun slett(salg: Salg) {
        crudRepo.delete(SalgEntitet(id = salg.id))
    }

    override fun opprett(salg: Salg) {
        crudRepo.save(SalgEntitet(antall = salg.antall, totalPris = salg.totalPris(), selger = salg.selger.id, kunde = salg.kunde.id, produkt = salg.produkt.id))
    }

    @Autowired
    lateinit var crudRepo: SalgCrudRepositoryH2

}