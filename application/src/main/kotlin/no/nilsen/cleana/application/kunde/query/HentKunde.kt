package no.nilsen.cleana.application.kunde.query


interface HentKunde {
    fun hentAlle(): List<HentKundeDto>
    fun hent(id: Int): HentKundeDto
}
