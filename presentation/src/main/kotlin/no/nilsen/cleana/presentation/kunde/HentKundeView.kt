package no.nilsen.cleana.presentation.kunde

import no.nilsen.cleana.application.kunde.query.HentKundeDto

data class HentKundeView(val id: Int, val navn: String) {
    constructor(h: HentKundeDto) : this(h.id, h.navn) {}
}
