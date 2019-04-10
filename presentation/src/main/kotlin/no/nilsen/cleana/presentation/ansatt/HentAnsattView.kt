package no.nilsen.cleana.presentation.ansatt

import no.nilsen.cleana.application.ansatt.query.HentAnsattDto

data class HentAnsattView(val id: Int, val navn: String) {
    constructor(h: HentAnsattDto) : this(h.id, h.navn) {}
}
