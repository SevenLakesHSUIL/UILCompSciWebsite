package main.data.service

import main.data.persistence.AbstractUserDetailsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class AbstractUserDetailsService @Autowired
constructor(private val abstractUserDetailsRepository: AbstractUserDetailsRepository) : UserDetailsService {
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails =
        abstractUserDetailsRepository.findByUsername(username).orElseThrow { UsernameNotFoundException(username) }
}
