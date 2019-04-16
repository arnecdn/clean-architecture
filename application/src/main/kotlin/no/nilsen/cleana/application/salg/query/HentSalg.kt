package no.nilsen.cleana.application.salg.query

interface HentSalg {
    fun hent(id: Int): SalgDto
}
