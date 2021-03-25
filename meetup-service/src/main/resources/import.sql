insert into meetup(subject, description, meetup_date, duration) values('Creating a RESTful web service with Spring Boot', 'A hands-on experience', CURRENT_TIMESTAMP(3), 60);
insert into meetup(subject, description, meetup_date, duration) values('Creating backend with Spring Boot', 'Personal notes', CURRENT_TIMESTAMP(3), 45);

insert into attendee(name, last_name, gender) values('Armando Jose', 'Alaniz Aragon', 'MALE');
insert into attendee(name, last_name, gender) values('Omar Danilo', 'Berroteran Silva', 'MALE');
insert into attendee(name, last_name, gender) values('Roberto', 'Rodriguez', 'MALE');
insert into attendee(name, last_name, gender) values('Jorge', 'Solorzano', 'MALE');
insert into attendee(name, last_name, gender) values('Luis Alberto', 'Guido', 'MALE');
insert into attendee(name, last_name, gender) values('Felix', 'Medina', 'MALE');

insert into meetup_attendee_detail(meetup_id, attendee_id, registration_date) values(1, 1, CURRENT_TIMESTAMP(3));
insert into meetup_attendee_detail(meetup_id, attendee_id, registration_date) values(1, 2, CURRENT_TIMESTAMP(3));
