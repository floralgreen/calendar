package floralgreen.proj.calendar.services;

import floralgreen.proj.calendar.entities.Event;
import floralgreen.proj.calendar.entities.enums.RecordStatusEnum;
import floralgreen.proj.calendar.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    /**
     * Saves the given event to the repository.
     *
     * @param event the event to be saved
     * @return the saved event
     */
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }


    /**
     * Finds an active event by its ID.
     *
     * @param id the ID of the event to be found
     * @return an {@link Optional} containing the found event, or empty if no event is found
     */
    public Optional<Event> findEventById(Long id) {
        return eventRepository.findActiveEventById(id);
    }


    /**
     * Retrieves all active events.
     *
     * @return a list of all active events
     */
    public List<Event> getAllActiveEvents() {
        return eventRepository.findAllActiveEvent();
    }

    /**
     * Retrieves a list of all events associated with a specific calendar.
     *
     * @param calendarId The unique identifier of the calendar whose events are to be retrieved.
     * @return A list of Event objects associated with the specified calendar.
     */
    public List<Event> findAllCalendarEventsByCalendarId(Long calendarId) {
        return eventRepository.findAllCalendarEventsByCalendarId(calendarId);
    }

    /**
     * Retrieves a List of events where the starting OffsetDateTime eventStart is within a specified range of 2 dates
     *
     * @param calendarId
     * @param fromDate starting date
     * @param toDate ending bound
     * @return
     */
    public List<Event> findAllEventsByCalendarIdWithinRange(Long calendarId, OffsetDateTime fromDate, OffsetDateTime toDate) {
        List<Event> eventList = eventRepository.findAllCalendarEventsByCalendarIdByDay(fromDate, toDate, calendarId);
        return eventList;
    }



    /**
     * Updates an existing active event with the given event details.
     *
     * @param id the ID of the event to be updated
     * @param event the event details to update
     * @return an {@link Optional} containing the updated event, or empty if no event is found
     */
    public Optional<Event> updateEvent(Long id, Event event) {
        Optional<Event> eventOptional = eventRepository.findActiveEventById(id);
        if (eventOptional.isPresent()) {
            eventOptional.get().setEventTitle(event.getEventTitle());
            eventOptional.get().setEventStart(event.getEventStart());
            eventOptional.get().setEventEnd(event.getEventEnd());
            eventRepository.save(eventOptional.get());
        }
        return eventOptional;
    }


    /**
     * Marks an active event as deleted by setting its record status to 'D'.
     *
     * @param id the ID of the event to be deleted
     * @return an {@link Optional} containing the event marked as deleted, or empty if no event is found
     */
    public Optional<Event> deleteEventById(Long id) {
        Optional<Event> eventOptional = eventRepository.findActiveEventById(id);
        if (eventOptional.isPresent()) {
            eventOptional.get().setRecordStatus(RecordStatusEnum.D);
            eventRepository.save(eventOptional.get());
        }
        return eventOptional;
    }

}
