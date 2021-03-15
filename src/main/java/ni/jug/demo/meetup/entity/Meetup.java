package ni.jug.demo.meetup.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table
public class Meetup extends SurrogateIdentifier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meetup_id")
    private Integer id;

    private String subject;

    private String description;

    @Column(name = "meetup_date")
    private LocalDateTime dateTime;

    private Integer duration;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "meetup", orphanRemoval = true)
    private List<MeetupAttendeeDetail> attendeeDetails = new ArrayList<>();

    public Meetup() {
    }

    public Meetup(String subject, String description) {
        this.subject = subject;
        this.description = description;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public List<MeetupAttendeeDetail> getAttendeeDetails() {
        return attendeeDetails;
    }

    public void setAttendeeDetails(List<MeetupAttendeeDetail> attendeeDetails) {
        this.attendeeDetails = attendeeDetails;
    }

    public void addAttendee(Attendee attendee) {
        MeetupAttendeeDetail attendeeDetail = new MeetupAttendeeDetail();
        attendeeDetail.setMeetup(this);
        attendeeDetail.setAttendee(attendee);
        attendeeDetails.add(attendeeDetail);
    }

    public void removeAttendee(Attendee attendee) {
        Optional<MeetupAttendeeDetail> attendeeDetail = attendeeDetails.stream()
                .filter(attDetail -> attDetail.getAttendee().equals(attendee))
                .findAny();
        if (attendeeDetail.isPresent()) {
            attendeeDetails.remove(attendeeDetail);
        }
    }

    public void addDetail(MeetupAttendeeDetail detail) {
        attendeeDetails.add(detail);
    }
}