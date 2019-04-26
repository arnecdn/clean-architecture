package no.nilsen.cleana.domain.salg

import java.time.LocalDateTime

class SalgsHendelse( val id: Int = 0, val salgsId: Int, val hendelseType: HendelsesType, val opprettet: LocalDateTime = LocalDateTime.now()) {
}