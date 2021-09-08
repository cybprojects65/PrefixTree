package it.cnr.textprocessing.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import it.cnr.textprocessing.Trie;

public class SearchTaxonTree {

	public static void main(String[] args) throws Exception{
	
		File taxafile = new File("taxsons.csv");
		File taxonTree = new File("taxatree.bin");
		BufferedReader br = new BufferedReader(new FileReader(taxafile));
		Trie trie = new Trie();
		System.out.println("Reading taxa and populating tree");
		String line = br.readLine();
		long counter = 1;
		long limit = 1000000;
		int ntree = 0;
		while(line!=null) {
			line = line.trim();
			if (line!=null && line.length()>0) {
				trie.insert(line);
				
				if (counter%100==0)
					System.out.println("Status "+counter+" entries");
				
				if (counter==limit) {
					counter=0;
					long t0 = System.currentTimeMillis();
					System.out.println("check "+trie.contains("anemone"));				
					long t1 = System.currentTimeMillis();
					System.out.println("Efficiency "+(t0-t1)+"ms");
					
					System.out.println("Saving tree "+ntree);
					File taxonTreeN = new File(taxonTree.getAbsolutePath().replace(".bin", "_"+ntree+".bin"));
					Trie.saveToFile(taxonTreeN,trie);
					long t2 = System.currentTimeMillis();
					System.out.println("Done saving tree "+ntree+" in "+(t2-t1)+" ms");
					trie = null;
					System.gc();
					trie = new Trie();
					ntree++;
				}
					
			}
			line = br.readLine();
			counter++;
		}
		br.close();		
		
		if (!trie.isEmpty()) {
			System.out.println("Saving final tree");
			File taxonTreeN = new File(taxonTree.getAbsolutePath().replace(".bin", "_"+ntree+".bin"));
			trie.saveToFile(taxonTreeN);
		}
		
		System.out.println("Done");
	}
}
