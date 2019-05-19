package main.data.service

import main.data.persistence.UserDetailsImplRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl @Autowired
constructor(private val userDetailsImplRepository: UserDetailsImplRepository) : UserDetailsService {
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        return userDetailsImplRepository.findByUsername(username).orElseThrow { UsernameNotFoundException(username) }
    }
}