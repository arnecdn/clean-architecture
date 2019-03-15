package no.nilsen.cleana.persistence.kunde

import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "Produkt")
open class ProduktEntitet(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int = 0, val beskrivelse: String, val pris: BigDecimal) {

}