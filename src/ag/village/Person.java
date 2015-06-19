package ag.village;

import java.util.*;

public class Person {
	private long id;
	private String name;
	private Sex sex;
	private int birthYear;
	private int deathYear;
	
	public Person(long id, String name, Sex sex, int birthYear) {
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.birthYear = birthYear;
		this.deathYear = -1;
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Sex getSex() {
		return sex;
	}
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	public int getBirthYear() {
		return birthYear;
	}
	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}
	public int getDeathYear() {
		return deathYear;
	}
	public void setDeathYear(int deathYear) {
		this.deathYear = deathYear;
	}

	public int getAge(int currYear) {
		return currYear - birthYear;
	}

	
	public boolean isMarried() {
		//TODO: isMarried()
		return false;
	}
	
	
	/**
	 * order by birth year, eventually by death year (living first)
	 */
	public static void sortChrono(List<Person> people) {
		Collections.sort(people, new Comparator<Person>() {
			public int compare(Person p1, Person p2) {
				if (p1.getBirthYear() > p2.getBirthYear())
					return 1;

				if (p1.getBirthYear() < p2.getBirthYear())
					return -1;
				
				if (p1.getDeathYear() > p2.getDeathYear())
					return 1;

				if (p1.getDeathYear() < p2.getDeathYear())
					return -1;

				return 0;
			}
		});
	}

	
	public String toString() {
		return String.format("[%03d] %30s: %1s, b: %2d%s", id, name, 
				(sex == Sex.MALE ? "m" : "f"),
				birthYear,
				(deathYear == -1 ? "" : ", d: " + deathYear)
		);
//		return "[" + id + "] " + name + ": " + (sex == Sex.MALE ? "m" : "f")
//				+ ", b: " + birthYear + (deathYear == -1 ? "" : ", d: " + deathYear);
	}
}
