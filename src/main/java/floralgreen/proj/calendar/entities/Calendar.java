package floralgreen.proj.calendar.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import floralgreen.proj.calendar.entities.enums.RecordStatusEnum;
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
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Event> events;



    @Enumerated(EnumType.STRING)
    @Column(name = "record_status_enum")
    @JsonIgnore
    private RecordStatusEnum recordStatus = RecordStatusEnum.A;


    public Calendar() {
    }

    public Calendar(Long id, String calendarName, User user, List<Event> events, RecordStatusEnum recordStatus) {
        this.id = id;
        this.calendarName = calendarName;
        this.user = user;
        this.events = events;
        this.recordStatus = recordStatus;
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

    public RecordStatusEnum getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(RecordStatusEnum recordStatus) {
        this.recordStatus = recordStatus;
    }
}