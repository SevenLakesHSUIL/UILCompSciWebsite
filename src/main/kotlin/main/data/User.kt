package main.data

import kotlinx.collections.immutable.immutableListOf
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

import javax.persistence.Entity

@Entity
class User(username: String, password: String, private val admin: Boolean) : UserDetails(username, password) {

    override fun getAuthorities(): Collection<GrantedAuthority> =
        if (admin) {
            immutableListOf(SimpleGrantedAuthority("ROLE_ADMIN"), SimpleGrantedAuthority("ROLE_DATA"))
        } else {
            immutableListOf(SimpleGrantedAuthority("ROLE_DATA"))
        }
}
