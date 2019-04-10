package no.nilsen.cleana.persistence.salg

import no.nilsen.cleana.application.salg.query.SalgQueryRepository
import no.nilsen.cleana.domain.ansatt.Ansatt
import no.nilsen.cleana.domain.kunde.Kunde
import no.nilsen.cleana.domain.produkt.Produkt
import no.nilsen.cleana.domain.salg.Salg
import no.nilsen.cleana.persistence.ansatt.AnsattCrudRepositoryH2
import no.nilsen.cleana.persistence.kunde.KundeCrudRepositoryH2
import no.nilsen.cleana.persistence.produkt.ProduktCrudRepositoryH2
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository


@Repository
open class SalgQueryRepositoryH2Impl : SalgQueryRepository {
    override fun hent(id: Int): Salg? {
        val salgEntitet = salgRepo.findById(id).get()
        return byggSalgsAggregatEnkelt(salgEntitet)
    }

    override fun hentSalgPerKunde(kundeId: Int): List<Salg> {
        val salgsEntiteter = salgRepo.findByKunde(kundeId)
        return byggSalgsAggregat(salgsEntiteter)
    }

    override fun hentSalgPerAnsatt(ansattId: Int): List<Salg> {
        val salgsEntiteter = salgRepo.findBySelger(ansattId)
        return byggSalgsAggregat(salgsEntiteter)
    }


    override fun hentAlleSalg(): List<Salg> {
        val alleSalgsEntiteter = salgRepo.findAll().toList()
        return byggSalgsAggregat(alleSalgsEntiteter)

    }


    private fun byggSalgsAggregat(salgsEntiteter: List<SalgEntitet>): List<Salg> {
        return salgsEntiteter.map { s -> byggSalgsAggregatEnkelt(s) }
    }

    private fun byggSalgsAggregatEnkelt(salg: SalgEntitet): Salg {
        val eksisterendeSalg = kundeRepo.findById(salg.kunde)
        if (!eksisterendeSalg.isPresent) {
            return Salg()
        }

        val kundeSalg = eksisterendeSalg.map { a -> Kunde(id = a.id, navn = a.navn) }.get()
        val selger = ansattRepo.findById(salg.selger).map { a -> Ansatt(id = a.id, navn = a.navn) }.get()
        val produkt = produktRepo.findById(salg.produkt).map { p -> Produkt(id = p.id, beskrivelse = p.beskrivelse, pris = p.pris) }.get()

        return Salg(salg.id, salg.antall.toLong(), selger, kundeSalg, produkt)
    }


    @Autowired
    lateinit var salgRepo: SalgCrudRepositoryH2


    @Autowired
    lateinit var kundeRepo: KundeCrudRepositoryH2

    @Autowired
    lateinit var ansattRepo: AnsattCrudRepositoryH2

    @Autowired
    lateinit var produktRepo: ProduktCrudRepositoryH2

}