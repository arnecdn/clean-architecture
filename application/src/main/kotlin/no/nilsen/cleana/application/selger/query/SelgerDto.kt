package no.nilsen.cleana.application.selger.query

import no.nilsen.cleana.domain.selger.Selger

class SelgerDto(val id: Int, val navn: String) {
    constructor(a: Selger) : this(a.id, a.navn) {}

}
