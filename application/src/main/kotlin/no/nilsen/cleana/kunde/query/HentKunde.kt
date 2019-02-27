package no.nilsen.cleana.ansatt.query


interface HentKunde{
    fun hentAlle() : List<HentKundeDto>
    fun hent(id: Int): HentKundeDto
}
