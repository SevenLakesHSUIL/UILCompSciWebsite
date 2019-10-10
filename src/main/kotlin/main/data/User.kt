package main.data

import kotlinx.collections.immutable.persistentListOf
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

import javax.persistence.Entity

@Entity
class User(username: String, password: String, private val isAdmin: Boolean) : UserDetails(username, password) {

    override fun getAuthorities(): Collection<GrantedAuthority> =
        if (isAdmin) {
            ADMIN_ROLES
        } else {
            DATA_ROLES
        }

    companion object {
        @JvmStatic
        private val ADMIN_ROLES = persistentListOf(SimpleGrantedAuthority("ROLE_ADMIN"), SimpleGrantedAuthority("ROLE_DATA"))
        @JvmStatic
        private val DATA_ROLES = persistentListOf(SimpleGrantedAuthority("ROLE_DATA"))
    }
}
