package nl.koekoek.repository;

import nl.koekoek.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by its name.
     * @param name user name
     * @return User object
     */
    User findByName(String name);
}
