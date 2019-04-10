package no.nilsen.cleana.persistence.kunde

import javax.persistence.*

@Entity
@Table(name = "Kunde")
open class KundeEntitet(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) open val id: Int = 0, open val navn: String = "")