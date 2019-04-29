package no.nilsen.cleana.api.salg

import com.fasterxml.jackson.annotation.JsonCreator
import java.time.LocalDateTime

data class SalgsHendelseView @JsonCreator constructor(val id: Int = 0, val salgId: Int, val hendelse: String, val opprettet: LocalDateTime) {

}
