package no.nilsen.cleana.persistence.kunde

import no.nilsen.cleana.ansatt.query.ProduktQueryReporitory
import no.nilsen.cleana.domain.produkt.Produkt
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository


@Repository
open class ProduktQueryRepositoryH2Impl : ProduktQueryReporitory {
    override fun hentAlle(): List<Produkt> {
        return crudRepo.findAll().map { p -> Produkt(beskrivelse = p.beskrivelse, pris = p.pris) }

    }

    override fun hent(id: Int): Produkt {
        return crudRepo.findById(id).map { p -> Produkt(id = p.id, beskrivelse = p.beskrivelse, pris = p.pris) }.get()
    }

    @Autowired
    lateinit var crudRepo: ProduktCrudRepositoryH2
}