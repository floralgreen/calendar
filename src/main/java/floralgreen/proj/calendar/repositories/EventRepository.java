package floralgreen.proj.calendar.repositories;


import floralgreen.proj.calendar.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query(value = "SELECT * FROM events AS e WHERE e.id = :eventId AND e.record_status_enum = 'A';",nativeQuery = true)
    Optional<Event> findActiveEventById(@Param(value = "eventId") Long id);

    @Query(value = "SELECT * FROM events AS e WHERE e.record_status_enum = 'A';",nativeQuery = true)
    List<Event> findAllActiveEvent();

}
