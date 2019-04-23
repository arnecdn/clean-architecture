package no.nilsen.cleana.api.selger

import com.fasterxml.jackson.annotation.JsonCreator

data class LagreSelgerView @JsonCreator constructor(var navn: String) {

}
