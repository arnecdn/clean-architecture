package no.nilsen.cleana.repository

import no.nilsen.cleana.salg.Salg

interface SalgRepository{
    fun lagre(nyttSalg: Salg)
    fun hentAlleSalg() : List<Salg>
}