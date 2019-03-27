package no.nilsen.cleana.application.ansatt.command

import com.fasterxml.jackson.annotation.JsonCreator

data class EndreAnsattView @JsonCreator constructor(val id: Int, var navn: String) {
}
