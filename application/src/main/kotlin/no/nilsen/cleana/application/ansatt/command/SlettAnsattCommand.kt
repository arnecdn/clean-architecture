package no.nilsen.cleana.application.ansatt.command

interface SlettAnsattCommand {
    fun slett(ansatt: SlettAnsattDto)
}