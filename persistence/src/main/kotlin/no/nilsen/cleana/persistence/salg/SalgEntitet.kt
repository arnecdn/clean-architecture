package no.nilsen.cleana.persistence.salg

import no.nilsen.cleana.domain.ansatt.Ansatt
import no.nilsen.cleana.persistence.kunde.ProduktEntitet
import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name="Salg")
open class SalgEntitet(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int = 0, val antall: Long=0, val totalPris: BigDecimal = BigDecimal.ZERO, val selger: Int=0, val kunde: Int=0, val produkt: Int=0) {

}