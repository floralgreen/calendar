package floralgreen.proj.calendar.entities;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String eventTitle;
    private OffsetDateTime eventStart;
    private OffsetDateTime eventEnd;
    @ManyToOne
    @JoinColumn(name = "calendar_id")
    private Calendar calendar;


    public Event() {}

    public Event(Long id, String eventTitle, OffsetDateTime eventStart, OffsetDateTime eventEnd, Calendar calendar) {
        this.id = id;
        this.eventTitle = eventTitle;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        this.calendar = calendar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public OffsetDateTime getEventStart() {
        return eventStart;
    }

    public void setEventStart(OffsetDateTime eventStart) {
        this.eventStart = eventStart;
    }

    public OffsetDateTime getEventEnd() {
        return eventEnd;
    }

    public void setEventEnd(OffsetDateTime eventEnd) {
        this.eventEnd = eventEnd;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }
}
