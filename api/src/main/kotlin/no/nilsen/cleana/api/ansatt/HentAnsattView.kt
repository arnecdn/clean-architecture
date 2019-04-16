package no.nilsen.cleana.api.ansatt

import no.nilsen.cleana.application.ansatt.query.AnsattDto

data class HentAnsattView(val id: Int, val navn: String) {
    constructor(h: AnsattDto) : this(h.id, h.navn) {}
}
