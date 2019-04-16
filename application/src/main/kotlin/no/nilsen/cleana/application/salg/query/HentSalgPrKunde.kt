package no.nilsen.cleana.application.salg.query

interface HentSalgPrKunde {
    fun hentSalgPerKunde(kundeId: Int): List<SalgDto>
}
