package no.nilsen.cleana.persistence.kunde

import no.nilsen.cleana.ansatt.command.ProduktCommandRepository
import no.nilsen.cleana.domain.produkt.Produkt
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository


@Repository
open class ProduktCommandRepositoryH2Impl : ProduktCommandRepository {
    override fun opprett(produkt: Produkt) {
        crudRepo.save(ProduktEntitet(beskrivelse = produkt.beskrivelse, pris = produkt.pris))
    }


    @Autowired
    lateinit var crudRepo: ProduktCrudRepositoryH2

}