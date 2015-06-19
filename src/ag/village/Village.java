package ag.village;

import java.util.*;

public class Village {
	Map<Long, Person> people;
	long nextId = 0;
	
	public Village() {
		people = new HashMap<Long, Person>();
	}
	
	
	public Person addPerson(String name, Sex sex) {
		return addPerson(name, sex, 0);
	}
	
	public Person addPerson(String name, Sex sex, int birthYear) {
		long id = nextId ++;
		Person p = new Person(id, name, sex, birthYear); 
		
		people.put(id, p);
		return p;
	}
	
	
	public Set<Person> getLivingPeople() {
		Set<Person> result = new HashSet<Person>();
		
		for (Person p: people.values()) {
			if (p.getDeathYear() == -1)
				result.add(p);
		}
		
		return result;
	}
	
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for (Long id: people.keySet()) {
			sb.append(people.get(id).toString());
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
	
	public String reportStats() {
		int nAlive = 0;
		for (Person p: people.values()) {
			if (p.getDeathYear() == -1)
				nAlive ++;
		}
		
		return "# living people: " + nAlive;
	}

	public StringBuilder reportLivingPeople() {
		StringBuilder sb = new StringBuilder();
		
		List<Person> living = new ArrayList<Person>();
		for (Person p: people.values()) {
			if (p.getDeathYear() == -1)
				living.add(p);
		}
		
		Person.sortChrono(living);

		sb.append("living people: \n");
		for (Person p: living) {
			sb.append(p.toString());
			sb.append("\n");
		}
		
		return sb;
	}

	public StringBuilder reportAllPeople() {
		StringBuilder sb = new StringBuilder();
		
		List<Person> all = new ArrayList<Person>(people.values());

		Person.sortChrono(all);

		sb.append("all time people, living and dead: \n");
		for (Person p: all) {
			sb.append(p.toString());
			sb.append("\n");
		}
		
		return sb;
	}
}
