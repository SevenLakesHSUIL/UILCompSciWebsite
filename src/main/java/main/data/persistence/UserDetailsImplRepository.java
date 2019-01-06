package main.data.persistence;

import main.data.UserDetailsImpl;
import org.springframework.data.repository.CrudRepository;

public interface UserDetailsImplRepository extends CrudRepository<UserDetailsImpl, String> {
    UserDetailsImpl findByUsername(String username);
}