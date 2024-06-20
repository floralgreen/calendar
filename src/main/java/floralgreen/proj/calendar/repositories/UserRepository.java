package floralgreen.proj.calendar.repositories;

import floralgreen.proj.calendar.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
