package floralgreen.proj.calendar.repositories;

import floralgreen.proj.calendar.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
