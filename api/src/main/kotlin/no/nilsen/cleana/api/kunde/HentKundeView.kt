package no.nilsen.cleana.api.kunde

import no.nilsen.cleana.application.kunde.query.KundeDto

data class HentKundeView(val id: Int, val navn: String) {
    constructor(h: KundeDto) : this(h.id, h.navn) {}
}
