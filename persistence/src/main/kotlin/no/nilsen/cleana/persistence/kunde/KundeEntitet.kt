package no.nilsen.cleana.persistence.kunde

import javax.persistence.*

@Entity
@Table(name = "Kunde")
open class KundeEntitet(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int = 0, val navn: String = "")