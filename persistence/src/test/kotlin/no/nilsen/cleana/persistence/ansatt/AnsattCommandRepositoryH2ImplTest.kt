package no.nilsen.cleana.persistence.ansatt

import junit.framework.Assert.assertTrue
import no.nilsen.cleana.domain.ansatt.Ansatt
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.support.AnnotationConfigContextLoader
import javax.transaction.Transactional


@RunWith(SpringJUnit4ClassRunner::class)
@ContextConfiguration(
        classes = arrayOf(PersistenceConfig::class),
        loader = AnnotationConfigContextLoader::class)
@Transactional
open class AnsattCommandRepositoryH2ImplTest {


    @Autowired
    lateinit var ansattRepo: AnsattCommandRepositoryH2Impl

    @Test
    open fun skalTest() {
        ansattRepo.opprett(Ansatt(navn = "Arne Nilsen"))

        val findById = ansattRepo.ansattCrudRepo.findById(1)
        assertTrue(findById.get().navn.equals("Arne Nilsen"))
    }

}