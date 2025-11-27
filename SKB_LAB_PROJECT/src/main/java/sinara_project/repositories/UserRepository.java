package sinara_project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sinara_project.models.user.UserApp;

@Repository
public interface UserRepository extends JpaRepository<UserApp, Long> {
    UserApp findByName(String username);
}
