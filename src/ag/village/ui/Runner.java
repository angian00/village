package ag.village.ui;

import ag.village.Village;

public class Runner {

	public static void main(String[] args) throws Exception {
		Village v;
		
		System.out.println("--- Starting Village simulation ---");
		
		v = new Village();
		System.out.print(v.toString());
		System.out.println("--- Village simulation terminated ---");
	}

}
