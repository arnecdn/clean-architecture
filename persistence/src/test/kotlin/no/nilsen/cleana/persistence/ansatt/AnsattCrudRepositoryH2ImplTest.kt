package no.nilsen.cleana.persistence.ansatt

import junit.framework.Assert.assertTrue
import no.nilsen.cleana.domain.ansatt.Ansatt
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
@ContextConfiguration(classes = arrayOf(PersistenceConfig::class, AnsattCommandRepositoryH2Impl::class))
open class AnsattCrudRepositoryH2ImplTest {


    @Autowired
    lateinit var ansattRepo: AnsattCommandRepositoryH2Impl

    @Test
    fun `skal opprette ansatt`() {
        ansattRepo.opprett(Ansatt(navn = "Arne Nilsen"))

        val findById = ansattRepo.ansattCrudRepo.findById(1)
        assertTrue(findById.get().navn.equals("Arne Nilsen"))
    }


}