package no.nilsen.cleana.ansatt.query

import no.nilsen.cleana.repository.AnsattReporitory

class HentAnsattImpl(var ansatteRepo: AnsattReporitory) : HentAnsatt{
    override fun hent(id: Int): HentAnsattDto {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hentAlle(): List<HentAnsattDto> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}