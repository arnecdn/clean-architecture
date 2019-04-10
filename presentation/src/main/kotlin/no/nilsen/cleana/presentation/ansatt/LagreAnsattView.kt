package no.nilsen.cleana.presentation.ansatt

import com.fasterxml.jackson.annotation.JsonCreator

data class LagreAnsattView @JsonCreator constructor(var navn: String) {

}
