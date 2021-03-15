package ni.jug.demo.meetup.boundary;

import ni.jug.demo.meetup.control.MeetupRepository;
import ni.jug.demo.meetup.entity.Meetup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/meetup", produces = MediaType.APPLICATION_JSON_VALUE)
public class MeetupController {

    @Autowired
    MeetupRepository meetupRepository;

    @GetMapping
    public Iterable<Meetup> findAll() {
        return meetupRepository.findAll();
    }
}
