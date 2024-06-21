package floralgreen.proj.calendar.services;

import floralgreen.proj.calendar.entities.Event;
import floralgreen.proj.calendar.entities.enums.RecordStatusEnum;
import floralgreen.proj.calendar.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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