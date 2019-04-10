package no.nilsen.cleana.persistence.ansatt

import javax.persistence.*


@Entity
@Table(name = "Ansatt")
open class AnsattEntitet(@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
                         open val id: Int = 0, open val navn: String = "") {


}