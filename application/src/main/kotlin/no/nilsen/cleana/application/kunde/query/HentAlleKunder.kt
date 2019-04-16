package no.nilsen.cleana.application.kunde.query


interface HentAlleKunder {
    fun hentAlle(): List<KundeDto>
}
