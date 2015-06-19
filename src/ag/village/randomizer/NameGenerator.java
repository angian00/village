package ag.village.randomizer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import ag.village.Sex;

public class NameGenerator {
	List<String> maleNames;
	List<String> femaleNames;

	public NameGenerator() throws IOException {
		this("data/maleNames.txt", "data/femaleNames.txt");
	}
	
	public NameGenerator(String maleFile, String femaleFile) throws IOException {
		maleNames = loadNames(maleFile);
		femaleNames = loadNames(femaleFile);
	}
	
	
	public String randomName(Sex sex) {
		if (sex == Sex.MALE)
			return randomName(maleNames);
		else
			return randomName(femaleNames);
	}
	
	private String randomName(List<String> nameList) {
		return nameList.get((int)Math.floor(Math.random() * nameList.size()));
	}
	
	
	private static List<String> loadNames(String filePath) throws IOException {
		List<String> result = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line;
		
		while ((line=br.readLine()) != null) {
			result.add(line);
		}
		br.close();
		
		return result;
	}
}
