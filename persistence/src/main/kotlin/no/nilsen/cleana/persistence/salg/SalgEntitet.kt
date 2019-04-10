package no.nilsen.cleana.persistence.salg

import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "Salg")
open class SalgEntitet(@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
                       open val id: Int = 0,
                       open val antall: Long = 0,
                       open val totalPris: BigDecimal = BigDecimal.ZERO,
                       open val selger: Int = 0,
                       open val kunde: Int = 0,
                       open val produkt: Int = 0) {

}