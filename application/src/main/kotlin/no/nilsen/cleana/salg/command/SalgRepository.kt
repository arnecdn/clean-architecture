package no.nilsen.cleana.salg.command

import no.nilsen.cleana.salg.Salg

interface SalgRepository{
    fun lagre(nyttSalg: Salg)
}