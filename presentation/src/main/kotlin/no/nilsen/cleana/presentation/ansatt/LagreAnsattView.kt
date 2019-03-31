package no.nilsen.cleana.application.ansatt.command

import com.fasterxml.jackson.annotation.JsonCreator

data class LagreAnsattView @JsonCreator constructor(var navn: String) {
}
