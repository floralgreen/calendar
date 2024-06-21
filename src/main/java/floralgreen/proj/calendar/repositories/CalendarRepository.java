package floralgreen.proj.calendar.repositories;

import floralgreen.proj.calendar.entities.Calendar;
import floralgreen.proj.calendar.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {

    @Query(value = "SELECT * FROM calendar AS c WHERE c.id = :calendarId AND c.record_status_enum = 'A';",nativeQuery = true)
    Optional<Calendar> findActiveCalendarById(@Param(value = "calendarId") Long id);

    @Query(value = "SELECT * FROM calendar AS c WHERE c.record_status_enum = 'A';",nativeQuery = true)
    List<Calendar> findAllActiveCalendars();

}
