package no.nilsen.cleana.application.ansatt.query


interface HentAlleAnsatte {
    fun hentAlle(): List<AnsattDto>
}
