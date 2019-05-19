package main.data.persistence

import main.data.AbstractUserDetails
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Repository
@Transactional
interface AbstractUserDetailsRepository : CrudRepository<AbstractUserDetails, String> {
    fun findByUsername(username: String): Optional<AbstractUserDetails>
}