package no.nilsen.cleana.persistence.selger

import junit.framework.Assert.assertTrue
import no.nilsen.cleana.domain.selger.Selger
import no.nilsen.cleana.persistence.PersistenceConfig
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import javax.transaction.Transactional


@ExtendWith(SpringExtension::class)
@DataJpaTest
@Transactional
@ContextConfiguration(classes = arrayOf(PersistenceConfig::class, SelgerCommandRepositoryH2Impl::class))
open class SelgerCrudRepositoryH2ImplTest {


    @Autowired
    lateinit var ansattRepo: SelgerCommandRepositoryH2Impl

    @Test
    fun `skal opprette ansatt`() {
        ansattRepo.opprett(Selger(navn = "Arne Nilsen"))

        val findById = ansattRepo.selgerCrudRepo.findById(1)
        assertTrue(findById.get().navn.equals("Arne Nilsen"))
    }


}