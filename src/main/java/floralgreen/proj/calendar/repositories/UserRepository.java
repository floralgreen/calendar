package floralgreen.proj.calendar.repositories;

import floralgreen.proj.calendar.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM users AS u WHERE u.id = :userId AND u.record_status_enum = 'A';",nativeQuery = true)
    Optional<User> findActiveUserById(@Param(value = "userId") Long id);

    @Query(value = "SELECT * FROM users AS u WHERE u.record_status_enum = 'A';",nativeQuery = true)
    List<User> findAllActiveUsers();

}
