package no.nilsen.cleana.persistence.kunde

import no.nilsen.cleana.ansatt.query.KundeQueryReporitory
import no.nilsen.cleana.application.salg.query.SalgQueryRepository
import no.nilsen.cleana.domain.ansatt.Ansatt
import no.nilsen.cleana.domain.kunde.Kunde
import no.nilsen.cleana.domain.produkt.Produkt
import no.nilsen.cleana.domain.salg.Salg
import no.nilsen.cleana.persistence.ansatt.AnsattCrudRepositoryH2
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository


@Repository
open class SalgQueryRepositoryH2Impl : SalgQueryRepository {
    override fun hent(id: Int): Salg? {
        val salg = salgRepo.findById(id).get()

        val kundeSalg = kundeRepo.findById(salg.kunde).map { a  -> Kunde(id=a.id, navn=a.navn)}.get()
        val selger= ansattRepo.findById(salg.selger).map { a  -> Ansatt(id=a.id, navn=a.navn) }.get()
        val produkt = produktRepo.findById(salg.produkt).map { p -> Produkt(id = p.id, beskrivelse = p.beskrivelse, pris = p.pris) }.get()

        return Salg(salg.id, salg.antall.toLong(), selger, kundeSalg, produkt)
    }

    override fun hentSalgPerKunde(kundeId: Int): List<Salg> {

        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hentSalgPerAnsatt(ansattId: Int): List<Salg> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hentAlleSalg(): List<Salg> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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