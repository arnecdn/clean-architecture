package no.nilsen.cleana.ansatt.query


interface HentAnsatt {
    fun hentAlle(): List<HentAnsattDto>
    fun hent(id: Int): HentAnsattDto
}
