package floralgreen.proj.calendar.repositories;

import floralgreen.proj.calendar.entities.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarRepository extends JpaRepository<Calendar, Integer> {
}
