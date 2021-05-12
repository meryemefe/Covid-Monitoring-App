package com.bilkent.covidmonitoringservice;

import com.bilkent.covidmonitoringservice.entity.Gender;
import com.bilkent.covidmonitoringservice.entity.Symptom;
import com.bilkent.covidmonitoringservice.entity.User;
import com.bilkent.covidmonitoringservice.exception.NoUserFoundException;
import com.bilkent.covidmonitoringservice.service.SymptomService;
import com.bilkent.covidmonitoringservice.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("test")
class CovidMonitoringServiceApplicationTests {

	@Autowired
	UserService userService;

	@Autowired
	SymptomService symptomService;

	@Test
	void testAddUser() {

		User user1 = new User();
		user1.setUsername("trial1");
		user1.setEmail("trail1@gmail.com");
		user1.setPassword("pass123");
		user1.setAge(23);
		user1.setGender(Gender.FEMALE);

		User user2 = new User();
		user2.setUsername("trial2");
		user2.setEmail("trail2@gmail.com");
		user2.setPassword("pass123");
		user2.setAge(30);
		user2.setGender(Gender.MALE);

		User user3 = new User();
		user3.setUsername("trial3");
		user3.setEmail("trail3@gmail.com");
		user3.setPassword("pass123");
		user3.setAge(28);
		user3.setGender(Gender.OTHER);

		User user4 = new User();
		user4.setUsername("trial4");
		user4.setEmail("trail4@gmail.com");
		user4.setPassword("pass123");
		user4.setAge(45);
		user4.setGender(Gender.FEMALE);

		User addedUser1 = userService.add(user1);
		User addedUser2 = userService.add(user2);
		User addedUser3 = userService.add(user3);
		User addedUser4 = userService.add(user4);

		Assertions.assertEquals(4, userService.getAll().size());
		Assertions.assertNotNull(addedUser1);
		Assertions.assertNotNull(addedUser2);
		Assertions.assertNotNull(addedUser3);
		Assertions.assertNotNull(addedUser4);

		User addUser1Again = userService.add(user1);
		User addUser2Again = userService.add(user2);
		User addUser3Again = userService.add(user3);
		User addUser4Again = userService.add(user4);

		Assertions.assertEquals(4, userService.getAll().size());
		Assertions.assertNull(addUser1Again);
		Assertions.assertNull(addUser2Again);
		Assertions.assertNull(addUser3Again);
		Assertions.assertNull(addUser4Again);
	}

	@Test
	void testUpdateUser() throws NoUserFoundException {

		User user1 = new User();
		user1.setUsername("trial1");
		user1.setEmail("trail1@gmail.com");
		user1.setPassword("pass123");
		user1.setAge(23);
		user1.setGender(Gender.FEMALE);

		User user2 = new User();
		user2.setUsername("trial2");
		user2.setEmail("trail2@gmail.com");
		user2.setPassword("pass123");
		user2.setAge(30);
		user2.setGender(Gender.MALE);

		User addedUser1 = userService.add(user1);
		User addedUser2 = userService.add(user2);

		Assertions.assertEquals(user1.getUsername(), userService.get(addedUser1.getId()).getUsername());
		Assertions.assertEquals(user2.getUsername(), userService.get(addedUser2.getId()).getUsername());
		Assertions.assertEquals(2, userService.getAll().size());

		// test update
		addedUser1.setEmail("changed1@gmail.com");
		addedUser1.setPassword("changedPass123");
		addedUser1.setAge(50);

		addedUser2.setUsername("changed2");
		addedUser2.setGender(Gender.OTHER);

		User updatedUser1 = userService.update(addedUser1);
		User updatedUser2 = userService.update(addedUser2);

		Assertions.assertEquals(2, userService.getAll().size());
		Assertions.assertEquals(updatedUser1.getEmail(), userService.get(addedUser1.getId()).getEmail());
		Assertions.assertEquals(updatedUser1.getPassword(), userService.get(addedUser1.getId()).getPassword());
		Assertions.assertEquals(updatedUser1.getAge(), userService.get(addedUser1.getId()).getAge());
		Assertions.assertEquals(updatedUser2.getUsername(), userService.get(addedUser2.getId()).getUsername());
		Assertions.assertEquals(updatedUser2.getGender(), userService.get(addedUser2.getId()).getGender());

	}

	@Test
	void testDeleteUser() throws NoUserFoundException {
		User user1 = new User();
		user1.setUsername("trial1");
		user1.setEmail("trail1@gmail.com");
		user1.setPassword("pass123");
		user1.setAge(23);
		user1.setGender(Gender.FEMALE);

		User user2 = new User();
		user2.setUsername("trial2");
		user2.setEmail("trail2@gmail.com");
		user2.setPassword("pass123");
		user2.setAge(30);
		user2.setGender(Gender.MALE);

		User user3 = new User();
		user3.setUsername("trial3");
		user3.setEmail("trail3@gmail.com");
		user3.setPassword("pass123");
		user3.setAge(28);
		user3.setGender(Gender.OTHER);

		User user4 = new User();
		user4.setUsername("trial4");
		user4.setEmail("trail4@gmail.com");
		user4.setPassword("pass123");
		user4.setAge(45);
		user4.setGender(Gender.FEMALE);

		User addedUser1 = userService.add(user1);
		User addedUser2 = userService.add(user2);
		User addedUser3 = userService.add(user3);
		User addedUser4 = userService.add(user4);

		Assertions.assertEquals(4, userService.getAll().size());

		// test delete user
		User userToDelete = userService.get(addedUser1.getId());
		userService.delete(userToDelete);
		Assertions.assertEquals(3, userService.getAll().size());

		userToDelete = userService.get(addedUser2.getId());
		userService.delete(userToDelete);
		Assertions.assertEquals(2, userService.getAll().size());

		userToDelete = userService.get(addedUser3.getId());
		userService.delete(userToDelete);
		Assertions.assertEquals(1, userService.getAll().size());

		userToDelete = userService.get(addedUser4.getId());
		userService.delete(userToDelete);
		Assertions.assertEquals(0, userService.getAll().size());
	}

	@Test
	void testGetByUsername() throws NoUserFoundException {
		User user1 = new User();
		user1.setUsername("trial1");
		user1.setEmail("trail1@gmail.com");
		user1.setPassword("pass123");
		user1.setAge(23);
		user1.setGender(Gender.FEMALE);

		User user2 = new User();
		user2.setUsername("trial2");
		user2.setEmail("trail2@gmail.com");
		user2.setPassword("pass123");
		user2.setAge(30);
		user2.setGender(Gender.MALE);

		User addedUser1 = userService.add(user1);
		User addedUser2 = userService.add(user2);

		Assertions.assertEquals(addedUser1.getId(), userService.getByUsername(user1.getUsername()).getId());
		Assertions.assertEquals(addedUser2.getId(), userService.getByUsername(user2.getUsername()).getId());
	}

	@Test
	void testAddSymptomForOneUser() {

		User user1 = new User();
		user1.setUsername("trial1");
		user1.setEmail("trail1@gmail.com");
		user1.setPassword("pass123");
		user1.setAge(23);
		user1.setGender(Gender.FEMALE);

		User addedUser1 = userService.add(user1);
		Symptom symptom1 = new Symptom();
		symptom1.setUserId(addedUser1.getId());
		symptom1.setDate(LocalDate.of(2021,5,3));
		symptom1.setFever(true);
		symptom1.setDryCough(false);
		symptom1.setTiredness(true);
		symptom1.setLossOfTaste(false);
		symptom1.setLossOfSmell(false);
		symptom1.setSoreThroat(false);
		symptom1.setHeadache(false);

		Symptom symptom2 = new Symptom();
		symptom2.setUserId(addedUser1.getId());
		symptom2.setDate(LocalDate.of(2021,5,2));
		symptom2.setFever(true);
		symptom2.setDryCough(false);
		symptom2.setTiredness(true);
		symptom2.setLossOfTaste(false);
		symptom2.setLossOfSmell(false);
		symptom2.setSoreThroat(false);
		symptom2.setHeadache(false);

		Symptom symptom3 = new Symptom();
		symptom3.setUserId(addedUser1.getId());
		symptom3.setDate(LocalDate.of(2021,5,1));
		symptom3.setFever(true);
		symptom3.setDryCough(false);
		symptom3.setTiredness(true);
		symptom3.setLossOfTaste(false);
		symptom3.setLossOfSmell(false);
		symptom3.setSoreThroat(false);
		symptom3.setHeadache(false);

		Symptom addedSymptom1 = symptomService.addDailySymptom(symptom1);
		Symptom addedSymptom2 = symptomService.addDailySymptom(symptom2);
		Symptom addedSymptom3 = symptomService.addDailySymptom(symptom3);

		Assertions.assertEquals(3, symptomService.getSymptomsByUserId(addedUser1.getId()).size());
		Assertions.assertNotNull(addedSymptom1);
		Assertions.assertNotNull(addedSymptom2);
		Assertions.assertNotNull(addedSymptom3);

		Symptom addedSymptom1Again = symptomService.addDailySymptom(symptom1);
		Symptom addedSymptom2Again = symptomService.addDailySymptom(symptom2);
		Symptom addedSymptom3Again = symptomService.addDailySymptom(symptom3);

		Assertions.assertEquals(3, symptomService.getSymptomsByUserId(addedUser1.getId()).size());
		Assertions.assertNull(addedSymptom1Again);
		Assertions.assertNull(addedSymptom2Again);
		Assertions.assertNull(addedSymptom3Again);
	}

	@Test
	void testAddSymptomForDifferentUsers(){

		User user1 = new User();
		user1.setUsername("trial1");
		user1.setEmail("trail1@gmail.com");
		user1.setPassword("pass123");
		user1.setAge(23);
		user1.setGender(Gender.FEMALE);

		User user2 = new User();
		user2.setUsername("trial2");
		user2.setEmail("trail2@gmail.com");
		user2.setPassword("pass123");
		user2.setAge(30);
		user2.setGender(Gender.MALE);

		User user3 = new User();
		user3.setUsername("trial3");
		user3.setEmail("trail3@gmail.com");
		user3.setPassword("pass123");
		user3.setAge(28);
		user3.setGender(Gender.OTHER);

		User addedUser1 = userService.add(user1);
		User addedUser2 = userService.add(user2);
		User addedUser3 = userService.add(user3);

		// Add for user 1

		Symptom symptom1 = new Symptom();
		symptom1.setUserId(addedUser1.getId());
		symptom1.setDate(LocalDate.of(2021,5,3));
		symptom1.setFever(true);
		symptom1.setDryCough(false);
		symptom1.setTiredness(true);
		symptom1.setLossOfTaste(false);
		symptom1.setLossOfSmell(false);
		symptom1.setSoreThroat(false);
		symptom1.setHeadache(false);

		Symptom symptom2 = new Symptom();
		symptom2.setUserId(addedUser1.getId());
		symptom2.setDate(LocalDate.of(2021,5,2));
		symptom2.setFever(true);
		symptom2.setDryCough(false);
		symptom2.setTiredness(true);
		symptom2.setLossOfTaste(false);
		symptom2.setLossOfSmell(false);
		symptom2.setSoreThroat(false);
		symptom2.setHeadache(false);

		Symptom symptom3 = new Symptom();
		symptom3.setUserId(addedUser1.getId());
		symptom3.setDate(LocalDate.of(2021,5,1));
		symptom3.setFever(true);
		symptom3.setDryCough(false);
		symptom3.setTiredness(true);
		symptom3.setLossOfTaste(false);
		symptom3.setLossOfSmell(false);
		symptom3.setSoreThroat(false);
		symptom3.setHeadache(false);

		Symptom addedSymptom1ForUser1 = symptomService.addDailySymptom(symptom1);
		Symptom addedSymptom2ForUser1 = symptomService.addDailySymptom(symptom2);
		Symptom addedSymptom3ForUser1 = symptomService.addDailySymptom(symptom3);

		// Add for user 2
		Symptom symptom4 = new Symptom();
		symptom4.setUserId(addedUser2.getId());
		symptom4.setDate(LocalDate.of(2021,5,3));
		symptom4.setFever(true);
		symptom4.setDryCough(false);
		symptom4.setTiredness(true);
		symptom4.setLossOfTaste(false);
		symptom4.setLossOfSmell(false);
		symptom4.setSoreThroat(false);
		symptom4.setHeadache(false);

		Symptom symptom5 = new Symptom();
		symptom5.setUserId(addedUser2.getId());
		symptom5.setDate(LocalDate.of(2021,5,2));
		symptom5.setFever(true);
		symptom5.setDryCough(false);
		symptom5.setTiredness(true);
		symptom5.setLossOfTaste(false);
		symptom5.setLossOfSmell(false);
		symptom5.setSoreThroat(false);
		symptom5.setHeadache(false);

		Symptom addedSymptom1ForUser2 = symptomService.addDailySymptom(symptom4);
		Symptom addedSymptom2ForUser2 = symptomService.addDailySymptom(symptom5);

		// Add for user 3
		Symptom symptom6 = new Symptom();
		symptom6.setUserId(addedUser3.getId());
		symptom6.setDate(LocalDate.of(2021,5,3));
		symptom6.setFever(true);
		symptom6.setDryCough(false);
		symptom6.setTiredness(true);
		symptom6.setLossOfTaste(false);
		symptom6.setLossOfSmell(false);
		symptom6.setSoreThroat(false);
		symptom6.setHeadache(false);

		Symptom addedSymptom3ForUser3 = symptomService.addDailySymptom(symptom6);

		Assertions.assertEquals(3, symptomService.getSymptomsByUserId(addedUser1.getId()).size());
		Assertions.assertEquals(2, symptomService.getSymptomsByUserId(addedUser2.getId()).size());
		Assertions.assertEquals(1, symptomService.getSymptomsByUserId(addedUser3.getId()).size());
	}

	@Test
	void testUpdateSymptom(){
		User user1 = new User();
		user1.setUsername("trial1");
		user1.setEmail("trail1@gmail.com");
		user1.setPassword("pass123");
		user1.setAge(23);
		user1.setGender(Gender.FEMALE);

		User addedUser1 = userService.add(user1);

		Symptom symptom1 = new Symptom();
		symptom1.setUserId(addedUser1.getId());
		symptom1.setDate(LocalDate.of(2021,5,3));
		symptom1.setFever(false);
		symptom1.setDryCough(false);
		symptom1.setTiredness(false);
		symptom1.setLossOfTaste(false);
		symptom1.setLossOfSmell(true);
		symptom1.setSoreThroat(true);
		symptom1.setHeadache(true);

		Symptom symptom2 = new Symptom();
		symptom2.setUserId(addedUser1.getId());
		symptom2.setDate(LocalDate.of(2021,5,2));
		symptom2.setFever(true);
		symptom2.setDryCough(true);
		symptom2.setTiredness(true);
		symptom2.setLossOfTaste(true);
		symptom2.setLossOfSmell(false);
		symptom2.setSoreThroat(false);
		symptom2.setHeadache(false);

		Symptom addedSymptom1 = symptomService.addDailySymptom(symptom1);
		Symptom addedSymptom2 = symptomService.addDailySymptom(symptom2);

		Assertions.assertEquals(2, symptomService.getSymptomsByUserId(addedUser1.getId()).size());
		Assertions.assertNotNull(addedSymptom1);
		Assertions.assertNotNull(addedSymptom2);

		// update
		symptom1.setFever(true);
		symptom1.setDryCough(true);
		symptom1.setTiredness(true);
		symptom1.setLossOfTaste(true);
		symptom1.setLossOfSmell(false);
		symptom1.setSoreThroat(false);
		symptom1.setHeadache(false);

		symptom2.setFever(true);
		symptom2.setDryCough(true);
		symptom2.setTiredness(true);
		symptom2.setLossOfTaste(true);
		symptom2.setLossOfSmell(true);
		symptom2.setSoreThroat(true);
		symptom2.setHeadache(true);

		Symptom updatedSymptom1 = symptomService.updateDailySymptom(symptom1);
		Symptom updatedSymptom2 = symptomService.updateDailySymptom(symptom2);

		Assertions.assertEquals(2, symptomService.getSymptomsByUserId(addedUser1.getId()).size());

		Assertions.assertEquals(updatedSymptom1.isFever(), symptomService.getSymptomById(addedSymptom1.getId()).isFever());
		Assertions.assertEquals(updatedSymptom1.isDryCough(), symptomService.getSymptomById(addedSymptom1.getId()).isDryCough());
		Assertions.assertEquals(updatedSymptom1.isTiredness(), symptomService.getSymptomById(addedSymptom1.getId()).isTiredness());
		Assertions.assertEquals(updatedSymptom1.isLossOfTaste(), symptomService.getSymptomById(addedSymptom1.getId()).isLossOfTaste());
		Assertions.assertEquals(updatedSymptom1.isLossOfSmell(), symptomService.getSymptomById(addedSymptom1.getId()).isLossOfSmell());
		Assertions.assertEquals(updatedSymptom1.isSoreThroat(), symptomService.getSymptomById(addedSymptom1.getId()).isSoreThroat());
		Assertions.assertEquals(updatedSymptom1.isHeadache(), symptomService.getSymptomById(addedSymptom1.getId()).isHeadache());

		Assertions.assertEquals(updatedSymptom2.isFever(), symptomService.getSymptomById(addedSymptom2.getId()).isFever());
		Assertions.assertEquals(updatedSymptom2.isDryCough(), symptomService.getSymptomById(addedSymptom2.getId()).isDryCough());
		Assertions.assertEquals(updatedSymptom2.isTiredness(), symptomService.getSymptomById(addedSymptom2.getId()).isTiredness());
		Assertions.assertEquals(updatedSymptom2.isLossOfTaste(), symptomService.getSymptomById(addedSymptom2.getId()).isLossOfTaste());
		Assertions.assertEquals(updatedSymptom2.isLossOfSmell(), symptomService.getSymptomById(addedSymptom2.getId()).isLossOfSmell());
		Assertions.assertEquals(updatedSymptom2.isSoreThroat(), symptomService.getSymptomById(addedSymptom2.getId()).isSoreThroat());
		Assertions.assertEquals(updatedSymptom2.isHeadache(), symptomService.getSymptomById(addedSymptom2.getId()).isHeadache());

	}

	@Test
	void testDeleteSymptom(){

		User user1 = new User();
		user1.setUsername("trial1");
		user1.setEmail("trail1@gmail.com");
		user1.setPassword("pass123");
		user1.setAge(23);
		user1.setGender(Gender.FEMALE);

		User addedUser1 = userService.add(user1);
		Symptom symptom1 = new Symptom();
		symptom1.setUserId(addedUser1.getId());
		symptom1.setDate(LocalDate.of(2021,5,3));
		symptom1.setFever(true);
		symptom1.setDryCough(false);
		symptom1.setTiredness(true);
		symptom1.setLossOfTaste(false);
		symptom1.setLossOfSmell(false);
		symptom1.setSoreThroat(false);
		symptom1.setHeadache(false);

		Symptom symptom2 = new Symptom();
		symptom2.setUserId(addedUser1.getId());
		symptom2.setDate(LocalDate.of(2021,5,2));
		symptom2.setFever(true);
		symptom2.setDryCough(false);
		symptom2.setTiredness(true);
		symptom2.setLossOfTaste(false);
		symptom2.setLossOfSmell(false);
		symptom2.setSoreThroat(false);
		symptom2.setHeadache(false);

		Symptom symptom3 = new Symptom();
		symptom3.setUserId(addedUser1.getId());
		symptom3.setDate(LocalDate.of(2021,5,1));
		symptom3.setFever(true);
		symptom3.setDryCough(false);
		symptom3.setTiredness(true);
		symptom3.setLossOfTaste(false);
		symptom3.setLossOfSmell(false);
		symptom3.setSoreThroat(false);
		symptom3.setHeadache(false);

		Symptom addedSymptom1 = symptomService.addDailySymptom(symptom1);
		Symptom addedSymptom2 = symptomService.addDailySymptom(symptom2);
		Symptom addedSymptom3 = symptomService.addDailySymptom(symptom3);

		Assertions.assertEquals(3, symptomService.getSymptomsByUserId(addedUser1.getId()).size());
		Assertions.assertNotNull(addedSymptom1);
		Assertions.assertNotNull(addedSymptom2);
		Assertions.assertNotNull(addedSymptom3);

		Symptom symptomToDelete = symptomService.getSymptomById(addedSymptom1.getId());
		symptomService.deleteDailySymptom(symptomToDelete);
		Assertions.assertEquals(2, symptomService.getSymptomsByUserId(addedUser1.getId()).size());

		symptomToDelete = symptomService.getSymptomById(addedSymptom2.getId());
		symptomService.deleteDailySymptom(symptomToDelete);
		Assertions.assertEquals(1, symptomService.getSymptomsByUserId(addedUser1.getId()).size());

		symptomToDelete = symptomService.getSymptomById(addedSymptom3.getId());
		symptomService.deleteDailySymptom(symptomToDelete);
		Assertions.assertEquals(0, symptomService.getSymptomsByUserId(addedUser1.getId()).size());
	}

	@Test
	void testEmergencyForUserNotHaveRecord() {
		// Add user
		User user = new User();
		user.setUsername("trial1");
		user.setEmail("trail1@gmail.com");
		user.setPassword("pass123");
		user.setAge(23);
		user.setGender(Gender.FEMALE);
		User addedUser = userService.add(user);

		Assertions.assertEquals("You don't have any symptom record!", symptomService.getEmergencyStatus(addedUser.getId()));
	}

	@Test
	void testEmergencyForUserHaveNotRecentRecord() {
		// Add user
		User user = new User();
		user.setUsername("trial1");
		user.setEmail("trail1@gmail.com");
		user.setPassword("pass123");
		user.setAge(23);
		user.setGender(Gender.FEMALE);
		User addedUser = userService.add(user);

		// Add symptom for today
		Symptom symptom1 = new Symptom();
		symptom1.setUserId(addedUser.getId());
		symptom1.setDate(LocalDate.now().minusDays(5));
		symptom1.setFever(true);
		symptom1.setDryCough(true);
		symptom1.setTiredness(false);
		symptom1.setLossOfTaste(false);
		symptom1.setLossOfSmell(false);
		symptom1.setSoreThroat(false);
		symptom1.setHeadache(false);

		Symptom addedSymptom1 = symptomService.addDailySymptom(symptom1);

		Assertions.assertEquals("You did not enter any symptom for today. Please, add your daily symptom.", symptomService.getEmergencyStatus(addedUser.getId()));

	}

	@Test
	void testEmergencyForDailyHealthyUser(){
		// Add user
		User user = new User();
		user.setUsername("trial1");
		user.setEmail("trail1@gmail.com");
		user.setPassword("pass123");
		user.setAge(23);
		user.setGender(Gender.FEMALE);
		User addedUser = userService.add(user);

		// Add symptom for today
		Symptom symptom1 = new Symptom();
		symptom1.setUserId(addedUser.getId());
		symptom1.setDate(LocalDate.now());
		symptom1.setFever(true);
		symptom1.setDryCough(true);
		symptom1.setTiredness(false);
		symptom1.setLossOfTaste(false);
		symptom1.setLossOfSmell(false);
		symptom1.setSoreThroat(false);
		symptom1.setHeadache(false);

		Symptom addedSymptom1 = symptomService.addDailySymptom(symptom1);

		Assertions.assertEquals("You don't need to see a doctor for now. Take care of yourself!", symptomService.getEmergencyStatus(addedUser.getId()));
	}

	@Test
	void testEmergencyForDailyUnhealthyUser() {

		// Add user
		User user = new User();
		user.setUsername("trial1");
		user.setEmail("trail1@gmail.com");
		user.setPassword("pass123");
		user.setAge(23);
		user.setGender(Gender.FEMALE);
		User addedUser = userService.add(user);

		// Add symptom for today
		Symptom symptom1 = new Symptom();
		symptom1.setUserId(addedUser.getId());
		symptom1.setDate(LocalDate.now());
		symptom1.setFever(true);
		symptom1.setDryCough(true);
		symptom1.setTiredness(true);
		symptom1.setLossOfTaste(true);
		symptom1.setLossOfSmell(false);
		symptom1.setSoreThroat(false);
		symptom1.setHeadache(false);

		Symptom addedSymptom1 = symptomService.addDailySymptom(symptom1);

		Assertions.assertEquals("You should see a doctor!", symptomService.getEmergencyStatus(addedUser.getId()));
	}
}
