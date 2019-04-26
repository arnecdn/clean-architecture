package no.nilsen.cleana.persistence.salg

import no.nilsen.cleana.application.salg.command.SalgCommandRepository
import no.nilsen.cleana.domain.salg.HendelsesType
import no.nilsen.cleana.domain.salg.Salg
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime


@Repository
open class SalgCommandRepositoryH2Impl : SalgCommandRepository {

    @Transactional
    override fun endre(salg: Salg) {
        crudRepo.save(SalgEntitet(id = salg.id, antall = salg.antall, totalPris = salg.totalPris(), selger = salg.selger.id, kunde = salg.kunde.id, produkt = salg.produkt.id))
        salgsHendelseCrudRepo.save(SalgHendelseEntitet(salgId = salg.id, hendelse = HendelsesType.ENDRE.name, opprettet = LocalDateTime.now()))
    }

    @Transactional
    override fun slett(salg: Salg) {
        crudRepo.delete(SalgEntitet(id = salg.id))
        salgsHendelseCrudRepo.save(SalgHendelseEntitet(salgId = salg.id, hendelse = HendelsesType.SLETTE.name, opprettet = LocalDateTime.now()))
    }
    @Transactional
    override fun opprett(salg: Salg) {
        val persistertSalg = crudRepo.save(SalgEntitet(antall = salg.antall, totalPris = salg.totalPris(), selger = salg.selger.id, kunde = salg.kunde.id, produkt = salg.produkt.id))
        salgsHendelseCrudRepo.save(SalgHendelseEntitet(salgId = persistertSalg.id, hendelse = HendelsesType.NY.name, opprettet = LocalDateTime.now()))
    }

    @Autowired
    lateinit var crudRepo: SalgCrudRepositoryH2

    @Autowired
    lateinit var salgsHendelseCrudRepo : SalgsHendelseCrudRepositoryH2

}