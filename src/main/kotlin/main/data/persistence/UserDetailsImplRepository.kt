package main.data.persistence

import main.data.UserDetailsImpl
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Repository
@Transactional
interface UserDetailsImplRepository : CrudRepository<UserDetailsImpl, String> {
    fun findByUsername(username: String): Optional<UserDetailsImpl>
}