package no.nilsen.cleana.api.selger

import no.nilsen.cleana.application.selger.query.SelgerDto

data class HentSelgerView(val id: Int, val navn: String) {
    constructor(h: SelgerDto) : this(h.id, h.navn) {}
}
