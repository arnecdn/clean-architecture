package no.nilsen.cleana.persistence.kunde

import no.nilsen.cleana.ansatt.command.KundeCommandRepository
import no.nilsen.cleana.application.salg.command.SalgCommandRepository
import no.nilsen.cleana.domain.kunde.Kunde
import no.nilsen.cleana.domain.salg.Salg
import no.nilsen.cleana.persistence.salg.SalgEntitet
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository


@Repository
open class SalgCommandRepositoryH2Impl : SalgCommandRepository{
    override fun opprett(salg: Salg) {
        crudRepo.save(SalgEntitet(antall=salg.antall, totalPris = salg.totalPris(),selger = salg.selger.id, kunde=salg.kunde.id, produkt = salg.produkt.id))
    }

    @Autowired
    lateinit var crudRepo: SalgCrudRepositoryH2

}