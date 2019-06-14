package at.technikumwien;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import at.technikumwien.Employee;
import at.technikumwien.Sex;

class EmployeeTest {
	private Employee emp;
	private Employee empFemale;
	private Employee empMale;

//	@BeforeAll
//	public static void setUpBeforeClass() {}
//
//	@AfterAll
//	public static void tearDownAfterClass() {}

	@BeforeEach
	public void setUp() {
		emp = new Employee();
		new Employee();
		empFemale = new Employee("Martina", "Musterfrau", Sex.FEMALE, LocalDate.of(1991, 1, 1), true);
		empFemale.setClock(getFixedClockWithDate(2009, 12, 31));
		
		empMale = new Employee("Markus", "Mustermann", Sex.MALE, LocalDate.of(1991, 1, 1), false);
		empMale.setClock(getFixedClockWithDate(2010, 1, 1));
	}

//	@AfterEach
//	public void tearDown() {}

	@Test
	public void testGetFullName() {
		assertEquals("Frau Martina Musterfrau", empFemale.getFullName());
		assertEquals("Herr Markus Mustermann", empMale.getFullName());
	}

	@Test
	public void testGetFullNameWithSexNull() {
		assertThrows(NullPointerException.class, () -> emp.getFullName());
	}
		
	@Test
	public void testGetAgeInYears() {
		assertEquals(18, empFemale.getAgeInYears());
		assertEquals(19, empMale.getAgeInYears());
	}
	
	@Test
	public void testGetAgeInYearsWithBirthDateNull() {
		assertThrows(NullPointerException.class, () -> emp.getAgeInYears());
	}	
	
	@Test
	public void testGetDescription() {
		assertEquals("Frau Martina Musterfrau (18 Jahre, aktiv)", empFemale.getDescription());
		assertEquals("Herr Markus Mustermann (19 Jahre, inaktiv)", empMale.getDescription());
	}
	
	private Clock getFixedClockWithDate(int year, int month, int dayOfMonth) {
		return Clock.fixed(
			LocalDateTime.of(year, month, dayOfMonth, 0, 0).toInstant(ZoneOffset.ofHours(0)),
			ZoneId.systemDefault()
		);
	}
	
	// TODO add more tests here ;-)	
}