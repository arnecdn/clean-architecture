package no.nilsen.cleana.application.ansatt.query

import no.nilsen.cleana.domain.ansatt.Ansatt

class HentAnsattDto(val id: Int, val navn: String) {
    constructor(a: Ansatt) : this(a.id, a.navn) {}

}
