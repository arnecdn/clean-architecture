package no.nilsen.cleana.api.kunde.command

import com.fasterxml.jackson.annotation.JsonCreator

data class LagreKundeView @JsonCreator constructor(var navn: String) {
}
