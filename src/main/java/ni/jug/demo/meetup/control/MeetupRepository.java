package ni.jug.demo.meetup.control;

import ni.jug.demo.meetup.entity.Meetup;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MeetupRepository extends CrudRepository<Meetup, Integer> {

    @Override
    @Query("SELECT DISTINCT m FROM Meetup m LEFT JOIN FETCH m.attendeeDetails d")
    Iterable<Meetup> findAll();
}
