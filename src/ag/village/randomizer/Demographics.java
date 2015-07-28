package ag.village.randomizer;

import java.util.Arrays;

import ag.village.Sex;

public class Demographics {
	public static int[]    AGE_COHORTS = { 0, 14, 18, 26, 31, 41, 51, 61, 71, 81, 999 };
	public static double[] AGE_DIST    = { 29, 7, 14,  8, 14, 12,  9,  5,  2, 0.1};
	public static double[] AGE_CUM_DIST;

	/**
	 * Following rates refer to AGE_COHORTS
	 */
	public static double[] BIRTH_RATES = { .1, .05, .01, .01, .01, .01, .01, .01, .01, .01 };
	public static double[] DEATH_RATES = { .1, .05, .01, .01, .01, .01, .01, .01, .01, .01 };


	static {
		int l = AGE_DIST.length;
		AGE_CUM_DIST = new double[l];

		double totDist = 0;
		double progCum = 0;
		for (int i=0; i < l; i++) {
			totDist += AGE_DIST[i];
		}

		for (int i=0; i < l; i++) {
			progCum += AGE_DIST[i];
			AGE_CUM_DIST[i] = progCum / totDist;
		}
	}

	public static boolean randomDeath(int age)
	{
		int iCohort = Arrays.binarySearch(AGE_COHORTS, age);
		if (iCohort < 0)
			iCohort = -iCohort - 1;

		double rate = DEATH_RATES[iCohort];

		return (Math.random() <= rate);
	}


	/**
	 *
	 * @deprecated since birthYear has been added
	 */
	public static int randomAge() {
		double rnd = Math.random();
		int iCohort = Arrays.binarySearch(AGE_CUM_DIST, rnd);
		if (iCohort < 0)
			iCohort = -iCohort - 1;

		if (iCohort == AGE_CUM_DIST.length - 1) {
			return AGE_COHORTS[AGE_COHORTS.length-1];
		} else {
			//assume uniform distribution inside
			int minAge = AGE_COHORTS[iCohort];
			int maxAge = AGE_COHORTS[iCohort+1];
			return minAge + (int)Math.floor( ((double)(maxAge - minAge)) * Math.random() );
		}
	}

	public static Sex randomSex() {
		double rnd = Math.random();
		return (rnd < 0.5 ? Sex.MALE : Sex.FEMALE);
	}
}
