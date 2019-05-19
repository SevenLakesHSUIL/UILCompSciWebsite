package main.data

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

import javax.persistence.Entity
import java.util.Arrays

@Entity
class User(username: String, password: String, private val admin: Boolean) : UserDetailsImpl(username, password) {

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return if (admin) {
            listOf(SimpleGrantedAuthority("ROLE_ADMIN"), SimpleGrantedAuthority("ROLE_DATA"))
        } else {
            listOf(SimpleGrantedAuthority("ROLE_DATA"))
        }
    }
}
