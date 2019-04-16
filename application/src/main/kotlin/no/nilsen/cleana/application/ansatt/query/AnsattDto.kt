package no.nilsen.cleana.application.ansatt.query

import no.nilsen.cleana.domain.ansatt.Ansatt

class AnsattDto(val id: Int, val navn: String) {
    constructor(a: Ansatt) : this(a.id, a.navn) {}

}
