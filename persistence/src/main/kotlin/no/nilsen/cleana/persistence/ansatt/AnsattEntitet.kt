package no.nilsen.cleana.persistence.ansatt

import javax.persistence.*


@Entity
@Table(name="Ansatt")
open class AnsattEntitet (@Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
                     val id: Int=0, val navn: String=""){


}