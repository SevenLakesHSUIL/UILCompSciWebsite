package main.data

import org.springframework.security.core.userdetails.UserDetails

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Inheritance
import javax.persistence.InheritanceType
import javax.validation.constraints.NotBlank

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
abstract class UserDetails(@field:Id
                               @field:NotBlank
                               private val username: String, @field:NotBlank
                               private val password: String) : UserDetails {

    override fun getUsername(): String = username
    override fun getPassword(): String = password

    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isEnabled(): Boolean = true
}
