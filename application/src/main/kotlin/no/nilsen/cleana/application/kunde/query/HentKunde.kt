package no.nilsen.cleana.application.kunde.query


interface HentKunde {
    fun hent(id: Int): KundeDto
}
