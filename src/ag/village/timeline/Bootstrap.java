package ag.village.timeline;

import ag.village.*;
import ag.village.randomizer.Demographics;
import ag.village.randomizer.NameGenerator;
import cern.jet.random.Poisson;
import cern.jet.random.engine.RandomEngine;

public class Bootstrap {
	public static int BIRTH_RATE = 5; //mean # births/year
	
	private Village v;
	private int nYears;
	private int currYear = 0;
	
	private Poisson birthDist;
	private NameGenerator ng;
	

	public Bootstrap(int nYears) throws Exception {
		this.nYears = nYears;
		v = new Village();

		birthDist = new Poisson(BIRTH_RATE, RandomEngine.makeDefault());
		ng = new NameGenerator();
	}
	
	public void run() {
		while (currYear < nYears) {
			nextYear();
		}

		System.out.println(v.reportStats());
	}
	
	
	private void nextYear() {
		int nBirths = birthDist.nextInt();
		for (int i=0; i < nBirths; i++) {
			Sex sex = Demographics.randomSex();
			String name = ng.randomName(sex);
			
			v.addPerson(name, sex);
		}

		System.out.println("year " + currYear + ": " + nBirths + " births");
		currYear ++;
	}
	

	
	public static void main(String[] args) throws Exception {
		System.out.println("--- Bootstrapping Village simulation ---");
		
		new Bootstrap(10).run();
		System.out.println("--- Village simulation terminated ---");
	}

}
