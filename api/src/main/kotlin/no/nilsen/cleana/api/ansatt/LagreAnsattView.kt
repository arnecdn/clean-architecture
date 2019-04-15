package no.nilsen.cleana.api.ansatt

import com.fasterxml.jackson.annotation.JsonCreator

data class LagreAnsattView @JsonCreator constructor(var navn: String) {

}
