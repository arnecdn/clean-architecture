package no.nilsen.cleana.application.ansatt.query


interface HentAnsatt {
    fun hentAlle(): List<HentAnsattDto>
    fun hent(id: Int): HentAnsattDto
}
