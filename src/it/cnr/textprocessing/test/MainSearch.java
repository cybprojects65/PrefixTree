package it.cnr.textprocessing.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

import it.cnr.textprocessing.EfficientSearchInText;

public class MainSearch {

	public static void main(String[] args) throws Exception {
		EfficientSearchInText est = new EfficientSearchInText();
		File referenceTaxa = new File("taxsons.csv");
		
		int nSpecies = 0;
		BufferedReader br = new BufferedReader(new FileReader(referenceTaxa));
		String line = br.readLine();
		while(line!=null) {
			
			if (line.trim().contains(" "))
				nSpecies++;
			line = br.readLine();
		}
		br.close();
				System.out.println("N Species: "+nSpecies);
		
		int nthreads = 8;
		long t0 = System.currentTimeMillis();
		String toSearch [] = {
				"mentha gentilis agardhiana",
				"theclopsis mycon",
				"korscheltellus ganna",
				"afromarengo coriacea",
				"calycopis gizela",
				"strymon crambusa",
				"colgar asperum",
				"phaeostrymon alcestis oslari",
				"colgar",
				"hello"};
		
		boolean found[] = est.searchParallel(toSearch, referenceTaxa,nthreads);
		System.out.println("Found "+Arrays.toString(found));
		long t1 = System.currentTimeMillis();
		System.out.println("Elapsed Parallel "+(t1-t0)+"ms");
		t0 = System.currentTimeMillis();
		est.searchBruteForce(toSearch, referenceTaxa);
		t1 = System.currentTimeMillis();
		System.out.println("Elapsed Brute "+(t1-t0)+"ms");
	}
	
	
}
