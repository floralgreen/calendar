package floralgreen.proj.calendar.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "calendars")
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String calendarName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "calendar")
    private List<Event> events;

    public Calendar() {
    }

    public Calendar(Long id, String calendarName, User user, List<Event> events) {
        this.id = id;
        this.calendarName = calendarName;
        this.user = user;
        this.events = events;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCalendarName() {
        return calendarName;
    }

    public void setCalendarName(String calendarName) {
        this.calendarName = calendarName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}