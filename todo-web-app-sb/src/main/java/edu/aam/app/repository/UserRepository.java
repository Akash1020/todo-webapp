package edu.aam.app.repository;

import edu.aam.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    @Query("select u from User u where u.email = ?1")
    User findByUsername(String username);
}
