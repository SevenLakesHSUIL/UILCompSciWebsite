package main.service

import main.data.persistence.UserDetailsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService as SpringUserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsService @Autowired
constructor(private val userDetailsRepository: UserDetailsRepository) : SpringUserDetailsService {
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails =
        userDetailsRepository.findByUsername(username).orElseThrow { UsernameNotFoundException("User $username not found") }
}
