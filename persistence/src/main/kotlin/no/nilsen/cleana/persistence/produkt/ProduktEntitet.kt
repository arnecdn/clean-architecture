package no.nilsen.cleana.persistence.produkt

import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "Produkt")
open class ProduktEntitet(@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
                          open val id: Int = 0,
                          open val beskrivelse: String = "",
                          open val pris: BigDecimal = BigDecimal.ZERO) {

}