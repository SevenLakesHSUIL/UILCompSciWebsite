package main.data.persistence;

import main.data.UserDetailsImpl;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserDetailsImplRepository extends CrudRepository<UserDetailsImpl, String> {
    UserDetailsImpl findByUsername(String username);
}