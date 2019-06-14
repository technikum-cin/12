package at.technikumwien;

import java.time.Clock;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Null;

import lombok.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="t_employee")
@NamedQuery(
	name="Employee.findAll",
	query="SELECT e FROM Employee e ORDER BY e.lastName ASC, e.firstName ASC"
)
public class Employee {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NonNull
	@Column(length=50, nullable=false)
	@JsonbProperty(value="first-name", nillable=false)
	private String firstName;

	@NonNull
	@Column(length=50, nullable=false)
	@JsonbProperty(value="last-name", nillable=false)
	private String lastName;

	@NonNull
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	@JsonbProperty(nillable=false)
	private Sex sex;

	@NonNull
	@Column(nullable=false)
	@JsonbProperty(value="birth-date", nillable=false)
	private LocalDate birthDate;	

	@NonNull
	@Column(nullable=false)
	@JsonbProperty(nillable=false)
	private boolean active;
	
	// required only for unit testing
	@Getter(AccessLevel.NONE)
	@Transient
	private Clock clock = Clock.systemDefaultZone();	
	
	@JsonbTransient
	public String getDescription() {
		return getFullName() + " (" + getAgeInYears() + " Jahre, " + (active ? "aktiv" : "inaktiv") + ")"; 
	}
	
	@JsonbTransient
	public String getFullName() {
		Objects.requireNonNull(sex,"sex must not be null");
		return (sex == Sex.FEMALE ? "Frau" : "Herr") + " " + firstName + " " + lastName;
	}
	
	@JsonbTransient
	public int getAgeInYears() {
		return Period.between(birthDate, LocalDate.now(clock)).getYears();
	}
}