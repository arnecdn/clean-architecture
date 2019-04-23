package no.nilsen.cleana.application.selger.query


interface HentSelger {
    fun hent(id: Int): SelgerDto
}
