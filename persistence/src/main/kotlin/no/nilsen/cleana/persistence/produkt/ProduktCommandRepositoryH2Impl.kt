package no.nilsen.cleana.persistence.kunde

import no.nilsen.cleana.ansatt.command.ProduktCommandRepository
import no.nilsen.cleana.produkt.Produkt
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository


@Repository
open class ProduktCommandRepositoryH2Impl : ProduktCommandRepository {
    override fun endre(produkt: Produkt) {
        crudRepo.save(ProduktEntitet(id=produkt.id, beskrivelse = produkt.beskrivelse, pris = produkt.pris))
    }

    override fun slett(produkt: Produkt) {
        crudRepo.delete(ProduktEntitet(id=produkt.id))

    }

    override fun opprett(produkt: Produkt) {
        crudRepo.save(ProduktEntitet(beskrivelse = produkt.beskrivelse, pris = produkt.pris))
    }


    @Autowired
    lateinit var crudRepo: ProduktCrudRepositoryH2

}