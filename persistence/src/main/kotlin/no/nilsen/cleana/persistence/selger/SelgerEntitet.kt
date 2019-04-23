package no.nilsen.cleana.persistence.selger

import javax.persistence.*


@Entity
@Table(name = "Selger")
open class SelgerEntitet(@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
                         open val id: Int = 0, open val navn: String = "") {


}