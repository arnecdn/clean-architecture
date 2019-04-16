package no.nilsen.cleana.application.salg.query

interface HentSalgPrAnsatt {
    fun hentSalgPerAnsatt(ansattId: Int): List<SalgDto>
}
