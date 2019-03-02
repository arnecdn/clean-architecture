package no.nilsen.cleana.salg.query

interface HentSalg {
    fun hent(id: Int): HentSalgDto
    fun hentAlle(): List<HentSalgDto>
    fun hentSalgPerKunde(kundeId: Int): List<HentSalgDto>
    fun hentSalgPerAnsatt(ansattId: Int): List<HentSalgDto>
}
