package floralgreen.proj.calendar.repositories;

import floralgreen.proj.calendar.entities.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {

    @Query(value = "SELECT * FROM calendars AS c WHERE c.id = :calendarId AND c.record_status_enum = 'A'",nativeQuery = true)
    Optional<Calendar> findActiveCalendarById(@Param(value = "calendarId") Long id);

    @Query(value = "SELECT * FROM calendars AS c WHERE c.record_status_enum = 'A'",nativeQuery = true)
    List<Calendar> findAllActiveCalendars();

    @Query(value = "SELECT c.id, c.calendar_name, c.user_id, c.record_status_enum FROM calendars as c JOIN users as u ON u.id = c.user_id WHERE c.user_id = :userId and u.record_status_enum = 'A' and c.record_status_enum = 'A'", nativeQuery = true)
    List<Calendar> findAllUserCalendarsByUserId(@Param(value = "userId") Long userId);

}
