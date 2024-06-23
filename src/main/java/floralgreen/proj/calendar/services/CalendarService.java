package floralgreen.proj.calendar.services;

import floralgreen.proj.calendar.entities.Calendar;
import floralgreen.proj.calendar.entities.enums.RecordStatusEnum;
import floralgreen.proj.calendar.repositories.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CalendarService {

    @Autowired
    private CalendarRepository calendarRepository;

    /**
     * Saves a new Calendar object on the DB
     * @param calendar
     * @return the Object just created
     */
    public Calendar createCalendar(Calendar calendar) {
        return calendarRepository.save(calendar);
    }

    /**
     * Given a calendarId
     * @param calendarId
     * @return Optional of Calendar with the objectFound
     *  or Optional.empty if the Id doesn't exists or the Calendar has status "D"
     */
    public Optional<Calendar> findActiveCalendarById(Long calendarId){
        return calendarRepository.findActiveCalendarById(calendarId);
    }

    /**
     *
     * @return a list with all the calendars with record status "A"
     */
    public List<Calendar> findAllActiveCalendar() {
        return calendarRepository.findAllActiveCalendars();
    }

    /**
     * Retrieves a list of all calendars associated with a specific user.
     *
     * @param userId The unique identifier of the user whose calendars are to be retrieved.
     * @return A list of Calendar objects associated with the specified user.
     */
    public List<Calendar> findAllUserCalendarsByUserId(Long userId) {
        return calendarRepository.findAllUserCalendarsByUserId(userId);
    }


    /**
     * Given a calendarId and an updated calendar Object
     * @param calendarId
     * @param calendarUpdate
     * @return the updated calendar saved on the DB
     * empty optional if the calendarId is not existing or the calendar has status "D"
     */
    public Optional<Calendar> updateCalendarById(Long calendarId, Calendar calendarUpdate) {
        Optional<Calendar> optionalCalendar = findActiveCalendarById(calendarId);
        if (optionalCalendar.isPresent()) {
            optionalCalendar.get().setCalendarName(calendarUpdate.getCalendarName());
            optionalCalendar.get().setEvents(calendarUpdate.getEvents());
            calendarRepository.save(optionalCalendar.get());
        }
        return optionalCalendar;
    }


    /**
     *
     * @param calendarId given a calendarId
     * @return the deleted user
     * or an empty optional if the user doesn't exists or the user is already deleted
     */
    public Optional<Calendar> deleteCalendarById(Long calendarId) {
        Optional<Calendar> optionalCalendar = findActiveCalendarById(calendarId);
        if(optionalCalendar.isPresent()) {
            optionalCalendar.get().setRecordStatus(RecordStatusEnum.D);
            calendarRepository.save(optionalCalendar.get());
        }
        return optionalCalendar;
    }

}
