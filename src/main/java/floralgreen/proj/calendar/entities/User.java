package floralgreen.proj.calendar.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import floralgreen.proj.calendar.entities.enums.RecordStatusEnum;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    @OneToMany(mappedBy = "user")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Calendar> userCalendars;

    @Enumerated(EnumType.STRING)
    @Column(name = "record_status_enum")
    @JsonIgnore
    private RecordStatusEnum recordStatus = RecordStatusEnum.A;

    public User() {
    }

    public User(Long id, String username, String password, String firstName, String lastName, String email, List<Calendar> userCalendars, RecordStatusEnum recordStatus) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userCalendars = userCalendars;
        this.recordStatus = recordStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Calendar> getUserCalendars() {
        return userCalendars;
    }

    public void setUserCalendars(List<Calendar> userCalendars) {
        this.userCalendars = userCalendars;
    }

    public RecordStatusEnum getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(RecordStatusEnum recordStatus) {
        this.recordStatus = recordStatus;
    }
}