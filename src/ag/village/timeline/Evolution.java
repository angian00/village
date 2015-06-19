package ag.village.timeline;

import ag.village.*;
import ag.village.randomizer.Demographics;
import ag.village.randomizer.NameGenerator;
import cern.jet.random.Poisson;
import cern.jet.random.engine.RandomEngine;

public class Evolution {
	public static int SPAWN_RATE = 5; //mean # spawns/year

	private Village v;
	private int nYears;
	private int currYear = 0;
	
	private Poisson spawnDist;
	private NameGenerator ng;
	

	public Evolution(int nYears) throws Exception {
		this.nYears = nYears;
		v = new Village();

		spawnDist = new Poisson(SPAWN_RATE, RandomEngine.makeDefault());
		ng = new NameGenerator();
	}
	
	public void run() {
		while (currYear < nYears) {
			nextYear();
		}

		System.out.println(v.reportStats());
//		System.out.println(v.reportLivingPeople().toString());
		System.out.println(v.reportAllPeople().toString());
	}
	
	
	private void nextYear() {
		//constant spawn //TODO: reduce as population grows
		int nSpawns = spawnDist.nextInt();
		for (int i=0; i < nSpawns; i++) {
			Sex sex = Demographics.randomSex();
			String name = ng.randomName(sex);
			
			v.addPerson(name, sex, currYear);
		}

		int nDeaths = 0;
		for (Person p: v.getLivingPeople()) {
			if (Demographics.randomDeath(p.getAge(currYear))) {
				p.setDeathYear(currYear);
				nDeaths ++;
			}
			
			//p(marriage)
			
			if ( (p.getSex() == Sex.FEMALE) && p.isMarried() ) {
			//	p(birth)
			}
		}

		System.out.println("year " + currYear + ": "
				+ nSpawns + " spawns, "
				+ nDeaths + " deaths, "
		);
		currYear ++;
	}
	

	
	public static void main(String[] args) throws Exception {
		System.out.println("--- Evolving Village simulation ---");
		
		new Evolution(10).run();
		System.out.println("--- Village simulation terminated ---");
	}

}
