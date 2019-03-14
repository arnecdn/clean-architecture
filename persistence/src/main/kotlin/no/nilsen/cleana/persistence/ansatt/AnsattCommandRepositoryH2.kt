package no.nilsen.cleana.persistence.ansatt

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AnsattCommandRepositoryH2 : CrudRepository<AnsattEntitet, Int> {


}
