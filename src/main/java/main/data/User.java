package main.data;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.Entity;
import java.util.Arrays;
import java.util.Collection;

@Entity
public class User extends UserDetailsImpl {
    private boolean admin;

    protected User() {

    }

    public User(String username, String password, boolean admin) {
        super(username, password);
        this.admin = admin;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(admin) {
            return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_DATA"));
        } else {
            return Arrays.asList(new SimpleGrantedAuthority("ROLE_DATA"));
        }
    }
}
