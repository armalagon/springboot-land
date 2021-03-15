package ni.jug.demo.meetup.control;

import ni.jug.demo.meetup.entity.Attendee;
import ni.jug.demo.meetup.entity.Meetup;
import ni.jug.demo.meetup.entity.MeetupAttendeeDetail;
import org.hibernate.collection.internal.PersistentBag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@SpringBootTest
class MeetupServiceTest {

	@Autowired
	MeetupRepository meetupRepository;

	@PersistenceContext
	EntityManager em;

	@Test
	void findAll_QueryAll_TwoMeetupsPreloaded() {
		Iterable<Meetup> meetups = meetupRepository.findAll();

		int count = 0;
		for (Meetup meetup : meetups) {
			++count;
		}

		Assertions.assertEquals(2, count);
	}

	@Transactional
	@Test
	void merge_FetchMeetupAndDetachAndUpdateHeader_ChangesArePersisted() {
		Meetup detached = meetupRepository.findById(1).get();

		// Detach
		em.detach(detached);

		// Make some changes
		detached.setDuration(45);

		// Reattach
		Meetup reattached = em.merge(detached);

		// Persist the changes
		em.flush();

		Meetup managed = em.find(Meetup.class, 1);

		Assertions.assertTrue(reattached == managed);
		Assertions.assertEquals(detached.getSubject(), reattached.getSubject());
		Assertions.assertEquals(detached.getDescription(), reattached.getDescription());
		Assertions.assertEquals(detached.getDuration(), reattached.getDuration());
	}

	@Transactional
	@Test
	void merge_CreateDetachedCopyOfExistingMeetupWithNoDetail_DetailsAreDeleted() {
		Meetup firstMeetup = meetupRepository.findById(1).get();

		em.detach(firstMeetup);

		// Create a copy without details
		Meetup detachedMeetup = new Meetup(firstMeetup.getSubject(), firstMeetup.getDescription());
		detachedMeetup.setId(firstMeetup.getId());
		detachedMeetup.setDateTime(firstMeetup.getDateTime());
		detachedMeetup.setDuration(45); // New duration

		// Reattach
		Meetup reattached = em.merge(detachedMeetup);

		// Persist the changes
		em.flush();

		Meetup managed = em.find(Meetup.class, 1);

		Assertions.assertTrue(reattached == managed);
		Assertions.assertEquals(detachedMeetup.getDuration(), reattached.getDuration());
		Assertions.assertTrue(reattached.getAttendeeDetails().isEmpty());

		String jpql = "SELECT d FROM MeetupAttendeeDetail d WHERE d.meetup.id = :meetupId";
		List<MeetupAttendeeDetail> details = em.createQuery(jpql, MeetupAttendeeDetail.class)
				.setParameter("meetupId", 1)
				.getResultList();
		Assertions.assertTrue(details.isEmpty());
	}

	@Transactional
	@Test
	void merge_CreateDetachedCopyOfExistingMeetupWithProxiedDetails_HeaderIsUpdatedAndDetailsWithNoChanges() {
		Meetup firstMeetup = meetupRepository.findById(1).get();

		em.detach(firstMeetup);

		// Create a copy with a proxied detail
		Meetup detachedMeetup = new Meetup(firstMeetup.getSubject(), firstMeetup.getDescription());
		detachedMeetup.setId(firstMeetup.getId());
		detachedMeetup.setDateTime(firstMeetup.getDateTime());
		detachedMeetup.setDuration(45); // New duration
		detachedMeetup.setAttendeeDetails(new PersistentBag());

		// Reattach
		Meetup reattached = em.merge(detachedMeetup);

		// Persist the changes
		em.flush();

		Meetup managed = em.find(Meetup.class, 1);

		Assertions.assertTrue(reattached == managed);
		Assertions.assertEquals(detachedMeetup.getDuration(), reattached.getDuration());
		Assertions.assertEquals(2, reattached.getAttendeeDetails().size());
	}

	@Transactional
	@Test
	void merge_CreateDetachedCopyOfExistingMeetupWithSomeChangesInDetails_OrphanDetailIsDeletedAndNewDetailIsCreated() {
		Meetup firstMeetup = meetupRepository.findById(1).get();
		firstMeetup.getAttendeeDetails().size();

		em.detach(firstMeetup);

		// Create a copy with a few changes
		Meetup detachedMeetup = new Meetup(firstMeetup.getSubject().toUpperCase(), firstMeetup.getDescription().toUpperCase());
		detachedMeetup.setId(firstMeetup.getId());
		detachedMeetup.setDateTime(firstMeetup.getDateTime());
		detachedMeetup.setDuration(45); // New duration

		// Create a copy of the 1st detail
		MeetupAttendeeDetail detail = firstMeetup.getAttendeeDetails().get(0);
		MeetupAttendeeDetail detachedDetail = new MeetupAttendeeDetail();
		detachedDetail.setId(detail.getId());
		Attendee attendee = new Attendee();
		attendee.setId(1); // Armando
		detachedDetail.setAttendee(attendee);
		detachedDetail.setMeetup(detachedMeetup);
		detachedDetail.setRegistrationDate(detail.getRegistrationDate());
		detachedMeetup.addDetail(detachedDetail);

		// Create a fresh new 2nd detail
		attendee = new Attendee();
		attendee.setId(3); // Roberto
		detachedMeetup.addAttendee(attendee);

		// Reattach
		Meetup reattached = em.merge(detachedMeetup);

		// Persist the changes
		em.flush();

		Meetup managed = em.find(Meetup.class, 1);

		Assertions.assertTrue(reattached == managed);
		Assertions.assertEquals(detachedMeetup.getDuration(), reattached.getDuration());
		Assertions.assertEquals(detachedMeetup.getSubject(), reattached.getSubject());
		Assertions.assertEquals(detachedMeetup.getDescription(), reattached.getDescription());
		Assertions.assertEquals(2, reattached.getAttendeeDetails().size());
		Assertions.assertEquals(firstMeetup.getAttendeeDetails().get(0).getId(), reattached.getAttendeeDetails().get(0).getId());
		Assertions.assertNotEquals(firstMeetup.getAttendeeDetails().get(1).getId(), reattached.getAttendeeDetails().get(1).getId());
	}
}
