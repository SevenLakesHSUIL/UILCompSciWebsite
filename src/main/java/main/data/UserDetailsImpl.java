package main.data;

import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotBlank;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class UserDetailsImpl implements UserDetails {
    @Id
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    protected UserDetailsImpl() {

    }

    public UserDetailsImpl(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
