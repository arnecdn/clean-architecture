package no.nilsen.cleana.persistence.salg

import no.nilsen.cleana.domain.salg.HendelsesType
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "SalgHendelse")
open class SalgHendelseEntitet(@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
                       open val id: Int = 0,
                               open val salgId: Int = 0,
                               open val hendelse: String,
                               open val opprettet: LocalDateTime) {

}