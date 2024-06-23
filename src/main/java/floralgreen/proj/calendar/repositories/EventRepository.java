package floralgreen.proj.calendar.repositories;


import floralgreen.proj.calendar.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query(value = "SELECT * FROM events AS e WHERE e.id = :eventId AND e.record_status_enum = 'A'",nativeQuery = true)
    Optional<Event> findActiveEventById(@Param(value = "eventId") Long id);

    @Query(value = "SELECT * FROM events AS e WHERE e.record_status_enum = 'A'",nativeQuery = true)
    List<Event> findAllActiveEvent();

    @Query(value = "SELECT e.id, e.event_title, e.event_start, e.event_end, e.calendar_id, e.record_status_enum FROM events as e JOIN calendars as c ON c.id = u.calendar_id WHERE c.calendar_id = :calendarId and e.record_status_enum = 'A' and c.record_status_enum = 'A';", nativeQuery = true)
    List<Event> findAllCalendarEventsByCalendarId(@Param(value = "calendarId") Long calendarId);

    @Query(value = "SELECT e.id, e.event_title, e.event_start, e.event_end, e.calendar_id, e.record_status_enum FROM events as e JOIN calendars as c ON c.id = e.calendar_id WHERE e.record_status_enum = 'A' AND c.record_status_enum = 'A' AND ")
    List<Event> findAllCalendarEventsByCalendarIdByDay(@Param(value = "targetDay") OffsetDateTime targetDay,
                                                       @Param(value = "targetBound")OffsetDateTime targetBound,
                                                       @Param(value = "calendarId") Long calendarId);



}
