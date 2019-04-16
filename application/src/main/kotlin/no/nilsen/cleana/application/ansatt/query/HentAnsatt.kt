package no.nilsen.cleana.application.ansatt.query


interface HentAnsatt {
    fun hent(id: Int): AnsattDto
}
