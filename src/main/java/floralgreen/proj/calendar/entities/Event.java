package floralgreen.proj.calendar.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import floralgreen.proj.calendar.entities.enums.RecordStatusEnum;
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


    @Enumerated(EnumType.STRING)
    @Column(name = "record_status_enum")
    @JsonIgnore
    private RecordStatusEnum recordStatus = RecordStatusEnum.A;



    public Event() {}


    public Event(Long id, String eventTitle, OffsetDateTime eventStart, OffsetDateTime eventEnd, Calendar calendar, RecordStatusEnum recordStatus) {
        this.id = id;
        this.eventTitle = eventTitle;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        this.calendar = calendar;
        this.recordStatus = recordStatus;
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

    public RecordStatusEnum getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(RecordStatusEnum recordStatus) {
        this.recordStatus = recordStatus;
    }
}
